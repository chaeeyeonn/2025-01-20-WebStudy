<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#box{
  width: 200px;
  height: 200px;
  border: 1px solid black;
}
</style>
<script type="text/javascript">
window.onload=()=>{
	let div=document.querySelector("div")
	div.addEventListener('mousedown',function(){
		div.style.backgroundColor="red"
	})
	div.addEventListener('mouseup',function(e){
		e.currentTarget.style.background='orange'
	})
}
</script>
</head>
<body>
  <div id="box"></div>
  <div id="box"></div>
  <div id="box"></div>
  <div id="box"></div>
  <div id="box"></div>
  <div id="box"></div>
  <div id="box"></div>
  <div id="box"></div>
  <div id="box"></div>
  <div id="box"></div>
  <div id="box"></div>
</body>
</html>