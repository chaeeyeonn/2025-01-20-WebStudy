<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
		JQuery / Vue(Vuex) / React(React-Query/Redux)
		  => 문서 객체(태그 제어) => 태그 선택 후 처리
		  => 태그에 대한 이벤트 처리 => DOMScript
		  단점: 데이터가 없다 (데이터 => 서버)
		                           ----오라클 연결 가능
	      => 출력 역할(MVC => View) / 속도가 빠르다
	      
		1. CSS 선택자
		       ----
		       1) 태그 => $('태그명').함수()
		                           ------값 설정
		                           val("")
		                           text("") / html("") / attr("") / append("") => 값 읽기
		                           val() / text() / html() / attr() => 이벤트
		                           $(선택자).click(function(){})
		                           $(선택자).on('click',function(){})
		                           => mouseup / mousedown
		                           => keyup / keydown
		                           => hover
		                           => change
	           2) ID(속성값) => $('#ID명')
	           3) CLASS(속성값) => $('.클래스명')
	           4) 자손: $('태그명(id명,class명)	> 태그명(id명,class명')
	              후손: $('태그명(id명,class명) 태그명(id명,class명')
	              => 목록 출력에 많이 사용
	                 <table>, <ul>, <dl> => id, class를 부여하지 않는다 
	              <div>
	                <h1></h1> => 자손
	                <div>
	                  <h1></h1> => 후손
	                </div>
	              </div>
	           5) 속성선택자
	               - $('태그명[속성=값]') => a, img, input
	               	      = equals
	               - $('태그명[속성$=값]') => endsWith
	               - $('태그명[속성^=값]') => startsWith
	               - $('태그명[속성*=값]') => contains
	               --------------------------------------파이썬
	           6) 가상선택자
	               - nth-child / eq
	                 순서: 1번~ / 0번~
	                 $('태그명:nth-child(odd)') => 홀수
	                 $('태그명:nth-child(even)') => 짝수
	                 $('태그명:nth-child(2n+1)')
	                 $('태그명:eq(0)')
	                 
	             $('') => 내장 객체, 태그 선택자
	             
		2. 자바스크립트 기초
		   JSP / Spring / Spring-Boot => Java
		   Django => Python
		   Asp.net => C#
		   
		   변수
		    let : 지역변수, const : 전역변수
		    
		   데이터형
		    number: 숫자(정수,실수)
		    string: 문자, 문자열
		    boolean: true / false
		            숫자: 0, 0.0 외에는 true
		            문자: null 외에는 true
		    object
		      배열[] => List 호환
		      객체{} => VO 호환
		    undefined: 데이터값이 없는 경우
		    function: 데이터형 취급
		             => 매개변수 사용 가능(callback)
		             => 이벤트/ react-query / redux
		             map(function(){})
		             
		             function a(){}
		             map(a)
		             
		             forEach(function(){})
		             
		    연산자
		      산술연산자 => / (정수/정수=실수)
		      비교연산자 => 10 =='10' => true
		                  10 ==='10' => false
		      형변환연산자 => Number, parseInt: 문자열을 정수형으로 변환
		                   => 데이터는 주로 String으로 받음
		                => String(10) => "10" : 문자열 변환
		                => Boolean(1): true
		                   Boolean(0): false
		                   Boolean('a'): true
		                   Boolean(null): false     
		                   
		    제어문
		      if / if~else
		      for / for-each
		      => 출력 담당
		      일반 for
		      for(let i=0;i<=10;i++)
		      {
		        처리문장
		      }
		      for(let i in 배열) => i는 인덱스 번호(값 가져오지 않음)
		       [1,2,3,4] => i=0,1,2,3
		      for(let key in 객체) => key는 key값
		       {name:"",sex:""} key=name,sex
		      for(let vo of 배열)
		              --배열 실제값
		      ***배열.map(function(배열값){})
		      배열.forEach(function(배열값){})
		      
		    함수
		    내장 함수: String 
		               substring() / substr() => 문자열 자르기
		               indexOf / lastIndexOf => 문자 위치 찾기
		               length() => 문자 개수
		               split() => 문자 분리
		               trim() => 좌우의 공백 제거
		               replace() => 문자 변환
		             Array
		               push()
		             Number
		             Date
		               getFullYear()
		               getMonth() => 0번 시작
		               getDate() => 일
		               getDay() => 요일
		             Math
		               ceil() => 올림
		               round() => 반올림
	        브라우저: window / document / location / history
		   
		3. 서버연결 => 데이터 읽기 => Ajax
		   ---------------------------
		   1) 로그인처리 / 회원가입(아이디 찾기, 비밀번호 찾기)
		                아이디 중복체크
		   2) 검색
		   3) 추천
		   4) 예약
		   => 입력된 데이터를 유지(jsp는 서버를 거치면 초기화된다)
		   5) 채팅
		   
		   
	    JQuery => 서버 연결: Ajax, axios(<a> , <form>, location.href)
	                      => client에서 server로 => URL 이용
	      => 값 읽기 / 값 쓰기
	         val() / text()
	      => 이벤트: 언제 데이터를 읽어오는가 / 쓰는가
	      
 --%>
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
  width: 350px;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	// 이벤트 리스너 활용
	/*
	   click: 클릭했을 때
	   change: 선택이 변경되었을 때
	   mousedown / mouseup
	   keydown / keyup
	   hover
	   => $().함수()
	          ---
	          1. 디자인 변경: css()
	          2. 속성 변경: attr()
	          3. 값 읽기: val(), text(), html()
	          4. 값 저장: val(""), text(""), html("")
	                  여러 개 출력: append()
	   
	*/
	/*
	   $('form').submit() => 데이터전송
	*/
	$('#login').on('click',function(){
		// 1. ID 읽기
		let id=$('#id').val()
		if(id.trim()==="")
		{
			$('#print').html('<font color="red">아이디를 입력하세요</font>')
			$('#id').focus()
			return
		}	
		else
		{
			$('#print').text("")
		}
		let pwd=$('#pwd').val()
		if(pwd.trim()==="")
		{
			$('#print').html('<font color="red">비밀번호를 입력하세요</font>')
			$('#pwd').focus()
			return
		}
		else
		{
			$('#print').text("")
		}
		alert("Ajax를 이용한 로그인 처리 수행")
	})
	
	$('#find').click(function(){
		let fd=$('#keyword').val()
		if(fd.trim()==="")
		{
			$('#keyword').focus()
			return
		}
		alert("검색어:"+fd)
		$('#keyword').val("")
	})
	$('#keyword').keydown(function(e){
		if(e.keyCode===13)// 키종류 13 : enter
	   {
			let fd=$('#keyword').val()
			if(fd.trim()==="")
			{
				$('#keyword').focus()
				return
			}
			alert("검색어:"+fd)
			$('#keyword').val("")
	   }
	})
})
</script>
</head>
<body>
  <div class="container">
    <div class="row">
      <h3 class="text-center">Login(val()/text())</h3>
      <table class="table">
        <tr>
         <th class="text-center" width=20%>ID</th>
         <td width=80%>
           <input type=text id="id" size=15 class="input-sm">
         </td>
        </tr>
        <tr>
         <th class="text-center" width=20%>PW</th>
         <td width=80%>
           <input type=password id="pwd" size=15 class="input-sm">
         </td>
        </tr>
        <tr>
         <td colspan=2 class="text-center">
          <input type=button class="btn-sm btn-success" id=login value="로그인">
         </td>
        </tr>
        <tr>
         <td colspan=2> 
          <span id="print"></span>
         </td>
        </tr>
      </table>
      <h3 class="text-center">Key이벤트</h3>
      <input type=text id="keyword" size=20>
      <input type=button id="find" value="검색" class="btn-sm btn-danger">
    </div>
  </div>
</body>
</html>