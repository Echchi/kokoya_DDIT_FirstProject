package service;

import controller.Controller;
import dao.MemberDAO;
import util.ScanUtil;
import util.View;

public class MypageService {
	
	MemberDAO dao = MemberDAO.getInstance();
	
	private static MypageService instance = null;
	private  MypageService() {}
	public static MypageService getInstance() {
		if(instance == null) instance = new MypageService();
		return instance;
	}
	public int mypageHome() {
		
		Controller.mypage = true;
		
		while(true) {
			clearScreen();
			Controller.loginInfo = dao.userInfo(Controller.loginInfo.get("MEM_ID"));
			System.out.println("┌==================마이페이지==================┐");
			System.out.println("│                                              │");
			System.out.println("│              1. 나의 정보 보기               │");
			System.out.println("│                                              │");
			System.out.println("│              2. 강의 수강 내역               │");
			System.out.println("│                                              │");
			System.out.println("│              3. 장바구니 보기                │");
			System.out.println("│                                              │");
			System.out.println("│              4. 나의 게시글 보기             │");
			System.out.println("│                                              │");
			System.out.println("│              5. 마일리지 충전                │");
			System.out.println("│                                              │");
			System.out.println("│              0. 돌아가기                     │");
			System.out.println("│                                              │");
			System.out.println("└──────────────────────────────────────────────┘");
			System.out.println(" 입력 >> ");
			System.out.println(" ───────────────────────────────────────────────");
			switch(ScanUtil.nextInt()) {
			case 1:
				return View.MEMBERINFO;
			case 2:
				return View.CLASS_RSVLIST;
			case 3:
				clearScreen();
				return View.MARKET_CARTLIST;
			case 4:
				clearScreen();
				return View.ANI_MYANI;
			case 5:
				return View.MEM_MILE;
			case 0:
				Controller.mypage=false;
				return View.MAINPAGE;
			default:
				System.out.println("잘못입력");
			}
			
		}
	
		
	}
			
	
	public static void clearScreen() {
		for(int i = 0; i < 50 ; i++) {
			System.out.println();
		}
	}
	

}
