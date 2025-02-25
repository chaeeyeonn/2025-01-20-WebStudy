<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
 margin-top: 50px;
}
.row{
  margin: opx auto;
  width: 700px;
}
p{
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}
img.hover{
  cursor: pointer
}
</style>
<!-- 외부라이브러리 가져오기 -->
<script type="text/javascript" src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script type="text/javascript">
let food_list=[]
let startPage=0
let endPage=0
let curpage=0
let totalpage=0
// 시작과 동시에 값을 읽어 온다
window.onload=function(){
	let html='';
	//axios.get() / axios.post()
	axios.get('http://localhost/JSPFrontProject_3/food/find_js.do')
	.then((response)=>{
		console.log(response.data)
		food_list=response.data
		curpage=response.data[0].curpage
		totalpage=response.data[0].totalpage
		startPage=response.data[0].startPage
		endPage=response.data[0].endPage
		
		console.log("curpage="+curpage)
		console.log("totalpage="+totalpage)
		console.log("startPage="+startPage)
		console.log("endPage="+endPage)
		
		food_list.map(function(vo){
			html+='<div class="col-sm-4">'
				+'<div class="thumbnail">'
				+'<img src="'+vo.poster+'" style="width:100%" onclick="detail('+vo.fno+')">'
				+'<p>'+vo.name+'<p>'
				+'</div>'
				+'</div>'
		})
		let main=document.querySelector("#poster");
		//CSS selector
		main.innerHTML=html
	})
}
/*
 문서 객체 선택
 * document.querySelector("#fd") => id
   => document.getElementById("id")
   => $('#fd')
   document.querySelector(".fd") => class
   => document.getElementClassName("fd")
   => $('.fd')
   document.querySelector("div") => 태그명
   => document.getElementByTagName("div")
   => $('div')
 */
function foodFind(){
	let addr=document.querySelector("#fd").value
	if(addr.trim()=="")
	{
		alert("지역을 입력하세요")
		document.querySelector("#fd").focus()
		return
	}
	let html='';
	//axios.get() / axios.post()
	// ?대신 params 이용해야 함
	axios.get('http://localhost/JSPFrontProject_3/food/find_js.do',{
		params:{
			page:1,
			fd:addr
		}
	})
	.then((response)=>{
		console.log(response.data)
		food_list=response.data
		curpage=response.data[0].curpage
		totalpage=response.data[0].totalpage
		startPage=response.data[0].startPage
		endPage=response.data[0].endPage
		
		console.log("curpage="+curpage)
		console.log("totalpage="+totalpage)
		console.log("startPage="+startPage)
		console.log("endPage="+endPage)
		
		food_list.map(function(vo){
			html+='<div class="col-sm-4">'
				+'<div class="thumbnail">'
				+'<img src="'+vo.poster+'" style="width:100%">'
				+'<p>'+vo.name+'<p>'
				+'</div>'
				+'</div>'
		})
		let main=document.querySelector("#poster");
		//CSS selector
		main.innerHTML=html
	})
}
let detail=(fno)=>{
	axios.get("http://localhost/JSPFrontProject_3/food/detail_js.do",{
		params:{
			fno:fno
		}
	}).then((res)=>{
		console.log(res.data)
	})
}
</script>
</head>
<body>
<!-- 내용은 자바스크립트로 채운다 -->
  <div class="container">
    <div class="row">
      <input type=text id="fd" size=20 class="input-sm">
      <input type=button id="btn" value="검색" class="btn-sm btn-success"
      	onclick="foodFind()"
      >
    </div>
    <div class="row" style="margin-top:20px">
      <div class="col-sm-8" id="poster">
      
      </div>
      <div class="col-sm-4" id="detail" style="display:none">
      
      </div>
    </div>
    <div class="row" style="margin-top:10px">
      <div class="text-center" id="pages">
      
      </div>
  </div>
  </div>
</body>
</html>