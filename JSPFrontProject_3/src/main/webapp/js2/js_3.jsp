<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
  	문서 객체: 태그를 가져와서 객체로 변환(태그: 객체, 속성: 멤버변수)
  	<a href="" target="">
  	
  	let a=document.querySelector("a")
  	a.href="";
  	a.target="_blank"
  	
  	=> document.querySelector()
  	                        ----
  	                        1. id => #id명
  	                        2. class => .class명
  	                        3. tag명 => tag명
    => document.getElementById(id명)
    ***=> document.getElementByClassName(클래스명)
    ***=> document.getElementByTagName(태그명)
    ***=> 같은 태그 여러 개
       document.querySelectorAll()
    ***는 객체가 배열로 들어온다
    
    createElement("태그명") => 태그 생성
    createElement("a") => <a></a> 생성
    createTextNode("aaa") => <a>aaa</a> => 태그 사이 값
    속성 설정: setAttribute() => $().attr()
    속성 읽기: getAttribute()
    태그 여러 개 생성: appendChild() => $().append()
                                    ----document.querySelector()
    태그 간 값 첨부
    1) 태그.innerHTML="<h1>aaa</h1>" => aaa
    2) 태그.textContent="<h1>aaa</h1>" => <h1>aaa</h1>
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="img.js"></script>
</head>
<body>

</body>
</html>