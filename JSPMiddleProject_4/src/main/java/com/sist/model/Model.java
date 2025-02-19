package com.sist.model;

import jakarta.servlet.http.HttpServletRequest;

public interface Model {
   public String handlerRequest(HttpServletRequest request);
   // 요청 처리 메소드 => 실제 Spring에 존재
}
