package controller.board;

import java.io.PrintWriter;
import java.util.Vector;

import Service.BoardService;
import VO.BoardVO;
import VO.ReplyVO;
import controller.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class BoardReplyListController implements Controller{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		// 파라미터 받기(게시물 번호 받기)
		HttpSession session = req.getSession();
		BoardVO boardVO = (BoardVO) session.getAttribute("BoardVO");
		int number = boardVO.getNumber(); // 게시물 번호
		
		// 서비스 실행하기
		// reply_tbl에 게시물 번호에 해당하는 행들을 가지고 오기
		BoardService boardService = BoardService.getInstance();
		Vector<ReplyVO> list = boardService.getReplyList(number);
		System.out.println("List Size: " + list.size());
		
		// 페이지를 이동할 필요없다.
		// Ajax가 html 코드를 처리할 것이기에 html 코드를 여기 작성하면된다.
		
		try {
			
			PrintWriter out = resp.getWriter();
			
			for(int i=0; i<list.size(); i++) {
				
				out.print("<div class = \"row m-2\" style = \"background-color:white;\">");
				out.print("<div class = \"col-1 m-1\">");
				out.print("<img src=\"/Resources/img/chat-dots.svg\" style = \"width:50px; height:50px;\">");
				out.print("</div>");
				out.print("<div class = \"col-10 m-1\">");
				out.print("<div style = \"font-size:16px; font-weight:bold; color:green;\">" + list.get(i).getWriter() + "</div>");
				out.print("<div>" + list.get(i).getComment() + "</div>");
				out.print("</div>");
				out.print("</div>");
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
		
}
