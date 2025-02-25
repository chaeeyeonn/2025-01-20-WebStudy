<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
   331page~ 객체
   => 여러 개의 데이터를 묶어 관리
   => 배열 / 객체
      []    {} => JSON(JavaScript Object Nontation)
                  자바스크립트 객체 표현법
    - 배열(Array): 혼합 가능
      Object[] obj={1,"",'',...}
     => let names=["","","","",""];
     => let sawon=[1,"이름",90,80,30,200,66.6]
     => 지원하는 함수
        1. 추가: sawon[0]=2
        2. 삭제
        3. 개수
        4. 자르기
        5. 찾기
        6. 완전 삭제
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=function(){
	let names=["홍길동","심청이","박문수"]
    console.log(names)
    console.log("인원:"+names.length)
    //데이터 추가
    names.push("이순신")// 마지막 추가
    console.log(names)
    console.log("인원:"+names.length)
    // 데이터 삭제
    /*names.pop()// 마지막 제거
    console.log(names)
    console.log("인원:"+names.length)*/
    
    // 전체 삭제
    /*delete names
    console.log("인원:"+names.length)*/
    
    /*names.shift()// 앞에서 삭제
    console.log(names)
    console.log("인원:"+names.legnth)*/
    
    console.log(names.slice(2))// 시작위치
    console.log(names.slice(1,2))// 범위 지정
    let names2=[...names,"춘향이"]//... => names의 모든 것 가져오기 + 뒤에 추가 가능
	console.log(names2)
}
</script>
</head>
<body>

</body>
</html>