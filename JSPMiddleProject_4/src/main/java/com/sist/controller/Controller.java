package com.sist.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import com.sist.model.*;

/*
 *    1. MVC 동작 구조(.jsp는 사용하지 않는다)
 *                   지정한 확장자 사용
 *    JSP ===================Controller  ====== 위임
 *                                               |
 *                                             요청 처리
 *                                             =======Model
 *                                                 1. 요청처리
 *                                                 결과값 request/session에 담기
 *                                                 2. 어떤 JSP에 받을 건지 지정
 *                                               |
 *                                           Controller
 *                                               |
 *                                           JSP를 찾아 request를 전송
     JSP 사용 x => JSTL/EL, MyBatis , MVC => Spring
 *    <a href="list.do">
 *    <form action="insert.do">
 *    
 *    2. M(Model): 사용자 요청 처리해주는 클래스 집합
 *                --------------
 *                ~VO,~DAO,~SERVICE,~MANAGER. => JSTL/ EL 
 *    3. V(View): JSP로 만듦
 *    4. C(Controller)
 * 
 */
// xml 파일 속 .do 찾기 => controller 연결
@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
    // 클래스 저장
	private Map clsMap=new HashMap();
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		try
		{
			String xml_path="C:\\webDev\\webStudy\\JSPMiddleProject_4\\src\\main\\webapp\\WEB-INF\\application.xml";
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			// 싱글턴, 파서기 생성 => XML, WML, HDML, VML, ...
			DocumentBuilder db=dbf.newDocumentBuilder();
			// 파서기
			
			Document doc=db.parse(new File(xml_path));
			//System.out.println(doc.toString()); => xml 내용
			
			//root
			Element root=doc.getDocumentElement();
			//System.out.println(root.getTagName()); => root 태그 => beans
			NodeList list=root.getElementsByTagName("bean");
			for(int i=0;i<list.getLength();i++)
			{
				Element bean=(Element)list.item(i);//bean 태그 가져오기
				String id=bean.getAttribute("id");
				String cls=bean.getAttribute("class");
				
				//클래스 메모리 할당 => new ListModel() 과 같음
				Class clsName=Class.forName(cls);
				Object obj=clsName.getDeclaredConstructor().newInstance();
				System.out.println(id+":"+obj);
				// 데이터 삽입
				clsMap.put(id, obj);
			}
			// => DOM Parsing: 데이터를 메모리에 트리 형태로 저장, 관리
			// 실제 데이터만 추출 => SAX => 스프링에서 주로 사용
		}catch(Exception ex) {}
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// http://localhost/JSPMiddleProject_4/list.do => 잘라내서 알아내기
		// http://localhost/JSPMiddleProject_4/list.do?page=1 이라면?
		// => request는 ? 앞까지 => uri 인식이니 상관없음
		// 사용자 요청 받기
		String uri=request.getRequestURI();
		//System.out.println(uri);
		// /JSPMiddleProject_4/list.do
		uri=uri.substring(request.getContextPath().length()+1);
		// getContextPath() => JSPMiddleProject_4
		// +1 => / 이후
		Model model=(Model)clsMap.get(uri);
		String jsp=model.handlerRequest(request);
		
		if(jsp.startsWith("redirect:"))
		{
			jsp=jsp.substring(jsp.indexOf(":")+1);
			response.sendRedirect(jsp);
			// _ok.do => 화면 이동(list,detail)
		}
		else
		{
			// 화면 출력(출력할 데이터 보내기) => request
			RequestDispatcher rd=request.getRequestDispatcher(jsp);
			rd.forward(request, response);
			// 초기화 x
		}
	}

}
