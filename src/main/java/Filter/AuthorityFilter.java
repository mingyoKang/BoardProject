package Filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import VO.MemberVO;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class AuthorityFilter implements Filter{
	
	// 0: ANON, 1: USER, 2: ADMIN -> Role에 따른 각각의 권한을 부여
	// post에 ADMIN만 접근할 수 있도록 기능
	
	// role 저장 공간
	Map<String, Integer> roleMap = new HashMap();
	

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		// Notice 관련 페이지 권한
		roleMap.put("/Notice/list.do", 0); // 모든 계정이 접근 가능
		roleMap.put("/Notice/post.do", 2); // ADMIN 계정만 접근 가능
		
		// Board 관련 페이지 권한
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		// req에 있는 session 꺼내기
		HttpServletRequest request = (HttpServletRequest) req; // 다운캐스팅
		HttpSession session = request.getSession();
		
		MemberVO vo = (MemberVO) session.getAttribute("vo");
		
		if(vo !=null) {
			
			String tmp = vo.getRole();
			
			int myRole = 0; // 기본값 지정(ANON)
			
			if(tmp.equals("Role_ADMIN")) {
				
				myRole = 2; // ADMIN 값 부여
				
			}else if(tmp.equals("Role_USER")) {
				
				myRole = 1;
				
			}else {
				
				myRole = 0;
				
			}
			
			// 현재 읽고 있는 URL을 가져오기
			// 값을 비교해 권한 범위를 조정
			
			String url = request.getRequestURI();
			
			int pageRole = 0; // myRole과 비교할 변수
			
			if(roleMap.get(url) == null) {
				
				pageRole = 0; 
				// 기본 값을 0으로 해야 Notice/Post가 아닌 공간에 오류가 없다.
				
			}else {
				
				pageRole = roleMap.get(url);
				
			}
			
			// 권한에 따른 접근 금지처리하기
			
			if(pageRole>=1 && myRole == 0) {
				
				throw new ServletException("Anonymous Id is not allowed here");
				
			}else if(pageRole==2 && myRole<2) {
				
				throw new ServletException("Administrator Only");
				
			}
			
		}
		
		
		System.out.println("=====Filter Start=====");
		chain.doFilter(req, resp);
		System.out.println("=====Filter End=====");
		
	}

}
