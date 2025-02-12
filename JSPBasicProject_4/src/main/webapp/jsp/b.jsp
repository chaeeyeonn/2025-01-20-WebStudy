<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    //response.sendRedirect("c.jsp");//값은 초기화 => c로 넘어가면 null값 되어버림
pageContext.forward("c.jsp");//=> 값을 그대로 => MVC에서 많이 나옴

%>