package com.sist.model;
import java.text.SimpleDateFormat;
import java.util.*;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.*;
import com.sist.vo.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@Controller
public class QnABoardModel {
	@RequestMapping("qna/qna_list.do")
	public String qna_list(HttpServletRequest request,HttpServletResponse response)
	{
		String page=request.getParameter("page");
		   if(page==null)
			   page="1";
		   
		   int curpage=Integer.parseInt(page);
		   Map map=new HashMap();
		   map.put("start", (10*curpage)-9);
		   map.put("end",10*curpage);
		   List<QnABoardVO> list=QnABoardDAO.qnaListData(map);
		   int count=QnABoardDAO.qnaRowCount();
		   int totalpage=(int)(Math.ceil(count/10.0));
		   count=count-((10*curpage)-10);
		   
		   request.setAttribute("list", list);
		   request.setAttribute("totalpage", totalpage);
		   request.setAttribute("curpage", curpage);
		   request.setAttribute("count", count);
		   request.setAttribute("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		   
		request.setAttribute("main_jsp", "../qna/qna_list.jsp");
		return "../main/main.jsp";
	}
	@RequestMapping("qna/qna_insert.do")
	public String qna_insert(HttpServletRequest request,HttpServletResponse response)
	{
		request.setAttribute("main_jsp", "../qna/qna_insert.jsp");
		return "../main/main.jsp";
	}
	@RequestMapping("qna/qna_insert_ok.do")
	public String qna_insert_ok(HttpServletRequest request,HttpServletResponse response)
	{
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		String name=(String)session.getAttribute("name");
		
		QnABoardVO vo=new QnABoardVO();
		vo.setId(id);
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		
		QnABoardDAO.qnaInsert(vo);
		return "redirect:../qna/qna_list.do";
	}
	
	//관리자 모드
	@RequestMapping("qna/qna_admin_list.do")
	public String qna_admin_list(HttpServletRequest request,HttpServletResponse response)
	{
		String page=request.getParameter("page");
		   if(page==null)
			   page="1";
		   
		   int curpage=Integer.parseInt(page);
		   Map map=new HashMap();
		   map.put("start", (10*curpage)-9);
		   map.put("end",10*curpage);
		   List<QnABoardVO> list=QnABoardDAO.qnaAdminListData(map);
		   int count=QnABoardDAO.qnaAdminRowCount();
		   int totalpage=(int)(Math.ceil(count/10.0));
		   count=count-((10*curpage)-10);
		   
		   request.setAttribute("list", list);
		   request.setAttribute("totalpage", totalpage);
		   request.setAttribute("curpage", curpage);
		   request.setAttribute("count", count);
		   request.setAttribute("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		
		request.setAttribute("admin_jsp", "../qna/qna_admin_list.jsp");
		request.setAttribute("main_jsp", "../adminpage/admin_main.jsp");
		return "../main/main.jsp";
	}
	@RequestMapping("qna/qna_admin_insert.do")
	public String qna_admin_insert(HttpServletRequest request,HttpServletResponse response)
	{
		String gi=request.getParameter("gi");
		// vo 읽기
		QnABoardVO vo=QnABoardDAO.qnaAdminDetailData(Integer.parseInt(gi));
		request.setAttribute("vo", vo);
		request.setAttribute("admin_jsp", "../qna/qna_admin_insert.jsp");
		request.setAttribute("main_jsp", "../adminpage/admin_main.jsp");
		return "../main/main.jsp";
	}
	@RequestMapping("qna/qna_admin_insert_ok.do")
	public String qna_admin_insert_ok(HttpServletRequest request,HttpServletResponse response)
	{
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		String group_id=request.getParameter("group_id");
		
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		
		QnABoardVO vo=new QnABoardVO();
		vo.setId(id);
		vo.setGroup_id(Integer.parseInt(group_id));
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		
		QnABoardDAO.qnaAdminInsert(vo);
		return "redirect:../qna/qna_admin_list.do";
	}
	@RequestMapping("qna/qna_detail.do")
	public String qna_detail(HttpServletRequest request,HttpServletResponse response)
	{
		String no=request.getParameter("no");
		QnABoardVO vo=QnABoardDAO.qnaDetailData(Integer.parseInt(no));
		request.setAttribute("vo", vo);
		request.setAttribute("main_jsp", "../qna/qna_detail.jsp");
		return "../main/main.jsp";
	}
	@RequestMapping("qna/qna_delete.do")
	public String qna_delete(HttpServletRequest request,HttpServletResponse response)
	{
		String gi=request.getParameter("group_id");
		QnABoardDAO.qnaDelete(Integer.parseInt(gi));
		return "redirect:../qna/qna_list.do";
	}
	/*
	 *  - void: JavaScript 연동, ajax
	 *  - main.jsp: 화면 출력
	 *  - redirect: 이전 화면
	 *  
	 *  => 스프링과 동일
	 */
	@RequestMapping("qna/qna_update.do")
	public String qna_update(HttpServletRequest request,HttpServletResponse response)
	{
		String no=request.getParameter("no");
		
		// 사용자 요청 데이터 => ?no=1, submit
		/*
		 *  <input type=text name="id">
		 *  => ?id=aaa
		 *  post, get 모두 값을 보내는 것이지만
		 *  get은 url에 값이 보임 => 보안: post
		 */
		QnABoardVO vo=QnABoardDAO.qnaUpdateData(Integer.parseInt(no));
		// web은 모두 String으로 받음 => parameter는 int였으니 parseInt 필요
		request.setAttribute("vo", vo);
		request.setAttribute("main_jsp", "../qna/qna_update.jsp");
		return "../main/main.jsp";
	}
	// String => 화면 변경
	// void => ajax 화면 안 바꿈
	@RequestMapping("qna/qna_update_ok.do")
	public void qna_update_ok(HttpServletRequest request,HttpServletResponse response)
	{
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String no=request.getParameter("no");
		
		QnABoardVO vo=new QnABoardVO();
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setNo(Integer.parseInt(no));
		
		QnABoardDAO.qnaUpdate(vo);
	}
	@RequestMapping("qna/qna_admin_delete.do")
	public String qna_admin_delete(HttpServletRequest request,HttpServletResponse response)
	{
		String gi=request.getParameter("gi");
		QnABoardDAO.qnaAdminAnDelete(Integer.parseInt(gi));
		return "redirect:../qna/qna_admin_list.do";
	}
	
}
