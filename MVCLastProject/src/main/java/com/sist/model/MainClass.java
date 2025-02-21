package com.sist.model;

import java.io.File;

public class MainClass {
	public static void main(String[] args) {
		String path="C:\\webDev\\webStudy\\MVCLastProject\\src\\main\\java";
		String pack="com.sist.model";
		path=path+pack.replace(".","\\");
		try
		{
			File dir=new File(path);
			File[] files=dir.listFiles();
			for(File file:files)
			{
				String name=file.getName();
				String ext=name.substring(name.lastIndexOf(".")+1);
				if(ext.equals("java"))//java 파일만 가져오기 위함
				{
				  String clsName=name.substring(0,name.lastIndexOf("."));
				  String packName=pack+"."+clsName;
				  System.out.println(packName);
				}
			}
		}catch(Exception ex)
		{
			
		}
	}
}
  
