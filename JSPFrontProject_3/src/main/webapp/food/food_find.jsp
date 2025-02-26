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
<script type="text/javascript" src="food.js"></script>
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