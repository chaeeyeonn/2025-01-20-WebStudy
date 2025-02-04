package com.sist.food;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import java.io.*;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

@WebServlet("/FoodList")
public class FoodList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 브라우저로 전송 => HTML 전송
		// HTML,XML,JSON => response(HTML,Cookie 전송 가능)
		response.setContentType("text/html;charset=UTF-8");
		// 2. 브라우저 연결
		PrintWriter out=response.getWriter();
		// 3. 출력 전에 오라클 데이터 읽기
		FoodDAO dao=FoodDAO.newInstance();
		// 4. 사용자 요청 데이터 받기
		String page=request.getParameter("page");
		if(page==null)
			 page="1";// 초기값 지정 => 사용자가 page를 지정하지 않았다면
		// 현재 페이지 설정
		int curpage=Integer.parseInt(page);
		// 현재 페이지에 대한 데이터
		List<FoodVO> list=dao.foodListData(curpage);
		// 총페이지 읽기
		int totalpage=dao.foodTotalPage();
		
		// 블록별 페이지
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		// 만약 totalpage보다 endpage가 클 경우
		if(endPage>totalpage)
			endPage=totalpage;
		
		// https://www.w3schools.com/ 활용
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
		out.println("<link rel=stylesheet href=css/food.css>");// css 폴더 속 food.css 파일 적용
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=container>");
		out.println("<div class=row>");
		out.println("<tr>");
		out.println("<td>");
		out.println("<a href=MainServlet class=\"btn btn-xs btn-primary\">메인</a>");
		out.println("<a href=MusicList class=\"btn btn-xs btn-warning\">음악</a>");
		out.println("</td>");
		out.println("</tr>");
		out.println("<h1>맛집 목록</h1>");
		/*
		 *   response는 기능을 한 개만 수행 할 수 있다(두 개 이상 동시 수행 불가)
		 *   1. 쿠키 전송 => Detail 이동 => HTML 전송
		 *   2. HTML 전송
		 */
		for(FoodVO vo:list)
		{
			out.println("<div class=\"col-md-3\">");
			out.println("<div class=\"thumbnail\">");
			out.println("<a href=\"FoodBeforeDetail?fno="+vo.getFno()+"\">");
			out.println("<img src="+vo.getPoster()+" style=\"width:230px;height:200px\">");
			out.println("<div class=\"caption\">");
			out.println("<p>"+vo.getName()+"</p>");
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
		  out.println("<li><a href=\"FoodList?page="+(startPage-1)+"\">&lt;</a></li>");// 이전 블록 / 블록 중 마지막 페이지로 이동(이전 startPage-1)
		}
		
		for(int i=startPage;i<=endPage;i++)
		{
		 if(i==curpage)
		  out.println("<li class=active><a href=\"FoodList?page="+i+"\">"+i+"</a></li>");// active: 현재 페이지 버튼 색깔 바꾸기
		 else
		  out.println("<li><a href=\"FoodList?page="+i+"\">"+i+"</a></li>");
		}
		if(endPage<totalpage)
		{
			out.println("<li><a href=\"FoodList?page="+(endPage+1)+"\">&gt;</a></li>");// 다음 블록 / 블록 중 처음 페이지로 이동(이전 endPage+1)
		}
        out.println("</ul>");
		out.println("</div>");
		out.println("<div class=row>");
		out.println("<h3>최신 방문 맛집</h3>");
		out.println("<hr>");
		List<FoodVO> cList=new ArrayList<FoodVO>();
		Cookie[] cookies=request.getCookies();
		if(cookies!=null)
		{
		  for(int i=cookies.length-1;i>=0;i--)// 최신순을 위해 반대로 진행
		  {
			  if(cookies[i].getName().startsWith("food_"))
			  {
				  String fno=cookies[i].getValue();
				  FoodVO vo=dao.foodCookieData(Integer.parseInt(fno));
				  cList.add(vo);
			  }
		  }
		}
		for(int i=0;i<cList.size();i++)
		{
			FoodVO cvo=cList.get(i);
			if(i>8) break;
			out.println("<a href=FoodDetail?fno="+cvo.getFno()+">");
			out.println("<img src="+cvo.getPoster()+" style=\"width:100px;height:100px\" class=img-rounded title="+"'"+cvo.getName()+"'"+">");
			out.println("</a>");
		}
		out.println("</div>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}

}
