package com.sist.board;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.sist.dao.BoardDAO;
/*
 *   2개 => 폼 제작 / 요청 처리
 *         ------   -------
 *         get      post
 *            수정/삭제
 *         => JSP는 GET과 POST가 나눠져있지 않다
 *            delete.jsp/delete_ok.jsp  
 */
@WebServlet("/BoardDelete")
public class BoardDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
    // 삭제 폼 제작
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 브라우저에 전송 방식
		response.setContentType("text/html;charset=UTF-8");
		// XML: text/xml, JSON: text/plain
		// 2. html을 읽어 갈 브라우저 정보 확인
		PrintWriter out=response.getWriter();
		// 3. 사용자가 보낸 데이터 받기
		String no=request.getParameter("no");
		// 웹은 전송된 모든 데이터가 String 형태
		// 삭제 화면
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=stylesheet href=css/table.css>");
		out.println("</head>");
		out.println("<body>");
		out.println("<center>");
		out.println("<h1>삭제하기</h1>");
		// BoardDelete의 doPost() 호출 => action: 호출 대상
		// 기본: doGet() <a>태그
		// doPost()는 같이 호출하지 않으니 따로 호출 필요
		out.println("<form method=post action=BoardDelete>");
		out.println("<table class=table_content width=350>");
		out.println("<tr>");
		out.println("<td>");
		out.println("비밀번호:<input type=password size=20 name=pwd required>");
		out.println("<input type=hidden name=no value="+no+">");
		// 출력하면 안 된다 => 화면 감추기 => 데이터는 전송
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td align=center>");
		out.println("<input type=submit value=삭제>");
		out.println("<input type=button value=취소 onclick=javascript:history.back()>");
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
		
	}
    // 삭제 관련 요청 처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 삭제 처리 => 비밀번호 처리 => JAvaScript => alert
		// 1. 브라우저에 전송 방식
	    response.setContentType("text/html;charset=UTF-8");
		// XML: text/xml, JSON: text/plain
		// 2. html을 읽어 갈 브라우저 정보 확인
		PrintWriter out=response.getWriter();
		// 3. 사용자가 보낸 데이터 받기
		String no=request.getParameter("no");
		String pwd=request.getParameter("pwd");
		
		// 오라클 연동
		BoardDAO dao=BoardDAO.newInstance();
		boolean bCheck=dao.boardDelete(Integer.parseInt(no), pwd);
		// 화면 이동
		// 1. 비밀번호가 틀린 경우 => alert
		if(bCheck==false)
		{
			out.println("<script>");
			out.println("alert(\"비밀번호가 틀립니다\");");
			out.println("history.back();");
			out.println("</script>");
		}
		// 2. 게시판 목록으로 이동
		else
		{
			response.sendRedirect("BoardList");
		}
	}

}
