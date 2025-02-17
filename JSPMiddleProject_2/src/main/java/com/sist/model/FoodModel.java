package com.sist.model;
/*
 *         요청 request
 *     JSP =========== FoodModel ============= 데이터베이스 연동
 *      |                  |
 *      |             List<FoodVO>
 *      |                  |
 *      <-------------------
 *       request(List<FoodVO>)
 *          |
 *         ${}   => MV model
 *         
 *      JSP ========== Controller ======> Model <=======> DB
 *                   <========== <=======
 *      MVC구조 방식 => Spring(MVC)
 *      
 *      게시판
 *        BoardList / BoardInsert / BoardUpdate / BoardDelete / BoardFind 
 *                    |BoardInstertOk   |BoardUpdateOk  |BoardDeleteOk
 *      => class Board
 *        {
 *            메소드 8개
 *        }
 */
import java.util.*;
import com.sist.dao.*; 
import com.sist.vo.*;

import jakarta.servlet.http.HttpServletRequest;
public class FoodModel {
   public void foodListData(HttpServletRequest request)
   {
	   String page=request.getParameter("page");
		if(page==null)
			 page="1";// 초기값 지정 => 사용자가 page를 지정하지 않았다면
		// 현재 페이지 설정
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		map.put("start", (12*curpage)-11);
		map.put("end",12*curpage);
		// 현재 페이지에 대한 데이터
		List<FoodVO> list=FoodDAO.foodListData(map);
		
		// 전체 페이지
		int totalpage=FoodDAO.foodTotalPage();
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		// 만약 totalpage보다 endpage가 클 경우
		if(endPage>totalpage)
			endPage=totalpage;
		
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
   }
   public void foodDetailData(HttpServletRequest request)
   {
	   String fno=request.getParameter("fno");
	   FoodVO vo=FoodDAO.foodDetailData(Integer.parseInt(fno));
	   
	   request.setAttribute("vo", vo);
   }
}
