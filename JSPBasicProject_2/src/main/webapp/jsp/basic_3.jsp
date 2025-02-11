<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*,java.util.*"%>
<%--
    129page~ 기본 제어문 사용
    ---------------------if/for
 --%>
 <%
    //1. 자바 이용해 출력할 데이터 가져오기
    EmpDAO dao=EmpDAO.newInstance();
    List<EmpVO> list=dao.empListData();
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="table.css">
<style type="text/css">
.container{
  width: 800px;
  margin-top: 50px;
}
.table_content{
  width: 600px;
  margin: 0px auto;
}
</style>
</head>
<body>
  <div class="container">
    <h3 style="text-align:center">사원목록</h3>
    <table class="table_content">
      <tr>
        <th>사번</th>
        <th>이름</th>
        <th>직위</th>
        <th>입사일</th>
        <th>급여</th>
      </tr>
      <%
        for(EmpVO vo:list)
        {
        	if(vo.getEmpno()%2==0)
        	{
      %>
          <tr>
            <td align="center"><%= vo.getEmpno() %></td>
            <td align="center"><%= vo.getEname() %></td>
            <td align="center"><%= vo.getJob() %></td>
            <td align="center"><%= vo.getHiredate().toString() %></td>
            <td align="center"><%= vo.getSal() %></td>
          </tr>
      <%
        	}
        }
      %>
    </table>
  </div>
</body>
</html>