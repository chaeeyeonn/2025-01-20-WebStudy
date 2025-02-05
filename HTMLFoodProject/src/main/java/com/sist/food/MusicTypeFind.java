package com.sist.food;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

@WebServlet("/MusicTypeFind")
public class MusicTypeFind extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		String strPage=request.getParameter("page");
		// default 값 생성 => 항상 필요!
		if(strPage==null)
			strPage="1";
		int curpage=Integer.parseInt(strPage);
		
		String cno=request.getParameter("cno");
		if(cno==null)
			cno="2";
		
		MusicDAO dao=MusicDAO.newInstance();
		List<MusicVO> list=dao.musicTypeFind(curpage, Integer.parseInt(cno));
		int totalpage=dao.musicTypeTotalPage(Integer.parseInt(cno));
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage+1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalpage)
			endPage=totalpage;
		
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
		out.println("<link rel=stylesheet href=css/food.css>");// css 폴더 속 food.css 파일 적용
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=container>");
		out.println("<div class=\"row text-center\">");
		out.println("<a href=MusicTypeFind?cno=1 class=\"btn btn-sm btn-danger\">가요<a>");
		out.println("<a href=MusicTypeFind?cno=2 class=\"btn btn-sm btn-success\">Top50<a>");
		out.println("<a href=MusicTypeFind?cno=3 class=\"btn btn-sm btn-info\">POP<a>");
		out.println("<a href=MusicTypeFind?cno=4 class=\"btn btn-sm btn-primary\">OST<a>");
		out.println("<a href=MusicTypeFind?cno=5 class=\"btn btn-sm btn-warning\">트롯<a>");
		out.println("<a href=MusicTypeFind?cno=6 class=\"btn btn-sm btn-default\">JAZZ<a>");
		out.println("<a href=MusicTypeFind?cno=7 class=\"btn btn-sm btn-default\">Classic<a>");
		out.println("</div>");
		out.println("<div class=row style=\"margin-top:20px\">");
		
		for(MusicVO vo:list)
		{
			out.println("<div class=\"col-md-3\">");
			out.println("<div class=\"thumbnail\">");
			out.println("<a href=\"MusicBeforeDetail?mno="+vo.getMno()+"\">");
			out.println("<img src="+vo.getPoster()+" style=\"width:230px;height:200px\">");
			out.println("<div class=\"caption\">");
			out.println("<p>"+vo.getTitle()+"</p>");
			out.println("</div>");
			out.println("</a>");
			out.println("</div>");
			out.println("</div>");
		}
		out.println("</div>");
		out.println("<div class=\"row text-center\">");
		out.println("<ul class=\"pagination\">");
		if(startPage>1)
		{
		  out.println("<li><a href=\"MusicTypeFind?cno="+cno+"&page="+(startPage-1)+"\">&lt;</a></li>");// 이전 블록 / 블록 중 마지막 페이지로 이동(이전 startPage-1)
		}
		
		for(int i=startPage;i<=endPage;i++)
		{
		 if(i==curpage)
		  out.println("<li class=active><a href=\"MusicTypeFind?cno="+cno+"&page="+i+"\">"+i+"</a></li>");// active: 현재 페이지 버튼 색깔 바꾸기
		 else
		  out.println("<li><a href=\"MusicTypeFind?cno="+cno+"&page="+i+"\">"+i+"</a></li>");
		}
		if(endPage<totalpage)
		{
			out.println("<li><a href=\"MusicTypeFind?cno="+cno+"&page="+(endPage+1)+"\">&gt;</a></li>");// 다음 블록 / 블록 중 처음 페이지로 이동(이전 endPage+1)
		}
        out.println("</ul>");
        
		out.println("</div>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}

}
