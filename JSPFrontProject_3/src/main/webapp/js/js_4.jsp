<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
    callback: 매개변수로 전송되는 함수
    		  Redux
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function func(callback) 
{
	for(let i=1;i<=5;i++)
	{
	   callback()//setTimer(함수명,시간), setInterval(함수명,시간)   
	}
}
let collback=function(){
	document.write("함수 호출...<br>")
}
window.onload=function()
{
	func(callback)	
}
</script>
</head>
<body>

</body>
</html>