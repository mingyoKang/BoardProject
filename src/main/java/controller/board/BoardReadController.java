package controller.board;

import Service.BoardService;
import VO.BoardVO;
import controller.Controller;
import controller.HttpUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class BoardReadController implements Controller{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		// 01.파라미터 받기
		 // form에서 정보 받기
		int number = Integer.parseInt(req.getParameter("number"));
		String nowPage = req.getParameter("nowPage");
		
		int start = Integer.parseInt(req.getParameter("start"));
		int end = Integer.parseInt(req.getParameter("end"));
		
		// 02.입력값 검증
		
		// 03.서비스 실행
		BoardService boardService = BoardService.getInstance();
		BoardVO vo = boardService.getBoardVO(number);
		
		// 04.페이지 이동
		
		HttpSession session = req.getSession(); // 세션 객체 하나 받기
		session.setAttribute("BoardVO", vo); // BoardVO이름으로 vo 저장해서 보내기
		
		req.setAttribute("nowPage", nowPage);
		req.setAttribute("start", start);
		req.setAttribute("end", end);
		
		HttpUtil.forward(req, resp, "/WEB-INF/View/board/read.jsp");
	}

}
