package com.sist.model;
import java.io.PrintWriter;
import java.sql.RowId;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.*;
import com.sist.vo.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Controller
public class FoodModel {
	@RequestMapping("food/food_find.do")
	public String food_find(HttpServletRequest request,HttpServletResponse response)
	{
		// 화면 이동 => String
		return "../food/food_find.jsp";
	}
	@RequestMapping("food/find_js.do")
	// 화면 이동 안 함 => void
	public void find_js(HttpServletRequest request,HttpServletResponse response)
	{
		// 오라클 데이터 읽어서 JSON으로 변경 => JavaScript 전송
		// 페이지 받기
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		
		String address=request.getParameter("fd");
		if(address==null)
			address="마포";
		int curpage=Integer.parseInt(page);
		
		// List
		Map map=new HashMap();
		int rowSize=12;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		
		map.put("start", start);
		map.put("end", end);
		map.put("address", address);
		
		List<FoodVO> list=FoodDAO.foodFindData(map);
		
		// 총페이지
		int totalpage=FoodDAO.foodFindTotalPage(address);
		
		// 블럭
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalpage)
			endPage=totalpage;
		// [{},{},{},...] => List 안에 VO를 담는다
		// List => JSONArray / VO => JSONObject
		// 자바+자바스크립트 이어주기 => JSON
		JSONArray arr=new JSONArray();
		int i=0;
		for(FoodVO vo:list)
		{
			JSONObject obj=new JSONObject();
			obj.put("fno", vo.getFno());
			obj.put("name", vo.getName());
			obj.put("poster", "https://www.menupan.com"+vo.getPoster());
			if(i==0)
			{
				obj.put("curpage", curpage);
				obj.put("totalpage", totalpage);
				obj.put("startPage", startPage);
				obj.put("endPage", endPage);
			}
			arr.add(obj);
			i++;
		}
		try
		{
			response.setContentType("text/plain;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.write(arr.toJSONString());
		}catch(Exception ex) {}
	}
	@RequestMapping("food/detail_js.do")
	public void food_detail(HttpServletRequest request,HttpServletResponse response)
	{
		String fno=request.getParameter("fno");
		FoodVO vo=FoodDAO.foodFindDetailData(Integer.parseInt(fno));
		
		JSONObject obj=new JSONObject();
		obj.put("poster", "https://www.menupan.com"+vo.getPoster());
		obj.put("name", vo.getName());
		obj.put("score", vo.getScore());
		obj.put("address", vo.getAddress());
		obj.put("phone", vo.getPhone());
		obj.put("type", vo.getType());
		obj.put("time", vo.getTime());
		obj.put("parking", vo.getParking());
		obj.put("them", vo.getThem());
		obj.put("content", vo.getContent());
		
		try
		{
			response.setContentType("text/plain;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.write(obj.toJSONString());
		}catch(Exception ex) {}
	}
}

