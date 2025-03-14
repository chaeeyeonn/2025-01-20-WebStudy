<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#updateBtn').click(function(){
		//let no=$('#no').serialize()
		// serialize: 데이터 자동으로 넘기는 함수
		let no=$('#no').val()
		//detail 화면 이동에 no 보내줘야 함 => ajax는 창을 알아서 넘기는 게 아닌 데이터를 현 화면에 주기때문
		// 데이터 전송 : 수정요청
		$.ajax({
			type:'post',
			url:'../qna/qna_update_ok.do',
			data:$('#frm').serialize(),
			success:function(result)
			{
				//200
				location.href="../qna/qna_detail.do?no="+no
			},
			error:function(err)
			{
				alert(err)
			}
			//404,405,403,500,...
			// 전송 실패시 error 띄워줌
		})
	})
})
</script>
</head>
<body>
<div class="breadcumb-area" style="background-image: url(../img/bg-img/breadcumb.jpg);">
        <div class="container h-100">
            <div class="row h-100 align-items-center">
                <div class="col-12">
                    <div class="bradcumb-title text-center">
                        <h2>수정하기</h2>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="breadcumb-nav">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="#"><i class="fa fa-home" aria-hidden="true"></i> Home</a></li>
                            <li class="breadcrumb-item active" aria-current="page">food-list Page</li>
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
    </div>
    <!-- ****** Breadcumb Area End ****** -->

    <!-- ****** Archive Area Start ****** -->
    <section class="archive-area section_padding_80">
        <div class="container">
            <div class="row" style="width:800px;margin:0px auto">
             <form method="post" id="frm">
              <table class="table">
               
               <tr>
                <th class="text-center" width=15%>제목</th>
                <td width=85%>
                 <input type=text name=subject id=subject size=50
                  class="form-control-sm" required value="${vo.subject }">
                  <input type=hidden name=no value="${vo.no }" id="no">
                  <!-- hidden => DML이 있을 때 사용 -->
                </td>
               </tr>
               
               <tr>
                <th class="text-center" width=15%>내용</th>
                <td width=85%>
                 <textarea rows="10" cols="52" name=content required>${vo.content }</textarea>
                </td>
               </tr>
               
               
               <tr>
                 <td colspan="2" class="text-center">
                  <input type=button value="수정" class="btn-outline-primary btn-sm"
                   id="updateBtn"
                  >
                  <input type=button value="취소" class="btn-outline-danger btn-sm"
                   onclick="javascript:history.back()">
                 </td>
               </tr>
              </table>
              </form>
            </div>
        </div>
    </section>
</body>
</html>