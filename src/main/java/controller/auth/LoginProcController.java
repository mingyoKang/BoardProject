package controller.auth;

import org.mindrot.jbcrypt.BCrypt;

import Service.MemberService;
import VO.MemberVO;
import controller.Controller;
import controller.HttpUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginProcController implements Controller{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		// 01.파라미터 받기(ID/PW)
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		// 02.입력값 검증(password 정책 규율을 지켜서) -> ID/PW 미입력시 처리 방안 마련
		if(email.isEmpty() || password.isEmpty()) {
			req.setAttribute("MSG", "You forgot to put ID or PW");
			HttpUtil.forward(req, resp, "Login.jsp"); // 값 미입력 시 이동할 페이지
		}
		
		// 03.받은 계정 vs DB에 있는 계정 비교
		MemberService memberService = MemberService.getInstance();
		MemberVO vo = memberService.MemberSearch(email);
		
		if(vo == null) { // 계정이 조회가 안되는 경우 -> 계정이 없는 경우
			
			req.setAttribute("MSG", "Your Email is not in our DATA");
			HttpUtil.forward(req, resp, "Login.jsp");
		
		}else { // 계정이 조회된 경우
			
			if(BCrypt.checkpw(password, vo.getPassword())) {
				// ID/PW가 모두 일치하는지 판별
				
				// 일치한다면 세션 객체에 ID/PW 저장
				HttpSession session = req.getSession(); // Session 객체 하나 얻기
				session.setAttribute("vo", vo); // Server에 두 요소를 저장
				
				// 해당하는 페이지로 이동
				HttpUtil.forward(req, resp, "/WEB-INF/View/UserMainPage.jsp");
			}else {
				// PW가 일치하지 않는 경우
				req.setAttribute("MSG", "Wrong password");
				HttpUtil.forward(req, resp, "Login.jsp");
			}
			
			
		}

		
	}
}
