<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
     교재113page~ JSP 기초
     
     1. HTML과 JAVA 분리
       public class basic_jsp extends HttpBase
       {  
          -------------------------------멤버변수 선언/메소드 제작 => 클래스로 제작
          <%! %> => 선언문 => (극히 드물다)
          -------------------------------
          public void _jspInit(){}
          public void _jspDestroy(){}
          public void _jspService(***request,***response)
          {
             -------------------선언
             ***HttpSession session
             PageContext pagecontext
             JspWriter out
             Exception exception
             Object page=this
             ***ServletContext application
             ServletConfig config
             -------------------
             try
             {
               -----------------------JSP 코딩
               <% %> => 스크립트릿
               <%= %> => 표현식
               -----------------------
               
             }catch(Exception ex){}
             
          }
       }
       
       - <%@ %>: 선언문
          <%@ page %>
          : 파일에 대한 정보
          : 변환 => contentType="|" => 파일형식 지정
                                      1) text/html: html 전송
                                      2) text/xml: xml 전송
                                      3) text/plain: json 전송
          : pageEncoding="UTF-8"; => 한글 처리
          : import => 외부 라이브러리 / 사용자 정의 라이브러리
          : buffer => HTML을 출력할 메모리 공간의 크기
           => 8kb => 16kb
          : errorPage => error 발생시 자동으로 화면 이동
           => ***200 ***404 403 405 412 400 ***500
          <%@ taglib %>
          <%@ include %>
       - <%! %>
       - <% %>
       - <%= %>
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <%!
    // 선언문: 멤버 변수, 메소드 => class 영역
    public int add(int a, int b)
    {
    	return a+b;
    }
  %>
  <%
    // _jspService 영역(메소드 영역)
    int a=10;
    int b=20;
    int c=add(a,b);
  %>
  <%= c %>
</body>
</html>