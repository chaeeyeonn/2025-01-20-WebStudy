package com.sist.dao;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sist.vo.FoodVO;

import java.io.*;
public class FoodDAO {
	private static SqlSessionFactory ssf;
    //SqlSesssionFactoryBean => String
	/*
	 *   XML 파싱
	 *   1. dom => xml 트리형태로 저장후 처리 CRUD 가능 / 속도 느림
	 *   2. sax => 대부분
	 *             태그를 한 개씩 읽어 처리
	 */
	static
	{
		try
		{
		  Reader reader=Resources.getResourceAsReader("Config.xml");
		  ssf=new SqlSessionFactoryBuilder().build(reader);
		}catch(Exception ex)
		{
			ex.printStackTrace();
			// 에러가 어려움
		}
	}
	/*
	 * <select id="foodListData" resultType="FoodVO">
    SELETE fno,name,poster,num 
    FROM (SELECT fno,name,poster,rownum as num 
    FROM (SELECT fno,name,poster 
    FROM food_menupan ORDER BY fno ASC))
    WHERE num BETWEEN #{start} AND #{end}
  </select>
  <select id="foodTotalPage" resultType="int">
    SELECT CEIL(COUNT(*)/12.0) FROM food_menupan
  </select>
	 */
	public static List<FoodVO> foodListData(Map map)
	{
		return ssf.openSession().selectList("foodListData",map);
		// 반환 필요 => 안 하면 횟수 충족 후 더이상 못 들어감
	}
	public static int foodTotalPage()
	{
		return ssf.openSession().selectOne("foodTotalPage");
	}
	/*
   <update id="hitIncrement" parameterType="int">
     UPDATE food_menupan SET
     hit=hit+1
     <include refid="where-fno"/>
   </update>
   <select id="foodDetailData" resultType="FoodVO" parameterType="int">
     SELECT name,type,phone,address,them,poster,images,time,parking,content,price,score,hit 
     FROM food_menupan 
	 <include refid="where-fno"/>
   </select>
	 */
	public static FoodVO foodDetailData(int fno)
	{
		ssf.openSession(true).update("hitIncrement",fno);
		// 조회수 증가 불가 => true 넣기 => autoCommit
		return ssf.openSession().selectOne("foodDetailData",fno);
	}
	
}
