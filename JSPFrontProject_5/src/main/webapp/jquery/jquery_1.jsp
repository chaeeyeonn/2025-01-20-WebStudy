<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
		JQuery: 자바스크립트 라이브러리 => 소스 통일화하기 위함
		------3버전에서(4,5버전 최근 출시)
		=> 태그, 속성을 제어하는 프로그램: 문서 객체
		=> 태그를 제어하기 위해 태그를 가져온다
		   document.querySelector("")
		   						------CSS 선택자
		   		=> $(CSS선택자).제어
		=> JQuery 사용시 반드시 라이브러리 import
		  <script src="http://code.jquery.com/jquery.js">
		  *** 버전이 출돌되는 오류 발생
		  => main에 모두 두어 쓰기
		  
		  JQuery 핵심 => 태그를 읽어서 처리 => CSS 선택자 이해하기
		  
		  1. window.onload=function(){}
		   => $(function(){})
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	/* let h1=document.querySelector("#h1")
	h1.style.color="red"
	
	let h2=document.querySelector(".h1")
	h2.style.color="blue"
	
	let h3=document.querySelector("h2")
	h3.style.color="green" */
	$('#h1').css("color","red")
	$('.h1').css("color","blue")
	$('h2').css("color","green")
	// => 함수화
})
</script>
</head>
<body>
  <h1 id="h1">Hello JQuery1</h1>
  <h1 class="h1">Hello JQuery2</h1>
  <h2>Hello JQuery3</h2>
</body>
</html>