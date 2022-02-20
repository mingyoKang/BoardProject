package controller.board;

import Service.BoardService;
import VO.BoardVO;
import VO.MemberVO;
import VO.ReplyVO;
import controller.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class BoardReplyPostController implements Controller{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		String comment = req.getParameter("comment");
		
		HttpSession session = req.getSession();
		
		MemberVO memberVO = (MemberVO) session.getAttribute("vo");
		BoardVO boardVO = (BoardVO) session.getAttribute("BoardVO");
		
		// board_number, writer, comment
		
		ReplyVO replyVO = new ReplyVO(
				boardVO.getNumber(),
				memberVO.getEmail(),
				comment
				);
		
		// 서비스 실행하기
		BoardService boardService = BoardService.getInstance();
		boardService.postReply(replyVO);
	}

}
