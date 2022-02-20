package controller.board;

import Service.BoardService;
import VO.BoardVO;
import controller.Controller;
import controller.HttpUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class BoardDeleteController implements Controller{

	// BoardDeleteReqController에서 password 검사를 진행한 후
	// BoardDeleteController에서 실질적으로 업데이티를 실행하도록 설정
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
				
			// 01.파라미터 받기

			
			// 02.입력값 검증하기

			
			// 03.서비스 실행하기
			HttpSession session = req.getSession();
			BoardVO boardVO = (BoardVO) session.getAttribute("BoardVO");
			int number = boardVO.getNumber();
			
			BoardService boardService = BoardService.getInstance();
			boardService.boardDelete(number);
				
			// 04.페이지 이동
			try {
				
				String nowPage = req.getParameter("nowPage");
				String start = req.getParameter("start");
				String end = req.getParameter("end");
				
				String url = "/Board/list.do?nowPage=" + nowPage + "&start=" + start + "&end=" + end + "&number=" + number;
				
				resp.sendRedirect(url);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		
		
		
	}

}
