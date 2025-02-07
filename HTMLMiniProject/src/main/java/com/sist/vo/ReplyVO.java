package com.sist.vo;
import java.util.*;

import lombok.Data;
/*
RNO     NOT NULL NUMBER       
FNO              NUMBER       
ID               VARCHAR2(20) 
NAME    NOT NULL VARCHAR2(51) 
MSG     NOT NULL CLOB         
REGDATE          DATE 

  로그인 => id, name, sex(서버에 저장)
           => 모든 파일에서 사용 가능(로그아웃 / 브라우저 종료시까지)
              ==> 전역변수
               => 1. 사용자 정보
                  2. 장바구니
                  3. 경매
               => 저장 기간: 1800초 (30분)
               => session: 프로그램이 진행되는 동안
                  => 각 클라이언트당 1개 배정 => request
                     ----------브라우저에 대해
                     
   VO => 컬럼 외의 변수 설정
        => JDBC / MyBatis
   Entity => 컬럼 외의 변수는 설정 불가능
        => JPA => SQL을 자동화
 */
@Data
public class ReplyVO {
	private int rno,fno;
	private String id,name,msg,dbday;
	private Date regdate;
}
