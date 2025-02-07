package com.sist.food;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.sist.dao.*;

@WebServlet("/ReplyUpdate")
public class ReplyUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fno=request.getParameter("fno");
		String rno=request.getParameter("rno");
		String msg=request.getParameter("msg");
		// 한글
		ReplyDAO dao=ReplyDAO.newInstance();
		// 수정 요청
		dao.replyUpdate(Integer.parseInt(rno), msg);
		// 화면 이동
		response.sendRedirect("MainServlet?mode=2&fno="+fno);
	}

}