<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	이벤트: 사용자가 행위를 한 경우
	      ------ 브라우저 안에서 사용자 요청
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function btnClick()
{
	alert("버튼 클릭")
}
</script>
</head>
<body>
<input type=button value=클릭 onclick="btnClick()">
</body>
</html>