<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*,java.util.*"%>
<%
   // 사용자가 보낸 값 받기 => page
   // 첫 페이지는 null값 => 1로 지정
   String strPage=request.getParameter("page");
   if(strPage==null)
   {
	   strPage="1";
   }
   int curpage=Integer.parseInt(strPage);
   // 데이터베이스 연결
   DataBoardDAO dao=DataBoardDAO.newInstance();
   // 데이터베이스에서 출력할 데이터 가져오기
   List<DataBoardVO> list=dao.databoardListData(curpage);
   int totalpage=dao.databoardTotalPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="table.css">
<style type="text/css">
.container{
  width: 900px;
  margin-top: 50px;
}
h3{
  text-align: center;
}
.table_content{
  width: 500px;
  margin: 0px auto;
}
</style>
</head>
<body>
  <div class="container">
    <h3>글쓰기</h3>
    <form method="post" action="insert_ok.jsp"
     enctype="multipart/form-data"
    >
    <!-- 파일업로드시 사용하는 프로토콜 -->
    <table class="table_content">
      <tr>
        <th width=15%>이름</th>
        <td width=85%>
          <input type=text name=name size=20 required>
        </td>
      </tr>
      <tr>
        <th width=15%>제목</th>
        <td width=85%>
          <input type=text name=subject size=50 required>
        </td>
      </tr>
      <tr>
        <th width=15%>내용</th>
        <td width=85%>
          <textarea rows="5" cols="52" name=content required></textarea>
        </td>
      </tr>
      <tr>
        <th width=15%>첨부파일</th>
        <td width=85%>
          <input type=file name=upload size=30>
        </td>
      </tr>
      <tr>
        <th width=15%>비밀번호</th>
        <td width=85%>
          <input type=password name=pwd size=10 required>
        </td>
      </tr>
      <tr>
        <td colspan="2" align=center>
          <input type=submit value="글쓰기">
          <input type=button value="취소"
           onclick="javascript:history.back()">
           <!-- 이전으로 돌아가기 -->
      </tr>
    </table>
    </form>
  </div>
</body>
</html>