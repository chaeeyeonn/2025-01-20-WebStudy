package com.sist.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.sist.model.DeleteModel;
import com.sist.model.InsertModel;
import com.sist.model.ListModel;
import com.sist.model.UpdateModel;
/*
 *   장점: Front / Back
 *    구성요소가 명확히 나눠져 있다
 *    데이터 흐름이 단방향이기에 분석이 쉽다
 *    재사용에 좋다
 *    리팩토링 비용이 낮아진다
 *    => 가독성
 *   단점:
 *    컨트롤러가 복잡해지고 코드 중복이 발생
 *    뷰와 모델을 수동으로 처리
 *    양방향 통신으로 변경하면 추가 작업 필요
 *    컨트롤러에 의존하는 경향
 *   ---------분산(MSA)
 *   
 *   ***RestFul / MVC 구조 / Cookie, Session
 *   
 *   - 역할
 *   1. Controller: 서빙
 *   2. Model: 주방
 *   3. View: 손님
 *   
 *   View => Controller => Model => Controller => View
 */

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 1. 사용자 주문 받기
		String cmd=request.getParameter("cmd");
		if(cmd==null)
			cmd="list";
		// 2. 주방 요청 => 해당 Model 찾기
		// 2-1. 테이블 지정 => 어디서 요청했는가
		String jsp="";
		if(cmd.equals("list"))
		{
			ListModel model=new ListModel();
			model.execute(request);
			jsp="list.jsp";
		}
		else if(cmd.equals("insert"))
		{
			InsertModel model=new InsertModel();
			model.execute(request);
			jsp="insert.jsp";
		}
		else if(cmd.equals("update"))
		{
			UpdateModel model=new UpdateModel();
			model.execute(request);
			jsp="update.jsp";
		}
		else if(cmd.equals("delete"))
		{
			DeleteModel model=new DeleteModel();
			model.execute(request);
			jsp="delete.jsp";
		}
		// 3. 주방에서 음식 받아오기 => request에 담기
		// 4. 테이블에 올리기 => jsp 찾아서 보내기
		RequestDispatcher rd=request.getRequestDispatcher("board/"+jsp);
		rd.forward(request, response);
	}

}
