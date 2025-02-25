<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
     자바스크립트 사용법
     1. 내부 스크립트: 파일 한 개 제어
        <script type="text/javascript">
          함수
        </script>
     2. 외부 스크립트: 여러 파일에서 제어 => 재사용
        <script type="text/javascript" src="파일명"></script>
                                       ---경로명, URL 가능
		=> import
	 3. 인라인 스크립트: 태그 한 개 제어
	    <a href="javascript:history.back()">     
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%--
   Vue/React/Next
   => <script type="text/babel">
        소스코딩
      </script>
      <script>
        소스 코딩
      </script>
 --%>
<script type="text/javascript">
  	window.onclick=function()
  	{
  		let a=10;
  		let b=20
  		
  		let p1=plus1(a,b)
  		
  		document.write("p1="+p1)
  	}
	// 선언적 함수 선언
	function plus1(a,b)
	{
		return a+b
	}
	// 익명의 함수
	let plus2=function(a,b)
	{
		return a+b
	}
	// 가장 많이 사용
	let plus3=(a,b)=>{
		return a+b
	}
	// 가독성 떨어지는 함수
	let plus4(a,b)=>a+b
</script>
</head>
<body>

</body>
</html>