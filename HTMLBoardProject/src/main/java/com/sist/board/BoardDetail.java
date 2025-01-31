package com.sist.board;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.sist.dao.*;
import com.sist.vo.*;

@WebServlet("/BoardDetail")
public class BoardDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 브라우저에 전송 방식
		response.setContentType("text/html;charset=UTF-8");
		// XML: text/xml, JSON: text/plain
		// 2. html을 읽어 갈 브라우저 정보 확인
		PrintWriter out=response.getWriter();
		// 3. 사용자가 보낸 데이터 받기
		String no=request.getParameter("no");
		// 웹은 전송된 모든 데이터가 String 형태
		// 4. 데이터베이스 연동
		BoardDAO dao=BoardDAO.newInstance();
		// 5. 데이터 읽기 
		BoardVO vo=dao.boardDetailData(Integer.parseInt(no));
		// 6. html을 이용해 화면에 데이터 출력
	    out.println("<html>");
		out.println("<head>");
		out.println("<link rel=stylesheet href=css/table.css>");
		out.println("</head>");
		out.println("<body>");
		out.println("<center>");
		out.println("<h1>내용보기</h1>");
		out.println("<table class=table_content width=600>");
		out.println("<tr>");
		out.println("<th width=20%>번호</th>");
		out.println("<td width=30% align=center>"+vo.getNo()+"</td>");
		out.println("<th width=20%>작성일</th>");
		out.println("<td width=30% align=center>"+vo.getDbday()+"</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<th width=20%>이름</th>");
		out.println("<td width=30% align=center>"+vo.getName()+"</td>");
		out.println("<th width=20%>조회수</th>");
		out.println("<td width=30% align=center>"+vo.getHit()+"</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<th width=20%>제목</th>");
		out.println("<td colspan=3>"+vo.getSubject()+"</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td colspan=4 valign=top align=left height=200>");
		out.println("<pre style=\"white-space:pre-wrap\">"+vo.getContent()+"</pre>");
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td colspan=4 align=right>");
		out.println("<a href=#>수정</a>");
		// 해당 servlet에 데이터 전송 => ? 표기 => primary key 넘기기
		out.println("<a href=BoardDelete?no="+vo.getNo()+">삭제</a>");
		out.println("<a href=BoardList>목록</a>");
		out.println("</td>");
		out.println("</tr>");
		
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
	}

}
