package controller.Member;

import VO.MemberVO;
import controller.Controller;
import controller.HttpUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class MemberSearchController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		System.out.println("<Serach Member>");

		// 01.세션에서 접속한 계정 정보(vo)꺼내기
		HttpSession session = req.getSession();
		MemberVO vo = (MemberVO) session.getAttribute("vo");
		
		// 02.페이지 이동(View MVC 중에서 V)- sendRedirect()/getRequestDispather.forward()
		// vo를 지정된 페이지로 전달한다.
		req.setAttribute("vo", vo);
		HttpUtil.forward(req, resp, "/WEB-INF/View/MemberSearchResult.jsp");
	}

}
