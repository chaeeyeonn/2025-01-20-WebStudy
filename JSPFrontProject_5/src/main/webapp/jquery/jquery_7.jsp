<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	// 같은 태그가 많을 경우 인덱스 번호 이용
	// => eq : 0번부터 시작
	// => nth-child : 1번부터 시작
	$('span:eq(0)').text("Hello JQuery")
	$('span:eq(1)').text('<font color=red>Hello JQuery</font>')
	//text는 일반 문자열로 취급(textContent) => html 이용
	$('span:eq(1)').html('<font color=red>Hello JQuery</font>')
	// innerHTML
	// appendChild() => append()
})
</script>
</head>
<body>
  <span>
    
  </span><br>
  <span>
  
  </span>
</body>
</html>