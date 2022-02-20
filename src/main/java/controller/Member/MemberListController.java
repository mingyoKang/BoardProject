package controller.Member;

import controller.Controller;
import controller.HttpUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MemberListController implements Controller{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		System.out.println("<Member List>");

		
		// 01.파라미터 받기
		System.out.println("01.파라미터 받기");
		// 02.입력값 검증(Front-Javascript로 처리 가능)
		System.out.println("02.입력값 검증");
		// 03.서비스 작업
		System.out.println("03.서비스 작업");
		// 04.페이지 이동(View MVC 중에서 V)- sendRedirect()/getRequestDispather.forward()
		HttpUtil.forward(req, resp, "/WEB-INF/View/MemberListResult.jsp");
	}

}
