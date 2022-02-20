package controller.board;

import Service.BoardService;
import controller.Controller;
import controller.HttpUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BoardWriteController implements Controller{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		// 01.파라미터 받기
		String flag = req.getParameter("flag");
		
		if(flag.equals("true")) { // 처음 접근했을 때
			
			HttpUtil.forward(req, resp, "/WEB-INF/View/board/write.jsp");
			
		}else { // form에 입력한 후 post 처리 요청을 했을 때
			
			
			BoardService boardService = BoardService.getInstance();
			boardService.boardWrite(req);
			
			try {
				resp.sendRedirect("/Board/list.do");
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
		
	}

}
