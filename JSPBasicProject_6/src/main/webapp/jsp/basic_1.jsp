<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
<jsp:useBean id="dao" class="com.sist.dao.EmpDAO"/>
<%
   List<EmpBean> list=dao.empListData();
%>
<%--
   EmpDAO dao=new EmpDAO() 와 같음
 --%>
<%--
        교재 8장 => Basic
        ---------------
        1. 빈즈 / 액션
           JSP => Bean => VO(Spring), DTO(MyBatis)
           : 데이터를 모아서 브라우저 한 번에 전송
        - 자바빈즈 만들기
         1) 데이터를 저장하는 변수 설정
                         --------캡슐화 => private 설정
            => 설정된 변수마다 기능 부여
                    ---
                    메모리에 저장: setter
                    메모리에서 읽기: getter
            => 변수는 이미 결정
               -------------데이터베이스 => 컬럼명
                            크롤링필요한 데이터
                            오픈 API
         2) 설정 방식
            getter / setter: 자동완성기 / lombok
            => name
               setName() / getName()
            => boolean: 존재여부 확인
               boolean login
               setter: isLogin()
               getter: getLogin()
         3) 데이터 보호 / 데이터를 모아서 처리
         4) VO와 동일
         5) 액션태그 사용
           : <jsp:useBean>: 객체 메모리 할당
               MemberVO vo=new MemberVO()
               => <jsp:useBean id="vo" class="MemberVO">
             <jsp:setProperty>: 객체의 변수값 주입
               vo.setName="aaa"
               => <jsp:setProperty name="vo" property="name" value="aaa">
                ***=> <jsp:setProperty name="vo" property="*">
                    : vo가 가지고 있는 모든 setter에 값을 채운다
             <jsp:getProperty>: 객체의 변수값 출력
             <jsp:param>: 다른 페이지로 이동시 필요한 데이터 전송
           
        2. DBCP: DataBase Connection Pool
           (교재293page~)
          1) 데이터베이스 연결애 소모되는 시간을 단축할 수 있다
            : 보통 데이터베이스 연결 시간이 가장 길기 때문에 큰 장점
          2) 미리 Connection을 데이터베이스에 연결한 상태에서 저장
          3) 일반적으로 웹프로그램은 대부분 DBCP를 사용
          4) Connection 객체 제한
          5) 사용 후 재사용 가능
          6) Connection을 미리 생성했기 때문에 객체 관리 쉬움
          7) 쉽게 서버 다운되지 않음
          <dataSource type="POOLED"> MyBatis에서 DBCP 사용
        - 사용 목적: 웹 사용자에게 응답시간을 단축(빠른 속도)
                 => 효율적인 데이터베이스 연동
        - 방법
          1) 시작과 동시에 Connection 객체 생성(연결된 상태)
            : 몇 개 생성인지 알아야 함 => maxIdle="n"
            : 만약 초과된다면? 확장 => 추가 => maxActive="n"
          2) 저장 => 저장공간: Pool
          3) 사용자가 요청하면 Connection의 주소를 얻어온다
          4) 사용자 요청 처리
          5) 사용 후에는 반드시 POOL에 반환 => 재사용 위함
        - 코딩
          1) server.xml 등록
          2) getConnection(): 미리 생성된 Connection 객체 얻기
          3) disConnection(): 반환
          4) 기능은 동일
           
        3. Cookie / Session
        ----------------
        4. JSTL / EL
        5. MVC => Spring
        
        => 지도 / Cookie => 전체    
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="table.css">
</head>
<body>
  <h3>사원목록</h3>
  <table class="table_content" width=500>
    <tr>
      <th>사번</th>
      <th>이름</th>
      <th>직위</th>
      <th>입사일</th>
      <th>급여</th>
    </tr>
    <%-- 출력 위치: 자바 / 자바스크립트: 파일 변경 하지 않는다 --%>
    <%
      for(EmpBean vo:list)
      {
    %>
        <tr>
         <td><%=vo.getEmpno() %></td>
         <td><%=vo.getEname() %></td>
         <td><%=vo.getJob() %></td>
         <td><%=vo.getHiredate() %></td>
         <td><%=vo.getSal() %></td>
       </tr>
    <%
      }
    %>
  </table>
</body>
</html>