package com.sist.food;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/FoodBeforeDetail")
public class FoodBeforeDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 쿠키는 HTML 필요 x => content 타입 필요x
		String fno=request.getParameter("fno");
		Cookie cookie=new Cookie("food_"+fno, fno);
		cookie.setPath("/");// 저장 공간
		cookie.setMaxAge(60*60*24);// 24시간 표기 => 하루동안 저장하겠다
		response.addCookie(cookie);// 쿠키 전송
		// 쿠키 => 브라우저에 저장(클라이언트에 저장)
		// 보안에 취약 / 저장 -> 문자열만 저장 가능
		// 서버에 저장(세션)
		// => MAP 방식(키, 값) => 키는 중복 불가능
		// 상세보기로 이동 -> 서버에서 화면 이동
		response.sendRedirect("FoodDetail?fno="+fno);
		//       ------------GET방식
	
	}
}
