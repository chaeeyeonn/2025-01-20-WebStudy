<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%--


   교재 69page~ JSP 실행과정
   
                      JSP파일요청
      웹브라우저(클라이언트) =======> 웹서버 
                                  |
                                 웹애플리케이션서버
                                 톰캣(WAS): JSP/Servlet 엔진
                                 -------------------------
                                 처리과정
                                 1) JSP 찾기
                                 2) JSP를 Class로 변경
                                 3) 변경된 Class 컴파일
                                 4) .class 생성(=> Servlet)
                                 5) interpreter 이용 출력
                                 6) HTML만 메모리에 출력
                                          -----Buffer
                                 7) 메모리에 있는 HTML을 브라우저에 읽히기
         * 클래스 변경은 한 번만 수행
         : 이미 생성된 경우, 소스만 수정 후 컴파일
         : 생성 안 된 경우, 자바 파일 생성 후 컴파일
        => a.jsp => a_jsp.java => a_jsp.class(servlet 파일)
                                       => 실행 => out.write("<html>")
        => 자바로 변경
        public final clss a_jsp extends HttpJspBase
               -----상속 불가
        {
            public void _jspInit()
            {
                => 생성자 역할
                => 멤버변수의 초기화
                => 연결된 파일: web.xml
            }
            public void _jspService(http.HttpServletRequest request, http.HttpServletResponse response)
            {
                PageContext pageContext;
                HttpSession session = null;
                ServletContext application;
                ServletConfig config;
                JspWriter out = null;
                Object page = this;
                
                try
                {
                   => JSP 코딩 영역 => _jspService메소드 완성
                      ---메소드 영역
                     => JSP 파일 읽어서 추가 => 사용자 요청 처리
                   => JSP에서 변수 선언: 지역 변수
                }catch(Exception ex){}
            }
            
            public void _jspDestroy()
            {
                => 화면 변경 / 새로고침 => 자동 호출( 메모리에서 삭제)
                System.gc()
            }
        }
        
        서블릿          서블릿 파일요청
        client(브라우저) ==========> 톰캣
                                   ---서블릿 찾기
                                      |
                                      서블릿 객체 생성
                                      |
                                      init() : 초기화
                                               web.xml
                                      |
                                      doGet() / doPost()
                                      ------------------메모리(HTML 출력)
                                      |
                                      요청화면 브라우저 전송
      => 서블릿: 개발자가 직접 처리
               컴파일 / 인터프리터 => 브라우저 출력
              : 웹서비스 기능을 해주는 자바 클래스
              => 자바 안에서 HTML 코드 추가 => out.write("<html>")
                => HTML 사용 어려움 => CSS/JavaScript
                => 에러 처리 어려움
                => 자바 수정 시 컴파일 다시
                => + 리눅스 : javac / java 명령 필요
         JSP: 톰캣에 의해 처리 => 브라우저 출력
              => 컴파일 x , 바로 확인 가능
              
         서블릿
         1) HTML 출력하지 않는 웹개발
           => Java/HTML 분리
              ---------
              연결
              Controller(서블릿으로 제작)
         2) 보안이 필요한 부분: Spring Security
         
         JSP
         1) 서블릿보다 쉽게 조작 가능
         2) HTML/Java 구분
         3) HTML,Java 따로 구현 가능 => 여러 명 동시 개발 가능
         4) 단순 => 데이터베이스 연동 
         5) HTML 따로 나와있어서 첨부하기 편리
         6) JSP는 파일이 아닌 메소드 영역에 코딩하는 것
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>