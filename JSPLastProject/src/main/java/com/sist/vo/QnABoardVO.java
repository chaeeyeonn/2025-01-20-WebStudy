package com.sist.vo;

import java.util.Date;

import lombok.Data;

@Data
public class QnABoardVO {
	private int no,hit,group_id,group_step,group_tab;
	private String name,id,subject,content,pwd,anok,dbday;
	private Date regdate;
}
