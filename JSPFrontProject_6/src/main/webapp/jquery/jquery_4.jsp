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
  width: 450px;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
let bCheck=true
$(function(){
	$('tr:eq(1)').hide()
	$('.btn-sm').on('click',function(){
		if(bCheck===true)
		{
			$('tr:eq(1)').show("slow")//보여지는 속도 => jquery ui 참고
			$(this).val('취소')
			bCheck=false
		}	
		else
		{
			$('tr:eq(1)').hide("fast")
			$(this).val('삭제') // value값 변환
			bCheck=true
		}
	})
})
</script>
</head>
<body>
  <div class="container">
    <div class="row">
      <h3 class="text-center">show/hide</h3>
      <table class="table">
        <tr>
         <td class="text-right">
          <input type=button value="삭제" class="btn-sm btn-danger">
         </td>
        </tr>
        <tr>
         <td>
          비밀번호<input type=password size=20 class="input-sm">
         </td>
        </tr>
      </table>
    </div>
  </div>
</body>
</html>