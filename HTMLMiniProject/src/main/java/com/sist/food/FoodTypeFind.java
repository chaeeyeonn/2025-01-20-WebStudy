package com.sist.food;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import com.sist.dao.*;
import com.sist.vo.*;
/*
 *   1. 사용자의 전송 방식
 *     - GET / POST
 *     GET: <a>   sendRedirect() => URL?데이터 전송
 *     POST: <form>, ajax, axios.post()
 *     
 *     검색어 입력 / 찾는 컬럼 => 두 개를 동시 전송
 *     ---------------------------------==> <form> => POST
 *     처음 출력 => GET
 *     
 *     Get => doGet() => @GetMapping()
 *     Post => doPost() => @PostMapping()
 *     GET/POST => service() => @RequestMapping() (Spring 6.0: 권장) => Get/Post 구분위해 속도가 느려지므로 막 쓰지 말 것
 *     
 *     JSP는 doGet/doPost가 없다 => _jspService() 사용
 *     
 */
@WebServlet("/FoodTypeFind")
public class FoodTypeFind extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		String type=request.getParameter("type");
		if(type==null)
			type="한식";
		
		// => DAO에서 결과값 받기
		FoodDAO dao=FoodDAO.newInstance();
		List<FoodVO> list=dao.foodTypeFind(curpage, type);
		int totalpage=dao.foodTypeTotalPage(type);
		
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
		out.println("<a href=MainServlet?mode=3&type=한식 class=\"btn btn-sm btn-danger\">한식<a>");
		out.println("<a href=MainServlet?mode=3&type=양식 class=\"btn btn-sm btn-success\">양식<a>");
		out.println("<a href=MainServlet?mode=3&type=중식 class=\"btn btn-sm btn-info\">중식<a>");
		out.println("<a href=MainServlet?mode=3&type=일식 class=\"btn btn-sm btn-primary\">일식<a>");
		out.println("<a href=MainServlet?mode=3&type=카페 class=\"btn btn-sm btn-warning\">카페<a>");
		out.println("<a href=MainServlet?mode=3&type=기타 class=\"btn btn-sm btn-default\">기타<a>");
		out.println("</div>");
		out.println("<div class=row style=\"margin-top:20px\">");
		/*
		out.println("<tr>");
		out.println("<td>");
		out.println("<a href=MainServlet class=\"btn btn-xs btn-primary\">메인</a>");
		out.println("<a href=MusicList class=\"btn btn-xs btn-warning\">음악</a>");
		out.println("</td>");
		out.println("</tr>");
		out.println("<h1>맛집 목록</h1>");
		*/
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
		  out.println("<li><a href=\"FoodTypeFind?type="+type+"&page="+(startPage-1)+"\">&lt;</a></li>");// 이전 블록 / 블록 중 마지막 페이지로 이동(이전 startPage-1)
		}
		
		for(int i=startPage;i<=endPage;i++)
		{
		 if(i==curpage)
		  out.println("<li class=active><a href=\"MainServlet?mode=3&type="+type+"&page="+i+"\">"+i+"</a></li>");// active: 현재 페이지 버튼 색깔 바꾸기
		 else
		  out.println("<li><a href=\"MainServlet?mode=3&type="+type+"&page="+i+"\">"+i+"</a></li>");
		}
		if(endPage<totalpage)
		{
			out.println("<li><a href=\"MainServlet?mode=3&type="+type+"&page="+(endPage+1)+"\">&gt;</a></li>");// 다음 블록 / 블록 중 처음 페이지로 이동(이전 endPage+1)
		}
        out.println("</ul>");
        
		out.println("</div>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}
// http://localhost:8080/HTMLFoodProject/FoodTypeFind?type=%EC%96%91%EC%8B%9D => window10부터는 GET방식에서 자동 decoding이 됨
}
