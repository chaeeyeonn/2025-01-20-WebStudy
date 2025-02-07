package com.sist.main;

public class ChangeServlet {
	// 출력하지 않는 서블릿은 포함하지 않음 => FoodBeforeDetail => 쿠키만 저장함
	private static String[] pages= {
		"",
		"FoodList", // mode 1번
		"FoodDetail", // mode 2번
		"FoodTypeFind", // mode 3번
		"FoodFind", // mode 4번 => 순서 및 번호 지정함
		"MusicList",
		"MusicDetail",
		"MusicTypeFind",
		"MusicFind"
	};
	public static String pageChange(int mode)// 매번 클래스 이름 적기 힘드니 배열 통해 int 변수로 지정
	{
		return pages[mode];
	}
}
