<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=function(){
	display()
}
// 함수 형태 => 여러 개 쓸 수 있음 => 마지막 작성한 코드가 작동
function display()
{
	alert("function display Call...")	
}
// 지역변수 형태 => 여러 개 쓰면 오류
/* let display=()=>{
	alert("let display=() Call")
} */
function display()
{
	alert("let display=function() Call")	
}
/* let display=function(){
	alert("let display=function() Call")
} */
</script>
</head>
<body>

</body>
</html>