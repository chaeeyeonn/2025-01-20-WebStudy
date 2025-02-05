package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.vo.*;

public class MusicDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	private static MusicDAO dao;
	
	public MusicDAO()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception ex) {}
	}
	public static MusicDAO newInstance()
	{
		try
		{
			if(dao==null)
				dao=new MusicDAO();
		}catch(Exception ex) {}
		return dao;
	}
	public void getConnection()
	{
		try
		{
			conn=DriverManager.getConnection(URL,"hr","happy");
		}catch(Exception ex) {}
	}
	public void disConnection()
	{
		try
		{
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(Exception ex) {}
	}
	
	// 목록
	public List<MusicVO> MusicListData(int page)
	{
		List<MusicVO> list=new ArrayList<MusicVO>();
		try
		{
			getConnection();
			String sql="SELECT mno,title,poster,num "
					 +"FROM (SELECT /*+ INDEX_ASC(genie_music gm_fno_pk)*/mno,title,poster,rownum as num "
					 +"FROM (SELECT mno,title,poster "
					 +"FROM genie_music)) "
					 + "WHERE num BETWEEN ? AND ?";
			ps=conn.prepareStatement(sql);
			int rowSize=12;
			int start=(rowSize*page)-(rowSize-1);
			int end=rowSize*page;
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
			    MusicVO vo=new MusicVO();
			    vo.setMno(rs.getInt(1));
			    vo.setTitle(rs.getString(2));
			    vo.setPoster(rs.getString(3));
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
	
	// 총페이지
	public int musicTotalPage()
	{
		int total=0;
		try
		{
			getConnection();
			String sql="SELECT CEIL(COUNT(*)/12.0) FROM genie_music";
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
	// 상세보기
	public MusicVO MusicDetailData(int mno)
	{
		MusicVO vo=new MusicVO();
		try
		{
			getConnection();
			String sql="UPDATE genie_music SET "
					+ "hit=hit+1 "
					+ "WHERE mno="+mno;
			ps=conn.prepareStatement(sql);
			ps.executeUpdate();
			/*
			 * private int mno,cno,idcrement,hit;
	           private String title,singer,album,poster,state,key;
			 */
			sql="SELECT title,singer,album,poster,state "
				+"FROM genie_music "
				+"WHERE mno="+mno;
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setTitle(rs.getString(1));
			vo.setSinger(rs.getString(2));
			vo.setAlbum(rs.getString(3));
			vo.setPoster(rs.getString(4));
			vo.setState(rs.getString(5));
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
		// 쿠키 데이터
		public MusicVO musicCookieData(int mno)
		{
			MusicVO vo=new MusicVO();
			try
			{
				getConnection();
				String sql="SELECT mno,title,poster "
						 +"FROM genie_music "
						 +"WHERE mno="+mno;
				ps=conn.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				rs.next();
				vo.setMno(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setPoster(rs.getString(3));
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
	// 음악 장르별 검색
	public List<MusicVO> musicTypeFind(int page,int cno)
	{
		List<MusicVO> list=new ArrayList<MusicVO>();
		try
		{
			getConnection();
			int rowSize=12;
			int start=(rowSize*page)-(rowSize-1);
			int end=rowSize*page;
			String sql="SELECT mno,title,singer,poster,num "
					+"FROM (SELECT mno,title,singer,poster,rownum as num "
					+"FROM (SELECT mno,title,singer,poster "
					+"FROM genie_music "
					+"WHERE cno=?)) "
					+"WHERE num BETWEEN ? AND ?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, cno);
			ps.setInt(2, start);
			ps.setInt(3, end);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				MusicVO vo=new MusicVO();
				vo.setMno(rs.getInt(1));
				vo.setTitle(rs.getNString(2));
				vo.setSinger(rs.getString(3));
				vo.setPoster(rs.getString(4));
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
	public int musicTypeTotalPage(int cno)
	{
		int total=0;
		try
		{
			getConnection();
			String sql="SELECT CEIL(COUNT(*)/12.0) "
					  +"FROM genie_music "
					  +"WHERE cno=?";
			ps=conn.prepareStatement(sql);
		    ps.setInt(1, cno);
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
}
