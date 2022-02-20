package controller.board;

import Service.BoardService;
import controller.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BoardDownloadController implements Controller{
	// 파일을 다운로드 받을 수 있도록 기능하는 컨트롤러
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		// 03.서비스 실행
		BoardService boardService = BoardService.getInstance();
		boardService.fileDownload(req, resp);
	}

}
