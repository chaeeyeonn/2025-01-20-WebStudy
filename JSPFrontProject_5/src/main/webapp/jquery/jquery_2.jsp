<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
// main
/*
 *   태그 읽기 => 속성 변경 / 이벤트 처리(동작)
     => 자바스크립트로 소스 코딩
     => let / const
     => DOMScript
 */
$(function(){
	// 자바스크립트 => 태그 읽기(선택자)
	// $(선택자).함수(매개변수...)
	//$(document).ready(function(){}) => window.onload=function()
	//  ---------------생략함
	/**
		$(선택자).css() => 태그의 CSS 적용 => 교재 383page~
		$(선택자).attr() => 속성의 값 변경
		---------------
		  <img src="">
		  $('img').attr("src") => getter
		  $('img').attr("src","") => setter
		
		$(선택자).val() => <input> <select> <textarea> => getter
		$(선택자).val("admin") => setter
		
		$(선택자).text() => textContent="값"
		$(선택자).text("") => 태그와 태그 사이 값 첨부
		
		innerHTML
		$(선택자).html()
		$(선택자).html("<h1></h1>")
	*/
	// 자바스크립트
	/* let h1=document.querySelectorAll('h1')
	for(let i=0;i<h1.length;i++)
	{
		h1[i].style.backgroundColor="yellow"
		h1[i].style.color='blue'
	} */
	$('h1').css("backgroundColor","green")
	$('h1').css("Color","white")
})
</script>
</head>
<body>
  <h1>Hello Jquery1</h1>
  <h1>Hello Jquery2</h1>
  <h1>Hello Jquery3</h1>
</body>
</html>