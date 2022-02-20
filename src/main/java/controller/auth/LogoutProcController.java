package controller.auth;

import controller.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LogoutProcController implements Controller{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		// 현재 접속중인 계정의 섹션 객체를 꺼내고
		HttpSession session = req.getSession();
		
		// 세션을 해제시킨다
		session.invalidate();
		
		// 페이지 이동
		
		try {
			
			resp.sendRedirect("Login.jsp");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
