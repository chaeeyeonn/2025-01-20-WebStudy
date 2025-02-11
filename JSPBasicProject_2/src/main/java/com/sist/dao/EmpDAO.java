package com.sist.dao;
import java.util.*;
import java.sql.*;
/*
 *   JSP 기초
 *   JSP middle
 */
public class EmpDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	private static EmpDAO dao;
	
	public EmpDAO()
	{
		try
		{
			Class.forName("oracle.jdbc.dirver.OracleDriver");
		}catch(Exception ex) {}
	}
	
	public static EmpDAO newInstance()
	{
		if(dao==null)
			dao=new EmpDAO();
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
	
	public List<EmpVO> empListData()
	{
		List<EmpVO> list=new ArrayList<EmpVO>();
		try
		{
			getConnection();
			String sql="SELECT empno,ename,job,hiredate,sal "
					  +"FROM emp ORDER BY empno ASC";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
		    while(rs.next())
		    {
		    	EmpVO vo=new EmpVO();
		    	vo.setEmpno(rs.getInt(1));
		    	vo.setEname(rs.getString(2));
		    	vo.setJob(rs.getString(3));
		    	vo.setHiredate(rs.getDate(4));
		    	vo.setSal(rs.getInt(5));
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
}
