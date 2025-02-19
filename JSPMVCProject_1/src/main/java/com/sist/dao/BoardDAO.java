package com.sist.dao;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;

import com.sist.vo.BoardVO;

import java.io.*;
public class BoardDAO {
   private static SqlSessionFactory ssf;
   // 초기화 => XML에 등록된 데이터 전송 => ssf
   static
   {
	   try
	   {
		   // 1. XML 읽기
		   Reader reader=Resources.getResourceAsReader("Config.xml");
		   // 2. XML 파싱
		   ssf=new SqlSessionFactoryBuilder().build(reader);
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
   }
   // 목록
   /*
    <select id="boardListData" resultType="BoardVO" parameterType="hashmap">
    SELECT no,subject,name,regdate,hit,num 
    FROM (SELECT no,subject,name,regdate,hit,rownum as num 
    FROM (SELECT no,subject,name,regdate,hit 
    FROM board ORDER BY no DESC))
    WHERE num BETWEEN #{start} AND #{end}
    */
   public static List<BoardVO> boardListData(Map map)
   {
	   SqlSession session=ssf.openSession();
	   List<BoardVO> list=session.selectList("boardListData",map);
	   session.close();
	   return list;
   }
   //총페이지
   /*
    <select id="boardTotalPage" resultType="int">
    SELECT CEIL(COUNT(*)/10.0) FROM board
    */
   public static int boardTotalPage()
   {
	   SqlSession session=ssf.openSession();
	   int total=session.selectOne("boardTotalPage");
	   session.close();
	   return total;
   }
   // 게시물 등록
   /*
    <insert id="boardInsert" parameterType="BoardVO">
    <selectKey keyProperty="no" resultType="int" order="BEFORE">
      SELECT NVL(MAX(no)+1,1) as no FROM board
    </selectKey>
    INSERT INTO board VALUES(#{no},#{name},#{subject},#{content},#{pwd},SYSDATE,0)
    */
   public static void boardInsert(BoardVO vo)
   {
	   SqlSession session=ssf.openSession();
	   // commit이 없음
	   session.insert("boardInsert",vo);// insert 대신 delete,update 써도 되긴 함 => executeUpdate()가 같으므로
	   session.commit();//update,insert,delete
	   // 데이터 갱신
	   session.close();
   }
}
