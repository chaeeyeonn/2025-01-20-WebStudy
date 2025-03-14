package com.sist.dao;
import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.vo.*;
import com.sist.commons.*;
public class ReplyDAO {
	private static SqlSessionFactory ssf;
    static
	{
	   ssf=CreateSqlSessionFactory.getSsf();
	}
    /*
     * <select id="replyListData" resultType="ReplyVO" parameterType="ReplyVO">
  <!-- rno, type을 모두 보내줘야 하니 vo를 parameterType으로 (vo에 없는 변수라면 hashmap) -->
    SELECT cno,rno,type,id,name,sex,msg,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') as dbday
    FROM all_comment
    WHERE rno=#{rno} AND type=#{type}
    ORDER BY cno DESC
  </select>
  <!-- 댓글쓰기 -->
  <insert id="replyInsert" parameterType="ReplyVO">
    INSERT INTO all_comment(cno,rno,type,id,name,sex,msg,group_id)
    VALUES ((SELECT NVL(MAX(cno)+1,1) FROM all_comment),
    		#{rno},#{type},#{id},#{name},#{sex},#{msg},(SELECT NVL(MAX(group_id)+1,1) FROM all_comment))
     */
    public static List<ReplyVO> replyListData(ReplyVO vo)
    {
    	SqlSession session=ssf.openSession();
    	List<ReplyVO> list=session.selectList("replyListData",vo);
    	session.close();
    	return list;
    }
    public static void replyInsert(ReplyVO vo)
    {
    	SqlSession session=ssf.openSession(true);
    	session.insert("replyInsert",vo);
    	session.close();
    }
    /*
     * <select id="replyCount" resultType="int" parameterType="ReplyVO">
    SELECT COUNT(*) FROM all_comment
    WHERE rno=#{rno} AND type=#{type}
     */
    public static int replyCount(ReplyVO vo)
    {
    	SqlSession session=ssf.openSession();
    	int count=session.selectOne("replyCount",vo);
    	session.close();
    	return count;
    }
}
