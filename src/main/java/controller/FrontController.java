package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import controller.Member.MemberDeleteController;
import controller.Member.MemberJoinController;
import controller.Member.MemberListController;
import controller.Member.MemberSearchController;
import controller.Member.MemberUpdateController;
import controller.auth.LoginProcController;
import controller.auth.LogoutProcController;
import controller.board.BoardDeleteController;
import controller.board.BoardDeleteReqController;
import controller.board.BoardDownloadController;
import controller.board.BoardListController;
import controller.board.BoardReadController;
import controller.board.BoardReplyListController;
import controller.board.BoardReplyPostController;
import controller.board.BoardUpdateController;
import controller.board.BoardUpdateReqController;
import controller.board.BoardWriteController;
import controller.home.HomeForwardingController;
import controller.notice.NoticeListController;
import controller.notice.NoticePostController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@MultipartConfig(
		fileSizeThreshold = 1024*1024*10, // 10MB
		maxFileSize = 1024*1024*50,
		maxRequestSize = 1024*1024*100
		)

// web.xml에 URL-Mapping을 할 것이기에 annotation은 불필요
public class FrontController extends HttpServlet {
	// 모든 URL 주소를 받아낼 클래스
	
	// URL 저장소
	Map<String, Controller> list = null;
	
	@Override
	public void init() throws ServletException {
		// 처음 시작할 때 작동하는 메소드로 초반에 한 번 작업해 둘 것을 여기에 하면된다.
		// System.out.println("init() 메소드 작동!");
		
		list = new HashMap();
		
		// Member 관련 URL
		
		list.put("/MemberJoin.do", new MemberJoinController());
		list.put("/MemberList.do", new MemberListController());
		list.put("/MemberSearch.do", new MemberSearchController());
		list.put("/MemberUpdate.do", new MemberUpdateController());
		list.put("/MemberDelete.do", new MemberDeleteController());
		
		// Home 관련 URL
		list.put("/Home.do", new HomeForwardingController());
		
		// Notice 관련 URL
		list.put("/Notice/list.do", new NoticeListController());
		list.put("/Notice/post.do", new NoticePostController());
		
		// Board 관련 URL
		list.put("/Board/list.do", new BoardListController());
		list.put("/Board/write.do", new BoardWriteController());
		list.put("/Board/read.do", new BoardReadController());
		
		list.put("/Board/update.do", new BoardUpdateController());
		list.put("/Board/updateRequest.do", new BoardUpdateReqController());
		
		list.put("/Board/delete.do", new BoardDeleteController());
		list.put("/Board/deleteRequest.do", new BoardDeleteReqController());
		
		list.put("/Board/download.do", new BoardDownloadController());
		
		list.put("/Board/replypost.do", new BoardReplyPostController());
		list.put("/Board/replylist.do", new BoardReplyListController());
		
		// Auth 관련 URL
		list.put("/LoginProc.do", new LoginProcController());
		list.put("/LogoutProc.do", new LogoutProcController());
	}	

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 새로 고침할 때마다 다시금 작동하는 메소드로, 여러번 반복 가능한 작업을 진행하면된다.
		// System.out.println("service() 메소드 작동!");
		
		String url = req.getRequestURI(); // url 주소 얻기
		System.out.println("URL: " + url);
		System.out.println("----------");
		
		// Controller 꺼내기
		
		Controller subController = list.get(url);
		
		// subController에서 execute 함수 실행
		subController.execute(req, resp);
		
	}


	
	
	
}
