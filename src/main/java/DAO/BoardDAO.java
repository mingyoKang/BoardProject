package DAO;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import VO.BoardVO;
import VO.MemberVO;
import VO.ReplyVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

public class BoardDAO {
	// DB에 접근하는 클래스
	
	// 멤버변수 DataSource 관련 참조변수
	Context initCtx;
	Context envCtx;
	DataSource ds;
	
	// 멤버변수 SQL 쿼리 관련 참조변수
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs = null;
	
	// 업로드 경로 추가하기
	public final static String SAVEFOLDER = "c:\\Cactus";

	// Singleton pattern START
	private static BoardDAO instance = new BoardDAO();
	
	private BoardDAO() {
		
		try {
			
			initCtx = new InitialContext();
			envCtx = (Context) initCtx.lookup("java:comp/env");
			ds = (DataSource) envCtx.lookup("jdbc/MysqlDB");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	};
	
	public static BoardDAO getInstance() {
		
		if(instance == null) {
			instance = new BoardDAO();
		}
		
		return instance;
		
	}
	// Singleton pattern END
	
	// 게시물 읽어오기 처리 함수
	public Vector<BoardVO> getBoardList(int start, int end){
		Vector<BoardVO> list = new Vector(); // 여기에 저장
		
		try {
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select * from board_tbl order by number desc limit ?,?");
			
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rs = pstmt.executeQuery();
			
			if(rs !=null) {
				
				while(rs.next()) {
					
					// 읽어온 테이블 자료들을 BoardVO로 저장하기
					BoardVO vo = new BoardVO(
							rs.getInt("number"),
							rs.getString("email"),
							rs.getString("password"),
							rs.getString("title"),
							rs.getString("content"),
							rs.getString("regDate"),
							rs.getString("ip"),
							rs.getInt("view"),
							rs.getString("fileName"),
							rs.getInt("fileSize"));
					
					// 미리 만들어놓은 list에 넣기
					list.add(vo);
					
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			try{
				pstmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			try{
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			try{
				rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return list;
	}
	
	// 전체 게시물 개수 받아오기
	public int getTotalCount() {
		int count = 0;
		
		try{
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select count(*) from board_tbl");
			
			rs = pstmt.executeQuery();
			
			if(rs !=null) {
				
				while(rs.next()) {
					count = rs.getInt(1);
				}
	
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			try {
				pstmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			try {
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			try {
				rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}			
		}
		
		return count;
		
	}
	
	// 게시물 Write
	public void boardWrite(HttpServletRequest req) {
		
		int fileSize = 0;
		String filename = null;
		
		try {
			
			conn = ds.getConnection();
			
			// 업로드 폴더 생성(미존재시)
			HttpSession session = req.getSession();
			MemberVO vo = (MemberVO) session.getAttribute("vo");
			String email = vo.getEmail();
			
			File upDir = new File(SAVEFOLDER + File.separator + email);
			
			if(upDir.exists() == false) {
				upDir.mkdirs();
			}
			
			// 파일 파트 추출
			Part part = req.getPart("anonyFile");
			// uploadFile이라는 이름을 가진 정보를 추출
			
			// 파일 이름 추출
			filename = getFilename(part);
			
			if(filename !=null) { // file이 있다면
				
				// 파일 업로드
				part.write(upDir + File.separator + filename);
				
			}else { // 파일이 없다면
				
				filename = "No_Name";
				
			}
			
			// DB에 게시물 저장
			
			pstmt = conn.prepareStatement("insert into board_tbl values(null,?,?,?,?,now(),?,0,?,?)");

			pstmt.setString(1, vo.getEmail());
			pstmt.setString(2, req.getParameter("password"));
			pstmt.setString(3, req.getParameter("title"));
			pstmt.setString(4, req.getParameter("content"));
			pstmt.setString(5, req.getRemoteAddr());
			pstmt.setString(6, filename);
			fileSize = (int)part.getSize();
			pstmt.setInt(7, fileSize);
			
			pstmt.executeUpdate();			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			try {
				pstmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			try {
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	// 파일 이름 추출 함수 처리하기
	private String getFilename(Part part) {
		
		System.out.println("=====파일 이름 추출 함수 시작=====");
		
		String contentDisp = part.getHeader("content-disposition");
		String[] tokens = contentDisp.split(";");
		// 세미콜론을 기준으로 문자 나누기
		String filename = tokens[2];
		
		System.out.println(filename); // file이 없을 때 어떻게 나오는지 보기
		
		if(filename.equals(" filename=\"\"")) {
			
			return null;
			
		}else {
			// 확장자 추출하기
			int dot = filename.lastIndexOf("."); // .의 위치 찾기
			String tmp = filename.substring(dot,filename.length()-1);
			System.out.println("파일 확장자명: " + tmp);
			// subString() 메소드로 문자를 자른다. (dot에서부터 맨 마지막 바로 앞까지)
			
			// UUID 식별자
			UUID random = UUID.randomUUID();
			
			// 기본형: 파일명_UUID.확장자
			
			// 파일이름
			filename = filename.substring(11,dot);
			
			System.out.println("FileName: " + filename);
			
			System.out.println("====파일 이름 추출 함수 끝=====");
			
			return filename+"_"+random+tmp;			
			
		}
		
	}
	
	// 게시물 View 증가시키기 함수
	public void upView(int number) {
		
		try {
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("update board_tbl set view=view+1 where number=?");
			
			pstmt.setInt(1, number);
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			try {
				pstmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			try {
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}			
		}
		
	}
	
	// 게시물 Read
	public BoardVO getBoardVO(int number) {
		
		BoardVO vo = null; // 저장할 공간
		
		upView(number); 
		// getBoardVO 메소드가 실행될 때마다 view를 1씩 증가시키는 메소드도 따라 실행되게 한다.
		
		try {
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select * from board_tbl where number=?");
			
			pstmt.setInt(1, number);
			
			rs = pstmt.executeQuery();
			
			if(rs !=null) {
				
				while(rs.next()) {
					
					vo = new BoardVO(
							rs.getInt("number"),
							rs.getString("email"),
							rs.getString("password"),
							rs.getString("title"),
							rs.getString("content"),
							rs.getString("regDate"),
							rs.getString("ip"),
							rs.getInt("view"),
							rs.getString("fileName"),
							rs.getInt("fileSize")
							);
					
				}
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			try {
				pstmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			try {
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			try {
				rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return vo;
		
	}
	
	// 게시물 Update
	public void boardUpdate(BoardVO vo) {
		
		try {
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("update board_tbl set title=?, content=? where number=?");
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getNumber());
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			try {
				pstmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			try {
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}			
		}
		
	}
	
	// 게시물 Delete
	public void boardDelete(int number) {
		
		try {
			
			conn = ds.getConnection();
			
			// 파일이름을 조회해서 해당 파일 삭제
			pstmt = conn.prepareStatement("select email, filename from board_tbl where number=?");
			
			pstmt.setInt(1, number);
			
			rs = pstmt.executeQuery();
			
			String email = null;
			String filename = null;
			
			if(rs !=null) {
				
				while(rs.next()) {
					
					email = rs.getString("email");
					filename = rs.getString("filename");
				}
			}
			
			if(!filename.equals("No_Name")) {
				
				File file = new File(SAVEFOLDER + File.separator + email + File.separator + filename);
				
				if(file.exists()) {
					file.delete();
				}
			}
			
			// 게시물 삭제
			pstmt = conn.prepareStatement("delete from board_tbl where number=?");
			
			pstmt.setInt(1, number);
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			try {
				rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			try {
				pstmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			try {
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}			
		}
	}
	
	// post reply 함수 처리하기(댓글 db에 저장하기)
	public void postReply(ReplyVO vo) {
		
		try {
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("insert into reply_tbl values(null,?,?,?,now())");
			
			pstmt.setInt(1, vo.getBoard_number());
			pstmt.setString(2, vo.getWriter());
			pstmt.setString(3, vo.getComment());
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			try {
				pstmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}

			try {
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}			
		}
	}
	
	// list Reply 함수 처리하기(db에 저장된 정보 불러오기)
	public Vector<ReplyVO> getReplyList(int number){
		Vector<ReplyVO> list = new Vector<ReplyVO>();
		ReplyVO vo = null;
		
		try {
			
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement("select * from reply_tbl where board_number=? order by reply_number desc");
			
			pstmt.setInt(1, number);
			
			rs = pstmt.executeQuery();
			
			if(rs !=null) {
				
				while(rs.next()) {
					
					vo = new ReplyVO(
							rs.getInt("board_number"),
							rs.getString("writer"),
							rs.getString("comment"),
							rs.getString("reg_date")
							);
					
					list.add(vo);
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			try {
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			try {
				pstmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			try {
				rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}			
		}
		
		return list;
	}
	
}
