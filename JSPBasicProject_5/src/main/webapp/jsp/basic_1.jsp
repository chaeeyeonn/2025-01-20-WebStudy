<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%--
      1. 실행과정
       브라우저 URL 요청           컴파일      인터프리터
         a.jsp => a_jsp.java => a_jsp.class ====> 메모리에 HTML 저장
                   자바파일 변경                      => 브라우저에서 출력
                   
      2. page 지시자
         jsp에 대한 정보
         ------------
          contentType: 변환(html,xml,json)
          import: 라이브러리 추가
          errorPage: 에러시 보여주는 jsp
          buffer => 8kb
      
      3. 자바 / html 분리
         <% %> : 일반 자바 코딩
         <%= %> : 데이터 출력
      
      4. 내장 객체
        - request: 요청
           getParameter()
           getParameterValues()
           getSession()
           getCookies()
           setAttribute()
           getAttribute()
           getRequestURI()
           getContextPath()
           getRemoteAddr()
        - response: 서버 응답
           setHeader()
           sendRedirect()
        - application: 서버 정보 관리
           log()
           getInitParameter()
           getRealPath()
        - out: 출력버퍼 관리
           println() / print()
        - pageContext: 내장객체 관리 => JSP연결 => 웹흐름제어
           <jsp:include>
           <jsp:forward>
         -----------
         session / cookie => 저장 후 데이터 유지
         
   교재 221page~ Java Beans / Action Tag
                Cookie / Session
                JSTL / EL
      -----------------------------------------------------------------
      MVC => Spring 형식
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>