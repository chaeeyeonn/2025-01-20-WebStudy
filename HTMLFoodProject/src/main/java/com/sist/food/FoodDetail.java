package com.sist.food;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
	}

}
