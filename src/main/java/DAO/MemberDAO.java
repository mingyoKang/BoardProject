package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.mindrot.jbcrypt.BCrypt;

import VO.MemberVO;

public class MemberDAO {
	
	// 멤버 변수 DataSource 관련 참조변수
	Context initCtx;
	Context envCtx;
	DataSource ds;
	
	// 멤버 변수 SQL 쿼리 관련 참조변수
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs = null;

	// Singleton 패턴으로 객체를 하나 밖에 생성 못하도록 설정
	private static MemberDAO instance = new MemberDAO();
	
	private MemberDAO() {
		
		try {
			
			initCtx = new InitialContext();
			envCtx = (Context) initCtx.lookup("java:comp/env");
			ds = (DataSource) envCtx.lookup("jdbc/MysqlDB");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	};
	
	public static MemberDAO getInstance() {
		if(instance == null) {
			instance = new MemberDAO();
		}
		
		return instance;
	}
	// Singleton End
	
	// Join 처리 함수(회원 가입)
	
	public boolean MemberJoin(MemberVO vo) {
		
		boolean flag = false;
		
		try {
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("insert into member_tbl values(?,?,?,?,?,?)");
			
			pstmt.setString(1, vo.getEmail());
			pstmt.setString(2, BCrypt.hashpw(vo.getPassword(), BCrypt.gensalt())); 
			// password를 암호화한다!
			pstmt.setString(3, vo.getAddress01());
			pstmt.setString(4, vo.getAddress02());
			pstmt.setInt(5, vo.getZipcode());
			pstmt.setString(6, "Role_USER");
			
			int result = pstmt.executeUpdate();
			
			if(result !=0) {
				flag = true;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		// 알아서 해제하기 때문에 finally는 불필요.
		
		return flag;
	}
	
	// Search 처리 함수(계정 조회)
	public MemberVO MemberSearch(String email) {
		MemberVO vo = null;
		
		try {
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select * from member_tbl where email=?");
			
			pstmt.setString(1, email);
			
			rs = pstmt.executeQuery();
			
			if(rs !=null) {
				while(rs.next()) {
					vo = new MemberVO(rs.getString("email"),
							rs.getString("password"),
							rs.getString("address01"),
							rs.getString("address02"),
							rs.getInt("zipcode"));
					vo.setRole(rs.getString("role"));
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return vo;
	}
	
	
	
	// List
	
	// Update
	
	// Delete
}
