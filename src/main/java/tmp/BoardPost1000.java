package tmp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class BoardPost1000 {

	public static void main(String[] args) {
		
		String url = "jdbc:mysql://localhost:3306/shopdb";
		String id = "root";
		String pw = "0312";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection(url,id,pw);
			
			for(int i=1; i<=1000; i++) {
				
				pstmt = conn.prepareStatement("insert into board_tbl values(null,?,?,?,?,?,?,?,?)");
				
				pstmt.setString(1, "a"+i+"@naver.com");
				pstmt.setString(2, "Title" + i);
				pstmt.setString(3, "Content" + i);
				pstmt.setString(4, "2022-2-06");
				pstmt.setString(5, "127.0.45.1");
				pstmt.setInt(6, 0);
				pstmt.setString(7, "No_Name");
				pstmt.setInt(8, 0);
				
				pstmt.executeUpdate();
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
			
		}
		
		System.out.println("Insert Data to DB Done!");

	}

}
