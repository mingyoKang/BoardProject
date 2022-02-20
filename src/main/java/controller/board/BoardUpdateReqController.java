package controller.board;

import VO.BoardVO;
import controller.Controller;
import controller.HttpUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class BoardUpdateReqController implements Controller{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// update를 진행하기 전 password를 받아 DB에 저장된 password와 비교할 컨트롤러
		
		// 01.파라미터 받기
		String flag = req.getParameter("flag");
		
		if(flag !=null) { // flag에 값이 있을 때 // 더 정확하게는 init일 때는 처음 시행할 때
			
			HttpUtil.forward(req, resp, "/WEB-INF/View/board/isUpdated.jsp");
			
		}else { // flag에 값이 없을 때(처음이 아니라 password를 확인하려는 때)
			
			String conPassword = req.getParameter("password");
			
			HttpSession session = req.getSession();
			BoardVO vo = (BoardVO) session.getAttribute("BoardVO");
			int number = vo.getNumber();
			
			if(conPassword.equals(vo.getPassword())) { // 비밀번호 일치
				
				// 수정 후 다시 /Board/read.do로 돌아올 때 해당 페이지로 돌아오기 위해서 필요한 것들
				String start = req.getParameter("start");
				String end = req.getParameter("end");
				String nowPage = req.getParameter("nowPage");
				
				String url = "/Board/update.do?flag=init&number=" + number + "&nowPage=" + nowPage + "&start=" + start + "&end=" + end;
				
				HttpUtil.forward(req, resp, url);
				
			}else { // 비밀번호 불일치
				
				req.setAttribute("MSG", "Wrong Password");
				
				String start = req.getParameter("start");
				String end = req.getParameter("end");
				String nowPage = req.getParameter("nowPage");
				
				String url = "/Board/read.do?number=" + number + "&nowPage=" + nowPage + "&start=" +start + "&end=" + end;
				
				HttpUtil.forward(req, resp, url);
					
			}
			
		}
		

		
		
	}

}
