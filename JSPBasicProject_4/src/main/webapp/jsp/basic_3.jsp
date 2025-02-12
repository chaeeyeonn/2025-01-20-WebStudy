<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%--
      ***request/***response/***out/pageContext
      *application/***session
      config/page/exception
        |     |       |
    web.xml  this  try~catch
 
      application
      클래스명: ServletContext
      용도: 서버 관리
             버전: getMajorVersion()
                  getMinorVersion()
                  => 서블릿 버전
             서버명: getServerInfo
           로그파일: log()
                   web/xml 연동 => getInitParameter()
           ***실제 경로명: getRealPath()/getResource()
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
MajorVersion:<%=application.getMajorVersion() %><br>
MinorVersion:<%=application.getMajorVersion() %><br>
Version:<%=application.getMajorVersion()+"."+application.getMajorVersion() %>
<br>
서버명:<%=application.getServerInfo() %>
<%
   String driver=application.getInitParameter("driver");
   String url=application.getInitParameter("url");
   String username=application.getInitParameter("username");
   String password=application.getInitParameter("password");
    		
   application.log("Driver:"+driver);
   application.log("URL:"+url);
   application.log("Username:"+username);
   application.log("Password:"+password);
   //서버관리자가 보는 로그 출력
%>
</body>
</html> 