<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	오늘 할 일
	1) DB 테이블 만들기, 크롤링
	2) GIT Repository 만들고 팀원 Pull하기
	3) 템플릿 mainpage 넣어놓기
	이번주
	=> mainpage/join/login 만들기
				----------ajax 활용(+검색, 예약, 결제)
	
	1. 라이브러리 로드
	   <script src="http://code.jquery.com/jquery.js">
	   => 라이브러리 충돌 조심
	   => main.jsp에 추가
	2. jquery는 자바스크립스 라이브러리
	  1) 변수 설정: var/ let/ const: 서버에서 데이터 읽기
	  2) 데이터형: number/ string/ date/ boolean/ object:{}, []
	  3) 연산자: 자바스크립트 이용
	  	 +: 문자열 결합, HTML 제어하는 프로그램: 읽기 => 문자열 => 형변환: Number, parseInt
	  4) 제어문: if/ if~else
	  		   for => 일반 for
	  		   for-each => map/ forEach
	  5) 서버에서 데이터 전송 받는 경우 => Ajax
	  	 => 검색: List => [] 배열로
	  	 => VO: {키:값,키:값,...} => JSON
	  6) 객체 모델 / 문서 모델 => 태그 제어***
	  	 자바스크립트: document.querySelector(CSS)
	  	 Jquery: $(CSS) => selector
	  	 --------------------------------태그 선택 / 내장 객체
	  7) 자바스크립트에서 지원하는 라이브러리
	  	 Array / String / Number / Date / Math
	  	 ------		      ------          ------ ceil(), round()
	  	 push() 	      형변환, toLocalString()
	  	 : 데이터 추가	      parseInt
	  	 slice(): 자라서 새로운 배열
	  	 pop(): 데이터 삭제, 배열 복사
	  8) 브라우저 객체
	     window => open(), close(), scrollbar()
	     location => href: 화면 이동
	     history => back, go(-1)
	     document => write(), querySelector()
	   ---------------------------------------Jquery에서 자바스크립트 이용
	  
	   jquery
	   ------selector => 태그 제어(읽기/쓰기) => 출력
	                  => ajax
	   1) selector
	      태그명: $('태그명') => 멀티일 경우
	      아이디명: $('#아이디명') => 싱글
	      클래스명: $('.클래스명') => 멀티일 경우
	      						=> 해당 태그 $(this)
	      => CSS
	      	 후손/자손 => $('태그명>태그명') / $('태그명 태그명')
	      	 속성 => $('태그명[속성=값]')
	      	 가상선택자 => 목록 출력(table, dl, ul,...)
	      	 			=> $('li:eq(0)')
	      	 			   $('li:nth-child(1)')
       2) 태그 제어
       	  감추기/보여주기: display:none/display:''
       	  				   hide    /  show
       	  태그 간 값 읽기/쓰기: <td>aaa</td> => $('td').text() => 읽기
       	  					<td></td> => $('td').text("aaa") => 쓰기
       	  					=> 자바스크립트 td.textContent=""
       	  					<td><span>aaa</span></td> => $('td').html()
       	  												=> <span>aaa</span>
       	  					=> td.innerHTML=html
    	 태그의 속성값 읽기/쓰기: <태그 속성=값> => $('태그').attr(속성명) => 읽기
    	                                 => $('태그').attr(속성명,값) => 쓰기
    	 value값 받기 => input / select / textarea
    	 <input type=text> => $('input').val() => 읽기
    	 				   => $('input').val(값) => 쓰기
    	 append() => 추가를 계속 할 경우
      3) 읽기 / 쓰기 시점 => 이벤트
         버튼 / 이미지 => click
            => $('태그').click(function(){}): 고전적 이벤트
            => $('태그').on('click',function(){}): 리스너 이용
         => onchange: select
         	onkeydown / onkeyup
         	onmouseout / onmouseover => hover
      4) 애니메이션(생략)
      5) Ajax: 비동기적인 자바스크립트 and XML
               ------async / sync(동기적)
         동작
         : 브라우저에서 서버를 연결하는 객체 얻기
         			 -------------------XMLHttpRequest: 브라우저에 내장
           브라우저(화면출력)            서버(요청처리)
                 XMLHttpRequest: 요청
                 <--------------
                 요청 처리된 데이터 응답
                  => XML / JSON
           => 한페이지에서 송수신 가능 => 페이지 변경 X
                        -----유지보수
           => 'Ajax를 이용한 로그인 처리': PR용
       	             
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
let httpRequest=null
function getXMLHttpRequest()//브라우저에서 HttpXMLRequest 객체 읽어오기
{
	// 브라우저마다 객체 이름이 다름
	if(window.XMLHttpRequest)//null이 아니면
	{
		// 반드시 chrome, firefox 이용 => 익스플로러 사용 불가
		return new XMLHttpRequest()
		// XMLHttpRequest : 서버에 요청 => 결과값을 읽어오는 역할
		// => $.ajax({})
	}
	else
	{
		return null	
	}
}
// 전송 => 수신
function sendRequest(url,params,callback,method)
{
	//callback: 시스템에 의해 자동호출되는 함수 => 결과값 출력	
	/**
		$.ajax({
			url: ,
			type: , => method
			data:{}, => params
			seccess:function()
			{
				데이터 받아 출력 => callback
			}
		})
		// vue, react
		axios.get(url,{params}).then(function(){})
		axios.post(url,{params}).then(function(){}) => 최근 많이 쓰는 방식 2가지임
	*/
	// ajax 코딩
	// 1. 객체 생성
	httpRequest=getXMLHttpRequest()
	// <form action=""> => GET방식 default / <form method="POts"> => 잘못 씀 => GET방식
	let httpMethod=method?method:'GET' // null값인가? 아니면 method / 맞으면 GET
	// 2. method 방식 지정
	if(httpMethod!='GET' && httpMethod!='POST')
	{
		httpMethod='GET'	
	}
	// 3. param 처리 => ?id=admin
	let httpParams=(params===null||params==="")?null:params
	// 4. URL 처리
	let httpUrl=url
	// GET방식+전송할 데이터가 있다면
	if(httpMethod==='GET' && httpParams!=null)
	{
		// 전송할 값이 있다
		httpUrl=httpUrl+"?"+httpParams
	}
	// 서버 연결 => open 함수는 이미 존재
	httpRequest.open(httpMethod,httpUrl,true)
	// true => 비동기 , false => 동기
	// 한글 처리
	httpRequest.setRequestHeader('Content-Type','application/x-www-form-urlencoded')
	// 자동 호출되는 함수 지정
	httpRequest.onreadystatechange=callback
	// 데이터 전송
	httpRequest.send(httpMethod==='POST'?httpParams:null)
	// 데이터 읽기:responseText: 실행된 결과 읽기 / responseXML(JSON): JSON읽기
	
}
function send()
{
	sendRequest('sub.jsp',null,ok,'POST')	// 요청부분
}
function ok()
{
	/**
		0: 서버 연결 준비
		1: 서버 연결 => open
		2: 서버 연결 완료
		3: 데이터 전송 분비 => send()
		4: 데이터 전송 완료
	*/
	if(httpRequest.readyState===4)
	{
		// 200: 정상 수행
		if(httpRequest.status===200)
	    {
			let div=document.querySelector("#print")
			div.innerHTML=httpRequest.responseText
	    }
	}
}
</script>
</head>
<body>
  <button onclick="send()">전송</button>
  <div id="print"></div>
</body>
</html>