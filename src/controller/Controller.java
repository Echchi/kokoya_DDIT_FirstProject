package controller;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import service.AdminService;
import service.AnigramService;
import service.ClassService;
import service.MarketService;
import service.MemberService;
import service.MypageService;
import util.ScanUtil;
import util.View;

public class Controller {
	
	

	// 세션
	static public boolean login = false;
	static public Map<String, Object> loginInfo = null;
	static public boolean mypage = false;
	static public boolean  admin = false;
	
	// 객체(맴버, 클래스, 마켓, 마이페이지)
	MemberService memberService = MemberService.getInstance();
	AnigramService anigramService = AnigramService.getInstance();
	ClassService classService = ClassService.getInstance(); 
	MypageService mypageService = MypageService.getInstance();
	MarketService marketService = MarketService.getInstance();
	AdminService adminService = AdminService.getInstance();
	
	// 타이머 전용
	static public int time;
	static public int count = 0;
	
	public static void main(String[] args) {
		new Controller().start();
	}
	
	private void start() {
		int view = View.HOME;  
		while(true) {		
			System.out.println();
			switch (view) {
			// 팀장
			case View.HOME: view = home(); break;  // 1
			
			// 메인화면 
			// 애니그램 ,마이페이지, 구매, 강좌
			case View.MAINPAGE: view = mainPage(); break;
			
			// 관리자
			case View.ADMIN: view = adminService.adminPage(); break;
			// 강의(관리자)
			case View.ADMIN_CLASSMAIN: view = adminService.adminClass(); break; // 클래스 메인  
			case View.ADMIN_CALSS_ADD: view = adminService.adminClassAdd(); break; // // 강의 등록
			
			// 마켓(관리자)
			case View.ADMIN_MARKET: view = adminService.adminMarket(); break;
			case View.ADMIN_REVIEWMAIN: view = adminService.adminReview(); break;
			case View.ADMIN_MARKETPROD: view = adminService.adminMarketProd(); break;
			case View.ADMIN_PROD_DELETE: view = adminService.adminMarketProddelete(); break;
			case View.ADMIN_PROD_REVIEW: view = adminService.adminMarketProdReview(); break;
			case View.ADMIN_REVIEW_DELETE: view = adminService.adminReviewDelete(); break;
					
			// 게시판(관리자)
			case View.ADMIN_ANIGRAM: view = adminService.adminAnigram(); break;
			case View.ADMIN_REPLY: view = adminService.adminReply(); break;

		
			
			
			// 멤버(회원가입, 로그인)
			case View.MEMBER_LOGIN: view = memberService.login(); break;
			case View.MEMBER_SIGNUP: view = memberService.signUp(); break;
			case View.MEMBERINFO: view = memberService.info(); break;
			case View.MEM_MILE: view = memberService.addMile(); break;
			
			// 애니그램
			case View.ANIGRAM: view = anigramService.list(); break;
			case View.ANI_DETAIL: view = anigramService.detail(); break;
			case View.ANI_MYANI: view = anigramService.myAnigram(); break;
			case View.ANI_FRANI: view = anigramService.friendAni(); break;	
			case View.ANI_MYINSERT: view = anigramService.insertAni(); break; //
			case View.ANI_MYUPDATE: view = anigramService.updateAni(); break;
			case View.ANI_MYDELETE: view = anigramService.deleteAni(); break;  // 게시글 삭제
			case View.ANI_FRINSERT: view = anigramService.addFriend(); break;  // 친구등록
			case View.ANI_FRDELETE: view = anigramService.deleteFriend(); break;  // 친구삭제
			case View.ANI_FRDVIEW: view = anigramService.viewFriend(); break;
			case View.ANI_REDELETE: view = anigramService.replyDelete(); break;
			
			// 강좌
			case View.CLASS_LIST : view = classService.list(); break;
			case View.CLASS_DETAIL : view = classService.detail(); break;
			case View.CLASS_RSV: view = classService.rsv(); break;
			case View.CLASS_RSVLIST : view = classService.rsvlist(); break;

			// 마켓 
			case View.MARKET: view = marketService.choiceMenu(); break;
			case View.MARKET_PRODTYPE: view = marketService.showList();break;
			case View.MARKET_PRODINFO: view = marketService.showProdInfo();break;
			case View.MARKET_BUY: view = marketService.buyProcess();break;
			case View.MARKET_CARTINSERT: view = marketService.cartInsert();break;
			case View.MARKET_CARTINSERT2: view = marketService.cartInsert2();break;
			case View.MARKET_CARTLIST: view = marketService.cartList();break;
			case View.MARKET_CARTDELETE: view = marketService.cartDelete();break;
			case View.MARKET_CARTDELETE2: view = marketService.cartDelete2();break;
			case View.MARKET_CARTPURCHASE: view = marketService.cartPurchase();break;
			case View.MARKET_CARTPURCHASECHOICE: view = marketService.cartPurchaseChoice();break; //선택구매
			case View.MARKET_CARTPURCHASE2: view = marketService.cartPurchase2();break; //선택구매
			case View.MARKET_BUYLIST: view = marketService.buyList();break;
			case View.MARKET_MYINFO: view = marketService.myInfo();break;
			case View.MARKET_WRITEREVIEW: view = marketService.review();break;
			
		
			
			// 마이페이지
			case View.MYPAGE: view = mypageService.mypageHome(); break;

			}
		}
	}

	private int mainPage() {
		
		while(true) {
			
			
			System.out.println("┌================메인 화면================┐");
			System.out.println("│                                         │");
			System.out.println("│             1. 애니멀그램               │");		
			System.out.println("│             ──────────────              │");
			System.out.println("│             2.    강좌                  │");
			System.out.println("│             ──────────────              │");
			System.out.println("│             3.    구매                  │");
			System.out.println("│             ──────────────              │");
			System.out.println("│             4. 마이페이지               │");
			System.out.println("│             ──────────────              │");
			System.out.println("│             5.  로그아웃                │");
			System.out.println("│             ──────────────              │");
			System.out.println("└─────────────────────────────────────────┘");
			System.out.println(" 입력 >> ");
			System.out.println("-------------------------------------------");
			
			switch(ScanUtil.nextInt()) {
			case 1:
				return View.ANIGRAM;
			case 2:
				return View.CLASS_LIST;
			case 3:
				return View.MARKET;
			case 4:
				return View.MYPAGE;
			case 5:
				login = false;
				loginInfo = null;
				return View.HOME;
			default:
				System.out.println("잘못입력함");
				break;
			
		}
			
	
		}
	
	}

	private int home() {
		 


		System.out.println(" ⠀⠀⠀    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣠⣄⠀⠀⠀⠀⠀⠀⠀⠀⣠⣄⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⠀⠀⠀  ");
		System.out.println(" ⠀⠀⠀    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⠟⢻⣿⠀⠀⠀⠀⠀⠀⠀⠀⣿⡟⠻⣿⣦⡀⠀⠀⠀⠀⠀ ⠀⠀⣿⣿⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣾⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⠀⠀⠀  ");
		System.out.println(" ⠀⠀⠀    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⡟⠁⠀⢸⣿⠀⠀⠀⠀⠀⠀⠀⠀⠛⠃⠀⠈⢿⣷⠀⠀⠀⠀⠀⠀  ⠀⣿⡏⠙⢿⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠰⡿⠋⢹⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⠀⠀⠀  ");
		System.out.println(" ⠀⠀⠀    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀⢸⣿⣤⣤⣤⣤⣤⣤⣤⣤⣤⡄⠀ ⢸⣿⠀⠀⠀⠀ ⠀⠀   ⣿⡇⠀⠀⠙⢿⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⡆⠀ ⠀  ⢸⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⠀⠀⠀  ");
		System.out.println(" ⠀⠀⠀    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀⠈⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠁⠀ ⢸⣿⠀⠀⠀⠀⠀   ⠀ ⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀     ⠀ ⢸⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⠀⠀⠀  ");
		System.out.println(" ⠀⠀⠀    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⠀⠀⢸⣿⠀⠀⠀⠀⠀    ⠀⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀    ⠀  ⢸⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⠀⠀⠀  ");
		System.out.println(" ⠀⠀⠀    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀⠀⣴⣦⡄⠀⠀⠀⠀⢀⣴⣤⠀⠀⠀⢸⣿⠀⠀⠀⠀ ⠀⠀⠀⣿⡇⠀⠀⠀⠠⣾⣷⠄⠀⠀⠀⠀⠀⢠⣿⣷⠀⠀⠀⠀   ⢸⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⠀⠀⠀  ");
		System.out.println(" ⠀⠀⠀    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀⠀⠛⠛⠁⠀⠀⠀⠀⠈⠛⠛⠀⠀⠀⢸⣿⠀⠀⠀⠀⠀ ⠀⠀⣿⡇⠀⠀⠀⠀⠉⠉⠀⠀⠀⠀⠀⠀⠀⠉⠉⠀⠀⠀⠀    ⢸⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⠀⠀⠀  ");
		System.out.println(" ⠀⠀⠀    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⠀ ⢸⣿⠀⠀⠀⠀⢠⣤⣤⣿⣇⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⠀⣀⣀  ⣸⣿⣤⣤⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⠀⠀⠀  ");
		System.out.println(" ⠀⠀⠀    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀⠀⠀⠀⢀⣤⣤⣤⣤⡀⠀⠀⠀⠀⠀⢸⣿⠀⠀⠀⠀⠈⠉⠙⣿⡿⠛⠿⠿⠿⡷⠀⢰⣿⠿⠿⠿⣿⡄⠀⢾⠿⠿⠿⠛⣿⣿⠋⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⠀⠀⠀  ");
		System.out.println(" ⠀⠀⠀    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀⠀⠀⠀⣿⣿⠉⠉⣿⣿⠀⠀⠀⠀⠀⢸⣿⠀⠀⠀⠀⠀⣀⣀⣹⣷⣤⣤⣤⣤⣤⠀⠘⣿⣧⣀⣼⡿⠁⠀⣴⣤⣤⣤⣤⣿⣇⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⠀⠀⠀  ");
		System.out.println(" ⠀⠀⠀    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀⠀⠀⠀⠈⢿⣷⣾⡿⠁⠀⠀⠀⠀⠀⢸⣿⠀⠀⠀⠀⠸⠿⠟⠛⢿⣿⠉⠉⠉⠀⠀⠀⠈⢹⣿⡏⠀⠀⠀⠀⠉⠉⠉⣿⡿⠛⠿⠿⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⠀⠀⠀  ");
		System.out.println(" ⠀⠀⠀    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⣧⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠀⠀⠀⣼⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢻⣷⣄⠀⠀⠻⢿⣶⡿⠿⢿⣶⡿⠇⠀⠀⣠⣾⡟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⠀⠀⠀  ");
		System.out.println(" ⠀⠀⠀    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⢿⣧⣀⠀⠀⠀⠀⢀⣿⣿⡀⠀⠀⠀⠀⣀⣼⡿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠿⣷⣤⣀⠀⠀⠀⠀⠀⠀⠀⣀⣤⣾⠿⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⠀⠀⠀  ");
		System.out.println(" ⠀⠀⠀    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠻⠿⣶⣶⡿⠿⠛⠛⠿⢿⣶⣶⡿⠟⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠻⠿⢷⣶⣶⣶⠾⠿⠟⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⠀⠀⠀  ");
		System.out.println(" ⠀⠀⠀    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⠀⠀⠀  ");
		System.out.println(" ⠀⠀⠀    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⠀⠀⠀  ");
		System.out.println(" ⠀⠀⠀    ⠀⠀⠀⠀⠀⠀⠀⠀⣠⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⠀⠀⠀  ");
		System.out.println(" ⠀⠀⠀    ⠀⠀⠀⠀⠀⠀⣀⣾⡿⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀                    ⣿⣿⢿⣧⡀⠀⠀⠀⠀⠀⠀⠀⠀  ⠀⠀⠀  ");
		System.out.println(" ⠀⠀⠀    ⠀⠀⠀⠀⣀⣼⡿⠋⠀⣠⣄⠀⠀⠀⠀⢀⣀⠀⠀⠀⠀⣀⠀⠀⠀⢀⣀⣀⡀⠀⠀⠀⣀⠀⠀⠀⠀⣀⠀⠀⠀⢀⣀⣀⡀⠀⠀⠀⣀⠀⠀⠀⠀⣀⠀⠀⠀ ⣀⣀⡀⠀⠀⠀⠀⠀             ⣿⣿⠀⠙⢿⣦⡀⠀⠀⠀⠀⠀⠀  ⠀⠀⠀  ");
		System.out.println(" ⠀⠀⠀    ⠀⠀⢀⣴⡿⠋⠀⠀⠀⣿⣿⠀⠀⠀⠀⢸⣿⠀⠀⣠⣾⠟⠀⣠⣾⠟⠛⠛⢿⣷⡀⠀⣿⠀⠀⣠⣾⠟⠀⢠⣾⠟⠛⠛⢿⣷ ⠈⢿⣷⡀⢀⣾⡟⠀⢠⣿⢻⣷⠀⠀⠀⠀               ⣿⣿⠀⠀⠀⠙⣿⣦⡀⠀⠀⠀⠀  ⠀⠀⠀  ");
		System.out.println(" ⠀⠀⠀    ⠀⠰⡿⠋⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⢸⣿⣤⣾⣿⡁⠀⠀⣿⡇⠀⠀⠀⠀⣿⣧⠀⣿⣤⣾⣿⡁⠀⠀⣿⡏⠀⠀⠀⠀⣿⣷ ⠀⠀⠻⣷⣾⠟⠀⠀⣾⡏⠀⣿⡆⠀⠀⠀             ⠀⣿⣿⠀⠀⠀⠀⠀⠹⢿⠀⠀⠀⠀  ⠀⠀⠀  ");
		System.out.println(" ⠀⠀⠀    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠀⣿⠟⠉⢻⣧⠀⠀⣿⣧⠀⠀⠀⢀⣿⠏⠀⣿⠟⠉⢻⣷⡀⠀⢿⣧⠀⠀⠀⢀⣿⡟⠀⠀⠀⣿⣿⠀⠀⠀⢰⣿⣦⣤⣾⣿⠀⠀⠀            ⠀⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⠀⠀⠀  ");
		System.out.println(" ⠀⠀⠀    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠀⠿⠀⠀⠀⠻⠧⠀⠈⠻⠷⢶⠶⠿⠋⠀⠀⠿⠀⠀⠀⠻⠇⠀⠈⠻⠷⢶⠶⠿⠋⠀⠀⠀⠀⠿⠟⠀⠀⠀⠿⠇⠀⠀⠀⢻⠇⠀⠀             ⠀⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⠀⠀⠀  ");
		System.out.println(" ⠀⠀⠀    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀                   ⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⠀⠀⠀  ");
		System.out.println(" ⠀⠀⠀    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀                   ⠀⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⠀⠀⠀  ");
		System.out.println(" ⠀⠀⠀    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⠀⠀⠀  ");
	

		System.out.println("              ┌=================================================================┐");
		System.out.println("              │                           1. 로그인                             │");
		System.out.println("              │                                                                 │");
		System.out.println("              │                           2. 회원가입                           │");
		System.out.println("              └=================================================================┘");
		System.out.println("");
		System.out.println("입력 >> ");
		switch(ScanUtil.nextInt()) {
		case 1: 
			return View.MEMBER_LOGIN;
		case 2:
			return View.MEMBER_SIGNUP;
		case 3:
			admin = true;
			return View.ADMIN;
		default:
			return View.HOME;
		}
		
	}
}

	

