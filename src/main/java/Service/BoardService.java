package Service;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.Vector;

import DAO.BoardDAO;
import VO.BoardVO;
import VO.ReplyVO;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class BoardService {
	
	// 멤버변수 추가
	BoardDAO dao;

	// Singleton pattern START
	private static BoardService instance = new BoardService();
	
	private BoardService() {
		dao = BoardDAO.getInstance();
	};
	
	public static BoardService getInstance() {
		
		if(instance == null) {
			instance = new BoardService();
		}
		
		return instance;
	}
	// Singleton pattern END
	
	// 게시물 읽어오기 처리 함수
	public Vector<BoardVO> getBoardList(int start, int end){
		return dao.getBoardList(start, end);
	}
	
	// 전체 게시물 개수 읽어오기 처리 함수
	public int getTotalCount() {
		return dao.getTotalCount();
	}
	
	// 게시물 Write
	public void boardWrite(HttpServletRequest req) {
		dao.boardWrite(req);
	}
	
	// 게시물 Read
	public BoardVO getBoardVO(int number) {
		return dao.getBoardVO(number);
	}
	
	// 게시물 Update
	public void boardUpdate(BoardVO vo) {
		dao.boardUpdate(vo);
	}
	
	// 게시물 Delete
	public void boardDelete(int number) {
		dao.boardDelete(number);
	}
	
	// 파일 Download
	public void fileDownload(HttpServletRequest req, HttpServletResponse resp) {
		
		// 파일 경로 설정
		String saveDir = BoardDAO.SAVEFOLDER;
		
		HttpSession session = req.getSession();
		BoardVO boardVO = (BoardVO) session.getAttribute("BoardVO");
		
		String filename = boardVO.getFileName();
		String email = boardVO.getEmail();
		
		String filepath = saveDir + File.separator + email + File.separator + filename;
		// 파일 경로를 상세히 설정해야한다.
		// c:\\upload -> email.folder -> file
		
		try {
			
			// 파일명 UTF-8로 인코딩
			filename = URLEncoder.encode(filename, "utf-8");
			
			// MIME 타입 설정 (다운로드 타입으로)
			// Multipurpose Internet Mail Extensions
			resp.setContentType("application/octet-stream");
			
			// Header 설정
			resp.setHeader("content-disposition", "attachment; filename= " + filename);
			
			byte[] buffer = new byte[4096];
			
			// 파일 입출력을 통한 전송
			// 파일 --> 프로그램
			FileInputStream fileInputStream = new FileInputStream(filepath);
			
			// 서버 --> 클라이언트
			ServletOutputStream browserOutput = resp.getOutputStream();
			
			int read;
			
			while(true) {
				
				read = fileInputStream.read(buffer, 0, buffer.length);
				
				if(read == -1) {
					
					break;
					
				}
				
				browserOutput.write(buffer, 0, read);
				
			}
			
			browserOutput.flush();
			browserOutput.close();
			fileInputStream.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// post reply 함수 처리하기 (댓글 db에 저장하기)
	public void postReply(ReplyVO vo) {
		dao.postReply(vo);
	}
	
	// list reply 함수 처리하기 (db에 저장된 데이터 가져오기)
	public Vector<ReplyVO> getReplyList(int number){
		return dao.getReplyList(number);
	}
}
