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
	}
}
