package com.sist.dao;
import com.sist.vo.*;
// DBCP(web) / JDBC
import java.util.*;
import java.sql.*;
public class BoardDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	private static BoardDAO dao;
	
	public BoardDAO()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception ex) {}
	}
	public static BoardDAO newInstance()
	{
		if(dao==null)
			dao=new BoardDAO();
		return dao;
	}
	//오라클 연결
	public void getConnection()
	{
		try
		{
			conn=DriverManager.getConnection(URL,"hr","happy");
		}catch(Exception ex) {}
	}
	//오라클 닫기
	public void disConnection()
	{
		try
		{
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(Exception ex) {}
	}
	// 기능
	// 1. 목록
	public List<BoardVO> BoardListData(int page)
	{
		List<BoardVO> list=new ArrayList<BoardVO>();
		try
		{
			getConnection();
			String sql="SELECT no,subject,name,TO_CHAR(regdate,'YYYY-MM-DD'),hit,num "
					+ "FROM (SELECT no,subject,name,regdate,hit,rownum as num "
					+ "FROM (SELECT no,subject,name,regdate,hit "
					+ "FROM htmlBoard ORDER BY no DESC)) "
					+ "WHERE num BETWEEN ? AND ?";
			ps=conn.prepareStatement(sql);
			int rowSize=10;
			int start=(rowSize*page)-(rowSize-1);
			int end=rowSize*page;
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				BoardVO vo=new BoardVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setDbday(rs.getString(4));
				vo.setHit(rs.getInt(5));
				list.add(vo);
			}
			rs.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return list;
	}
	// 1-1. 총페이지
	public int BoardTotalPage()
	{
		int total=0;
		try
		{
			getConnection();
			String sql="SELECT CEIL(COUNT(*)/10.0) FROM htmlBoard";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			total=rs.getInt(1);
			rs.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return total;
	}
	// 2. 상세보기
	public BoardVO boardDetailData(int no)
	{
		BoardVO vo=new BoardVO();
		try
		{
			getConnection();
			String sql="UPDATE htmlboard SET "
					+ "hit=hit+1 "
					+ "WHERE no="+no;
			ps=conn.prepareStatement(sql);
			ps.executeUpdate();
		    sql="SELECT no,name,subject,content,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS'),hit "
		    		+ "FROM htmlboard "
		    		+ "WHERE no="+no;
		    ps=conn.prepareStatement(sql);
		    ResultSet rs=ps.executeQuery();
		    rs.next();
		    vo.setNo(rs.getInt(1));
		    vo.setName(rs.getString(2));
		    vo.setSubject(rs.getString(3));
		    vo.setContent(rs.getString(4));
		    vo.setDbday(rs.getString(5));
		    vo.setHit(rs.getInt(6));
		    rs.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return vo;
	}
	// 3. 글쓰기
	//=> 웹프로그램(감 익히기) => 화면 이동 -> ㅇ'''''''
	public void boardInsert(BoardVO vo)
	{
		try
		{
			getConnection();
			String sql="INSERT INTO htmlboard(no,name,subject,content,pwd) "
					 +"VALUES(hb_no_seq.nextval,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			
			ps.executeUpdate();// commit 포함
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
	}
	// 4. 수정
	// 5. 삭제
	public boolean boardDelete(int no,String pwd)
	{
		/*
		 *   오라클 => 사이트에 필요한 데이터 저장
		 *   => SQL문장 이용
		 *   자바 => 오라클 연동 / 브라우저 연동
		 *         => 결과값 받아 브라우저로 전송
		 *         사용자 요청 받는 경우
		 *   HTML/CSS => 브라우저 화면 출력
		 */
		boolean bCheck=false;
		try
		{
			getConnection();
			// 1. pwd 체크
			String sql="SELECT pwd FROM htmlboard "
					+ "WHERE no="+no;
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			String db_pwd=rs.getString(1);
			rs.close();
			
			if(db_pwd.equals(pwd))
			{
				bCheck=true;
				sql="DELETE FROM htmlboard "
				   +"WHERE no="+no;
				ps=conn.prepareStatement(sql);
				ps.executeUpdate();
			}
			else
			{
				bCheck=false;
			}
			// 2. 삭제
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return bCheck;
	}
}
