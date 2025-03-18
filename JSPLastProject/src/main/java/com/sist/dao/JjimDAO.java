package com.sist.dao;
import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.vo.*;
import com.sist.commons.*;
public class JjimDAO {
	private static SqlSessionFactory ssf;
	static
	{
		ssf=CreateSqlSessionFactory.getSsf();
	}
	/*
	    SqlSession session=null;
		try
		{
			session=ssf.openSession();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if(session!=null)
				session.close();
		}
	 */
	/*
	 * <select id="jjimCheckCount" resultType="int" parameterType="JjimVO">
     SELECT COUNT(*) FROM all_jjim
     WHERE id=#{id} AND rno=#{rno} AND type=#{type}
	 */
	public static int jjimCheckCount(JjimVO vo)
	{
		SqlSession session=null;
		int count=0;
		try
		{
			session=ssf.openSession();
			count=session.selectOne("jjimCheckCount",vo);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		return count;
	}
	/*
	 * <insert id="jjimInsert" parameterType="JjimVO">
     INSERT INTO all_jjim VALUES(
       aj_jno_seq.nextval,#{rno},#{type},#{id},SYSDATE
     )
	 */
	public static void jjimInsert(JjimVO vo)
	{
		    SqlSession session=null;
			try
			{
				session=ssf.openSession(true);
				session.insert("jjimInsert",vo);
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
			finally
			{
				if(session!=null)
					session.close();
			}
	}
	/*
	 * select id="jjimFoodListData" resultMap="jjimMap" parameterType="string">
     SELECT jno,aj.type,rno,name,poster,fno
     FROM all_jjim aj,project_food pf
     WHERE aj.rno=pf.fno
     AND id=#{id} AND aj.type=1
     ORDER BY jno DESC
	 */
	public static List<JjimVO> jjimFoodListData(String id)
	{
		SqlSession session=null;
		List<JjimVO> list=null;
		try
		{
			session=ssf.openSession(true);
			list=session.selectList("jjimFoodListData",id);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		return list;
	}
	/*
	 * <delete id="jjimDelete" parameterType="int">
     DELETE FROM all_jjim
     WHERE jno=#{jno}
	 */
	public static void jjimDelete(int jno)
	{
		    SqlSession session=null;
			try
			{
				session=ssf.openSession(true);
				session.delete("jjimDelete",jno);
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
			finally
			{
				if(session!=null)
					session.close();
			}
	}
}
