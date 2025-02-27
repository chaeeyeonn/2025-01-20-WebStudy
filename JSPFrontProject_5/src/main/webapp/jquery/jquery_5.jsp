<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script><!-- 라이브러리 import 상태 -->
<script type="text/javascript">
$(function(){
	/*
	   $(  )
	    ----selector(태그 선택자)
	     	브라우저 객체
	     	window, document, location
	     	------
	     	위치지정
	   $(this)
	    ------자신의 객체(행위를 한)
	*/
	//$('img').css('width','200px')
	//$('img').css('height','250px')
	$('img').css({
		'width':'250px',
		'height':'300px'
	})
	$('img').hover(function(){//if
		$(this).css('cursor','pointer')
		$(this).css('opacity',0.2)
	},function(){//else
		$(this).css('opacity',1.0)
	})
})
</script>
</head>
<body>
  <img src="m1.jpg">
  <img src="m2.jpg">
</body>
</html>