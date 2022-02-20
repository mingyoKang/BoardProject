package controller.notice;

import controller.Controller;
import controller.HttpUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class NoticeListController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		HttpUtil.forward(req, resp, "/WEB-INF/View/notice/list.jsp");
		
	}

}
