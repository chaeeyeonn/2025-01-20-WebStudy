package com.sist.music;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.sist.dao.*;
import com.sist.vo.*;

@WebServlet("/MusicFind")
public class MusicFind extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//JSP 파일 코딩
				// 1. 브라우저로 전송 => HTML 전송
						// HTML,XML,JSON => response(HTML,Cookie 전송 가능)
						response.setContentType("text/html;charset=UTF-8");
						// 2. 브라우저 연결
						PrintWriter out=response.getWriter();
						
						// 사용자가 보낸 값 받기
						String strPage=request.getParameter("page");
						// default 값 생성 => 항상 필요!
						if(strPage==null)
							strPage="1";
						int curpage=Integer.parseInt(strPage);
						
						String column=request.getParameter("column");
						String fd=request.getParameter("fd");
						
						// => DAO에서 결과값 받기
						MusicDAO dao=MusicDAO.newInstance();
						List<MusicVO> list=dao.musicFind(curpage, column, fd);
						int totalpage=dao.musicFindTotalPage(column, fd);
						
						final int BLOCK=10;
						int startPage=((curpage-1)/BLOCK*BLOCK)+1;
						int endPage=((curpage+1)/BLOCK*BLOCK)+BLOCK;
						
						if(endPage>totalpage)
							endPage=totalpage;
						
						out.println("<html>");
						out.println("<head>");
						out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
						out.println("<link rel=stylesheet href=css/food.css>");
						out.println("</head>");
						out.println("<body>");
						out.println("<div class=container>");
						out.println("<div class=\"row\">");
						out.println("<form method=post action=MainServlet?mode=4>");
						out.println("<select name=column class=input-sm>");
						out.println("<option value=title>제목</option>");
						out.println("<option value=singer>가수명</option>");
						out.println("<option value=album>앨범명</option>");
						out.println("</select>");
						out.println("<input type=text name=fd size=15 class=input-sm>");
						out.println("<button class=\"btn-sm btn-danger\">검색</button>");
						out.println("</div>");
						out.println("<div class=row style=\"margin-top:20px\">");
						/*
						 *   response는 기능을 한 개만 수행 할 수 있다(두 개 이상 동시 수행 불가)
						 *   1. 쿠키 전송 => Detail 이동 => HTML 전송
						 *   2. HTML 전송
						 */
						if(list!=null)
						{
						for(MusicVO vo:list)
						{
							out.println("<div class=\"col-md-3\">");
							out.println("<div class=\"thumbnail\">");
							out.println("<a href=\"MusicBeforeDetail?mno="+vo.getMno()+"\">");
							out.println("<img src="+vo.getPoster()+" style=\"width:230px;height:200px\">");
							out.println("<div class=\"caption\">");
							out.println("<p>"+vo.getTitle()+"</p>");
							out.println("<p>"+vo.getSinger()+"</p>");
							out.println("</div>");
							out.println("</a>");
							out.println("</div>");
							out.println("</div>");
						}
						}
						out.println("</div>");
						out.println("<div class=\"row text-center\">");
						out.println("<ul class=\"pagination\">");
						if(startPage>1)
						{
						  out.println("<li><a href=\"MainServlet?mode=4&column="+column+"&fd="+(startPage-1)+"\">&lt;</a></li>");// 이전 블록 / 블록 중 마지막 페이지로 이동(이전 startPage-1)
						}
						
						for(int i=startPage;i<=endPage;i++)
						{
						 if(i==curpage)
						  out.println("<li class=active><a href=\"MainServlet?mode=4&column="+column+"&fd="+i+"\">"+i+"</a></li>");// active: 현재 페이지 버튼 색깔 바꾸기
						 else
						  out.println("<li><a href=\"MainServlet?mode=4&column="+column+"&fd="+i+"\">"+i+"</a></li>");
						}
						if(endPage<totalpage)
						{
							out.println("<li><a href=\"MainServlet?mode=4&column="+column+"&fd="+(endPage+1)+"\">&gt;</a></li>");// 다음 블록 / 블록 중 처음 페이지로 이동(이전 endPage+1)
						}
				        out.println("</ul>");
				        
						out.println("</div>");
						out.println("</div>");
						out.println("</body>");
						out.println("</html>");
			
	}

}
