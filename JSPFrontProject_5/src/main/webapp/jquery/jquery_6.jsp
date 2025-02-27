<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
/*
 *   이벤트 
 	  - 함수호출
 	  ------------------
 	  - 고전 이벤트 처리
 	  
 	  - 이벤트 리스너
 	  let btn=document.querySelector("#id")
 	  btn.addEventListener('click',function(){처리})
 	  => $('#id명').on('click',function(){})
 	  ------------------
 */
$(function(){
	$('#count').change(function(){
		let count=$(this).val() // <input> <select> <textarea>
		//alert(count)
		let price=$('#price').text()// <span>aaa</span> <td>aaa</td> => aaa 읽어옴
		// < a href=""> <img src=""> => attr()
		// val("admin") val(), text() text("")
		//alert(price)
		price=price.replace(',','')
		price=price.replace('원','')// => 30,000원 => 30000
		//alert(price)
		let total=count*price
		//alert(total)
		$('#total').text(total.toLocaleString()+"원")
		/*
			글자 조작
			------- 
			         모든태그         
			         <태그>값</태그>
			          -----
			  val() / text() / html() / attr() / append()
			  ----    
			  value값으로 채우는 태그
			  <input> <select>
			  <textarea>
		*/
	})
})
</script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
 margin-top: 50px;
}
.row{
  margin: opx auto;
  width: 350px;
}
</style>
</head>
<body>
  <div class="container">
    <div class="row">
      <span id="price">30,000원</span>
      <select class="input-sm" id="count">
        <option value="1">1개</option>
        <option value="2">2개</option>
        <option value="3">3개</option>
        <option value="4">4개</option>
        <option value="5">5개</option>
      </select>
      <br>
      총금액:<span id="total"></span>
    </div>
  </div>
</body>
</html>