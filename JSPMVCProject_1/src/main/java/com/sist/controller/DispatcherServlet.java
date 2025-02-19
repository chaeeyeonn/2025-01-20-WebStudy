package com.sist.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Method;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.sist.model.Model;
// DOM => XML을 한 번에 파싱
// SAX => XML을 순차적으로 읽어 파싱
// 문제점: AWS에 호스팅 => 리눅스(우분투) => 경로명의 문제
// => 직상 => 리눅스에서 호환
@WebServlet({"*.do" })
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map clsMap=new HashMap();
    // XML 읽어서 클래스 확인
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		try
		{
			URL url=this.getClass().getClassLoader().getResource(".");// . => 현재 폴더: controller
			File file=new File(url.toURI());
			//System.out.println(file.getPath());
			//C:\webDev\webStudy\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\JSPMVCProject_1\WEB-INF\classes
			// => xml 경로는 WEB-INF에 있기 때문에 \classes를 없애야 함
			String path=file.getPath();// 운영체제따라 path가 달라
			path=path.replace("\\", File.separator);// // => 윈도우, / => 리눅스 자동 변환
			path=path.substring(0,path.lastIndexOf(File.separator));
			//System.out.println(path);
			// C:\webDev\webStudy\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\JSPMVCProject_1\WEB-INF
			path=path+File.separator+"application.xml";
			// C:\webDev\webStudy\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\JSPMVCProject_1\WEB-INF\application.xml
			//System.out.println(path);
			// XML 경로 => 모든 운영체제 => 모든 컴퓨터 호환
			
			// XML 파싱(XML 데이터 추출)
			// JSON => Jackson
			// DocumentBuilder(html => Jsoup)
			// 1. XML 파서기 생성 (XML HDML VML 모두 가능)
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			// 2. 파서기
			DocumentBuilder db=dbf.newDocumentBuilder();
			// 3. XML을 읽어서 트리형태로 메모리 저장 => Document
			Document doc=db.parse(new File(path));
			// 4. root 태그 읽기
			Element beans=doc.getDocumentElement();
			//System.out.println(beans.getTagName());
			// beans
			// 5. 같은 이름의 태그를 모아서 처리
			NodeList list=beans.getElementsByTagName("bean");
			// 6. bean에 있는 id,class의 값 추출
			// => MVC 구조에서는 유지보수 가능 => 공사중
			//System.out.println(list);
			for(int i=0;i<list.getLength();i++)
			{
				Element bean=(Element)list.item(i);
				String id=bean.getAttribute("id");
				String cls=bean.getAttribute("class");
				//System.out.println(id+":"+cls);
				
				// 메모리 할당 후 저장하도록 클래스 정보 읽기
				Class clsName=Class.forName(cls);
				// 클래스 정보를 읽기 위해서는 반드시 패키지.클래스명 으로 기재
				// 스프링 고정 => 추가 / 수정 / 삭제 => 문서
				// 메모리 할당
				Object obj=clsName.getDeclaredConstructor().newInstance();
				// clsName.invoke() => 메소드 이름을 몰라도 호출 가능
				clsMap.put(id, obj);
				System.out.println(id+":"+obj);
			}
		}catch(Exception ex) 
		{
			ex.printStackTrace();
		}
	}
    // 사용자 요청 받기
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 1. 사용자 요청 받기
		String uri=request.getRequestURI();
		uri=uri.substring(uri.lastIndexOf("/")+1);
		System.out.println(uri);
		
		// 2. 모델 클래스 찾기
		Model model=(Model)clsMap.get(uri);
		// 3. 처리 결과를 어떤 JSP에 보낼 것인가
		String jsp=model.handlerRequest(request);
		// 4. 이동 방식
		// sendRedirect / forward
		// request => 초기화 request를 전송: forward
		// _ok
		if(jsp.startsWith("redirect:"))
		{
			// 이미 만들어진 JSP로 이동 => sendRedirect
			jsp=jsp.substring(jsp.indexOf(":")+1);
			// return redirect: list.do
			response.sendRedirect(jsp);
		}
		else
		{
			// request에 담긴 값 출력 => forward
			RequestDispatcher rd=request.getRequestDispatcher(jsp);
			rd.forward(request, response);
		}
	}

}
