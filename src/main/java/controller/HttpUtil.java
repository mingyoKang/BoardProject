package controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HttpUtil {
	// 연결 기능 관리 클래스
	
	// 각각이 해당되는 페이지로 잘 이동할 수 있도록 공통적인 기능을 생성
	public static void forward(HttpServletRequest req, HttpServletResponse resp, String url) {
		// static: 객체 생성과는 무관하게 클래스 이름으로 바로 접근 가능
		// getRequestDispatcher로 해당 페이지 연결
		
		try {
			
			req.getRequestDispatcher(url).forward(req, resp);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
