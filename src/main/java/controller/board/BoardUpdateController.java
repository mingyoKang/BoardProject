package controller.board;

import Service.BoardService;
import VO.BoardVO;
import controller.Controller;
import controller.HttpUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class BoardUpdateController implements Controller{

	// BoardUpdateReqController에서 password 검사를 진행한 후
	// BoardUpdateController에서 실질적으로 업데이티를 실행하도록 설정
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		// 01.파라미터 받기
		String flag = req.getParameter("flag");
		
		if(flag !=null) { // 처음으로 페이지를 실행할 때 // update.jsp form 띄우기
			
			HttpUtil.forward(req, resp, "/WEB-INF/View/board/update.jsp");
			
		}else { // update를 실행할 때
			
			// 01.파라미터 받기
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			
			// 02.입력값 검증하기
			if(title.isEmpty() || content.isEmpty()) {
				req.setAttribute("MSG", "Input Data");
				
				HttpUtil.forward(req, resp, "/Board/update.do");
			}
			
			// 03.서비스 실행하기
			HttpSession session = req.getSession();
			BoardVO boardVO = (BoardVO) session.getAttribute("BoardVO");
			int number = boardVO.getNumber();
			
			BoardService boardService = BoardService.getInstance();
			BoardVO vo = new BoardVO(number, title, content);
			
			boardService.boardUpdate(vo);
			
			// 04.페이지 이동
			try {
				
				String nowPage = req.getParameter("nowPage");
				String start = req.getParameter("start");
				String end = req.getParameter("end");
				
				String url = "/Board/read.do?nowPage=" + nowPage + "&start=" + start + "&end=" + end + "&number=" + number;
				
				resp.sendRedirect(url);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}

}
