package controller.board;

import java.util.Vector;

import Service.BoardService;
import VO.BoardVO;
import controller.Controller;
import controller.HttpUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BoardListController implements Controller{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		// 01.파라미터 받아오기(게시물 시작위치, 읽어들일 행 개수)
		int start = 0;
		int end = 10; // 행 10개를 읽어들일 것
		int nowPage = 1;
		
		// 01.1)form에서부터 받아온 파라미터 값들
		String formStart = req.getParameter("start");
		String formEnd = req.getParameter("end");
		String formNowPage = req.getParameter("nowPage");
		
		// 02.인증값 검증
		if(formNowPage !=null) {
			
			nowPage = Integer.parseInt(formNowPage);
			start = Integer.parseInt(formStart);
			end = Integer.parseInt(formEnd);
			
		}
		
		// 03.서비스 실행
		BoardService boardService = BoardService.getInstance();
		Vector<BoardVO> list = boardService.getBoardList(start, end);
		int totalCount = boardService.getTotalCount();
		
		// 04.해당 페이지로 이동
		 // list 이름으로 값을 실어 보낸다.
		req.setAttribute("list", list);
		req.setAttribute("totalCount", totalCount);
		HttpUtil.forward(req, resp, "/WEB-INF/View/board/list.jsp?nowPage="+ nowPage);
		
	}

}
