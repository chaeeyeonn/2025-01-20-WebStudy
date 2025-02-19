package com.sist.dao;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;
import com.sist.vo.*;
public class BoardDAO {
   private static SqlSessionFactory ssf;
   // xml의 데이터 저장 => SqlSessionFactory
   static
   {
	   try
	   {
		   Reader reader=Resources.getResourceAsReader("Config.xml");
		   ssf=new SqlSessionFactoryBuilder().build(reader);
	   }catch(Exception ex)
	   {
		   System.out.println(ex.getMessage());
	   }
   }
   // 기능 설정
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
	   session.close();// POOL 반환
	   return list;
   }
   /*
    <select id="boardTotalPage" resultType="int">
    SELECT CEIL(COUNT(*)/10.0) FROM board
    */
   public static int boardTotalPage()
   {
	   SqlSession session=ssf.openSession();
	   int total=session.selectOne("boardTotalPage");
	   session.close();// POOL 반환
	   return total;
   }
   /*
    * <insert id="boardInsert" parameterType="BoardVO">
    <!-- MyBatis 시퀀스 만들기 -->
    <selectKey keyProperty="no" resultType="int" order="BEFORE">
      SELECT NVL(MAX(no)+1,1) as no FROM board
    </selectKey>
    INSERT INTO board VALUES(#{no},#{name},#{subject},#{content},#{pwd},SYSDATE,0)
    */
   public static void boardInsert(BoardVO vo)
   {
	   SqlSession session=ssf.openSession();
	   /*
	    *  ssf.openSession(); setAutoCommit(false)
	    *  ssf.openSession(true); setAutoCommit(true)
	    */
	   session.insert("boardInsert",vo);
	   session.commit();
	   session.close();
   }
   // insert / update / delete / select 관리 => SqlSession
   // SqlSession은 SqlSessionFactory에서 생성
   // ssf.openSession()
   // connection / preparestatement => close() => 반환
}
