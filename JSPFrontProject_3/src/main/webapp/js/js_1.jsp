<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
      자바스크립트: 브라우저에서 화면 출력
                정적 => html/CSS
                동적 => JavaScript
                      -----------서버 요청 / 서버 응답 => 동시 수행
                                 => Ajax / axios
     1. 데이터형
        자동 데이터형 변경
        --------------
        let / const
        => number(숫자형) => int / double
        => string(문자형) => '' , ""
                          ----여러 글자
        => object => 배열 , 객체형
        			 []    {} => JSON
        => function => 매개변수로 사용
                       ------callback
                       map(function(){})
        => null, NaN, infinity
        => typeof: 데이터형 확인
     2. 연산자
        +: 문자열 결합 / 덧셈
        /: 0으로 나누는 경우 => infinity
           정수 / 정수 => 실수
        비교연산자
        5=='5' => true => 문자 자동 변환
        5==='5' => false => 문자 자동 변환 X(권장): 엄격한 연산자
        형변환 연산자
        - 정수 변환: Number('10'),parseInt('10')
        - 논리 변환: Boolean(1), Boolean(0)
                   => 0, 0.0이 아닌 수는 모두 true
                   => null이 아닌 값 true
        - 문자 변환: String(10)
        *** HTML에서 값을 가지고 오는 경우
           => 모든 데이터형 String
     3. 제어문
        - 조건문
          - 단일 조건문
            if(조건문)
            {
               조건이 true 처리
            }
          - 선택 조건문
            if(조건문)
            {
               조건이 true 처리
            }
            else
            {
               조건이 false 처리
            }
          - 다중 조건문
            if(조건문)
            {
               조건이 true 처리
               조건이 false면 아래로
            }
            else if(조건문)
            {
               조건이 true 처리
               조건이 false면 아래로
            }
            else
            {
               해당 조건이 없으면 처리
            }
        - 반복문: for문 => while(채팅)
          - 일반 for문
            for(let i=0;i<=10;i++)
            {
               반복 처리
            }
          - for-each
          : for of
            for(변수 of 배열) => for(MovieVO vo:list)
            { 
               변수 => 배열에 있는 값 읽기
            }
          : forEach
            배열.forEach(function(값){처리})
            배열.forEach((값)=>{})
                           ---화살표 함수 => 람다식
                           ---함수 포인터
          : map
            배열.map(function(값){처리})
            배열.map((값)=>{})
     4. 함수(교재324page~): 명령문의 집합 => 한 가지 일만 수행
       - 선언적 함수
         ** 리턴형 선언
         ** 매개변수의 데이터형을 사용하지 않는다
         => 국영수 점수 받기
            function gesan(kor,eng,math) => 변수명만 사용
         function 함수명(매개변수) => 선언부
         {
            구현부
         }
         function
       - 익명의 함수
         let 함수명=function(매개변수){}
         let 함수명=(매개변수)=>{}
                          --function / return 제거(권장)
                          
         class A
         {
           constructor(msg)
           {
              this.msg=msg
           }
           test:function(){
             axios.get('URL',{데이터전송})
             .then((response)=>{
               응답 받아 처리
               this.msg=response.data.msg
               ----A의 것
             })
           }
         }  
         => 사용처는 이벤트 처리
         onclick
         onmouseover / onmouseout
         onkeyup / onkeydown
          => 사용자 정의 함수 주로 이용
     5. 태그 선택(객체 모댈)
     6. 객체(배열 / JSON)
     이벤트 처리
     내장 객체(브라우저 객체, 일반 객체)
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
let login=function(){
	
}
window.onload=function(){
	document.write("login="+typeof login)
}
</script>
</head>
<body>

</body>
</html>