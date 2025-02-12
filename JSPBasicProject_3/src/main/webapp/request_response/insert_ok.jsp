<%@page import="org.apache.ibatis.reflection.SystemMetaObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.io.*,com.sist.dao.*"%>
<%-- _ok: 데이터베이스 연동 => list.jsp로 이동 => html 필요 없음 --%>
<%
   // 오라클에 데이터 첨부
   DataBoardVO vo=new DataBoardVO();
   DataBoardDAO dao=DataBoardDAO.newInstance();
   
   
   //java.lang.IllegalStateException 어떤 multi-part 설정도 제공되지 않았기 때문에, part들을 처리할 수 없습니다. => part 사용 위한 xml 수정
   // => 서버 설정: context.xml 수정
   // <Context allowCasualMultipartParsing="true" path="/">
   //<Resources cachingAllowed="true" cacheMaxSize="100000" /> 삽입
   //※ 주의할 점은 HttpServletRequest 클래스의 getParts() 함수를 한번 호출하면 HttpServletRequest 객체의 파일 데이터를 다시 조회할 수가 없다. 
   /*
     xml은 지정된 속성만 사용 가능
     대소문자 구분 필요
     지정된 태그 / 속성을 정의하고 있는 파일 .dtd
     속성은 반드시 "" 붙이기
   */
   
   
   // 사용자가 보낸 데이터 가져오기
   String name=request.getParameter("name");
   String subject=request.getParameter("subject");
   String content=request.getParameter("content");
   String pwd=request.getParameter("pwd");
   
   // 사용자가 데이터 전송 => request.getParameter("name 속성값");
   // 사용자가 파일 전송 => request.getPart("name 속성값");
   Part filePart=request.getPart("upload");
   String fileName=filePart.getSubmittedFileName();
   if(fileName==null||fileName.equals(""))// 파일 업로드 x
   {
	   vo.setFilename("");
	   vo.setFilesize(0);
   }
   else// 파일 업로드 o
   {
	   String uploadDir="c:\\upload";
	   File file=new File(uploadDir,fileName);
	   // 파일 업로드 후 오라클 전송
	   // try ~ resource => 자동으로 input/output 생성
	   try(InputStream input=filePart.getInputStream();
		   FileOutputStream output=new FileOutputStream(file)   
		   )
	   {
		   byte[] buffer=new byte[1024];
		   int i=0;
		   while((i=input.read(buffer,0,1024))!=-1)
		   {
			   output.write(buffer,0,i);
		   }
	   }
	   vo.setFilename(fileName);
	   vo.setFilesize((int)file.length());
   }
   vo.setName(name);
   vo.setSubject(subject);
   vo.setContent(content);
   vo.setPwd(pwd);
   
   dao.databoardInsert(vo);
   /* System.out.println("name"+name);
   System.out.println("subject"+subject);
   System.out.println("content"+content);
   System.out.println("pwd"+pwd);
   System.out.println("fileName"+fileName); */
   response.sendRedirect("list.jsp");
%>