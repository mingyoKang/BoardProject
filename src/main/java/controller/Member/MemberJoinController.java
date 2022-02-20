package controller.Member;

import Service.MemberService;
import VO.MemberVO;
import controller.Controller;
import controller.HttpUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MemberJoinController implements Controller{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		System.out.println("<Join Member>");
		
		// 01.파라미터 받기
		 //System.out.println("01.파라미터 받기");
		
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String address01 = req.getParameter("address01");
		String address02 = req.getParameter("address02");
		String zipcodeS = req.getParameter("zipcode");
		int zipcode = Integer.parseInt(zipcodeS);
		
		System.out.printf("%s %s %s %s %d\n", email,password,address01,address02,zipcode);
			
		// 02.입력값 검증(Front-Javascript로 처리 가능)
		 // System.out.println("02.입력값 검증");
		
		 // optional 코드
		if(email.isEmpty() || password.isEmpty() || address01.isEmpty()
				|| address02.isEmpty() || zipcode==0) {
			req.setAttribute("MSG", "Wrong Information. Try Again");
			HttpUtil.forward(req, resp, "값 미입력시 이동할 페이지 URL");
		}
		
		// 03.서비스 작업
		 // System.out.println("03.서비스 작업");
		
		MemberService memberService = MemberService.getInstance(); // 싱글톤 객체 생성
		MemberVO vo = new MemberVO(email, password, address01, address02, zipcode);
		memberService.MemberJoin(vo);
		
		// 04.페이지 이동(View MVC 중에서 V)- sendRedirect()/getRequestDispather.forward()
		HttpUtil.forward(req, resp, "/WEB-INF/View/MemberJoinResult.jsp");		
	}

}
