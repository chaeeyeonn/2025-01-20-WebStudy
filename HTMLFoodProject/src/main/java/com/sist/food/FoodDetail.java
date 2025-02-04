package com.sist.food;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import com.sist.dao.*;
import com.sist.vo.*;

@WebServlet("/FoodDetail")
public class FoodDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		// 브라우저로 전송
		PrintWriter out=response.getWriter();
		/*
		 *   request
		 *   => 클라이언트의 정보(ip,port)
		 *   => 사용자 요청 정보(사용자가 보낸 값)
		 *     => String getParameter() => 단일값
		 *     => String[] getParameterValues() => 다중값
		 *      => checkbox
		 *     => encoding(byte[]) 전송 => decoding(원상 복귀)
		 *      => setCharacterEncoding("UTF-8");
		 *      
		 *   response
		 *   => 서버에서 전송 정보(HTML,Cookie) => setContentType(), addCookie()
		 *   => 화면 이동 정보 => sendRedirect()
		 */
		// 1. 사용자가 보낸 데이터 받기
		String fno=request.getParameter("fno");
		// 2. 데이터베이스 연결
		FoodDAO dao=FoodDAO.newInstance();
		FoodVO vo=dao.foodDetailData(Integer.parseInt(fno));
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
		out.println("<link rel=stylesheet href=css/food.css>");// css 폴더 속 food.css 파일 적용
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=container>");
		out.println("<div class=row>");
		//서브 이미지 출력
		out.println("<table class=table>");
		out.println("<tr>");
		StringTokenizer st=new StringTokenizer(vo.getImages(),",");
		while(st.hasMoreTokens())
		{
			out.println("<td class=text-center>");
			out.println("<img src=https://www.menupan.com"+st.nextToken()+" style=\"width:100%\">");
			out.println("</td>");
		}
		out.println("</tr>");
		out.println("</table>");
		
		out.println("<table class=table>");
		out.println("<tr>");
		out.println("<td width=30% class=text-center rowspan=8>");
		out.println("<img src="+vo.getPoster()+" style=\"width:270px;height:300px\">"); // 인라인 style
		out.println("</td>");
		out.println("<td colspan=2>");
		out.println("<h3>"+vo.getName()+"&nbsp;<span style=\"color:orange\">"+vo.getScore()+"</span></h3>");
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width=10% style=\"color:gray\">업종</td>");
		out.println("<td width=60%>"+vo.getType()+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width=10% style=\"color:gray\">주소</td>");
		out.println("<td width=60%>"+vo.getAddress()+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width=10% style=\"color:gray\">전화</td>");
		out.println("<td width=60%>"+vo.getPhone()+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width=10% style=\"color:gray\">영업시간</td>");
		out.println("<td width=60%>"+vo.getTime()+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width=10% style=\"color:gray\">주차</td>");
		out.println("<td width=60%>"+vo.getParking()+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width=10% style=\"color:gray\">가격대</td>");
		out.println("<td width=60%>"+vo.getPrice()+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width=10% style=\"color:gray\">테마</td>");
		out.println("<td width=60%>"+vo.getThem()+"</td>");
		out.println("</tr>");
		
		out.println("</table>");
		
		out.println("<table class=table>");
		out.println("<tr>");
		out.println("<td>");
		out.println(vo.getContent());
		out.println("</td>");
		out.println("</tr>");
		
		//https://www.w3schools.com/bootstrap/bootstrap_tables.asp 참고
		out.println("<tr>");
		out.println("<td class=text-right>");
		out.println("<a href=# class=\"btn btn-xs btn-danger\">좋아요</a>");//버튼 크기: xs => 제일 작음 / danger: 빨간색
		out.println("<a href=# class=\"btn btn-xs btn-success\">찜하기</a>");//success: 초록
		out.println("<a href=# class=\"btn btn-xs btn-info\">예약</a>");//info: 하늘
		out.println("<a href=FoodList class=\"btn btn-xs btn-primary\">목록</a>");//primary: 파란색
		out.println("<a href=MainServlet class=\"btn btn-xs btn-primary\">메인</a>");
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		
		out.println("</div>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}

}
