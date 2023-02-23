package util;

public interface View {
	int HOME = 1;
	
	//관리자
	int ADMIN = 9;
	int ADMIN_REVIEWMAIN = 99;
	int ADMIN_ANIGRAM = 91;  //게시판 관리 창
	int ADMIN_REPLY = 911; // 댓글관리

	
	
	
	int ADMIN_MARKET = 92; // 마켓 관리 창

	int ADMIN_MARKETPROD = 922; // 마켓상품 추가
	int ADMIN_PROD_DELETE = 923; // 마켓 상품 삭제
	int ADMIN_PROD_REVIEW = 924; // 마켓 상품 리뷰
	int ADMIN_REVIEW_DELETE = 925; // 마켓 리뷰 삭제
	
	
	
	
	int ADMIN_CLASSMAIN = 93; // 강의 관리 창
	int ADMIN_CALSS_ADD = 931; // 강의 추가
	int ADMIN_CLASS_MEMBER = 932; // 강의 회원 조회

	

	
	
	// 메인
	int MAINPAGE = 2;
	
	// 멤버 담당 팀원
	int MEMBER_SIGNUP = 31;
	int MEMBER_LOGIN = 32;
	int MEMBERINFO = 33;
	int MEM_MILE = 34;
	int MEM_UPDATE = 35;
	
	// 애니그램
	int ANIGRAM = 4;
	int ANI_DETAIL = 41;
	int ANI_MYANI = 42;
	int ANI_FRANI = 43;
	
	int ANI_MYINSERT = 44;
	int ANI_MYUPDATE = 45;
	int ANI_MYDELETE = 46;

	int ANI_FRINSERT = 47;
	int ANI_FRDELETE = 48;
	int ANI_FRDVIEW = 49;
	
	// 애니멀그램 댓글
	int ANI_REDELETE = 411;
	


	
	// 구매
	int MARKET = 7;  // 완료
	int MARKET_LIST = 71;
	int MARKET_BUY = 72;
	int MARKET_MYINFO = 73;
	int MARKET_PRODINFO = 74;  //완료
	
	
	int MARKET_PRODTYPE = 711;
	int MARKET_CARTLIST = 721; //완료
	int MARKET_CARTPURCHASE = 724;
	int MARKET_CARTPURCHASE2 = 7241;
	int MARKET_CARTPURCHASECHOICE = 7242;
	int MARKET_CARTDELETE = 723;
	int MARKET_CARTDELETE2 = 7231;
	
	int MARKET_CARTINSERT = 722; // 구매하기에서 장바구니에 넣는 메서드
	int MARKET_CARTINSERT2 = 7221; // 상품정보 보고나서 장바구니에 넣는 메소드
	int MARKET_BUYLIST = 731;
	
	int MARKET_WRITEREVIEW = 741;  //완료

	
	// 강
	int CLASS_LIST = 6;
	int CLASS_DETAIL = 61;
	int CLASS_RSV= 62;
	int CLASS_RSVLIST = 63;
	
	// 마이페이지
	int MYPAGE = 8;
	
	
	int FRIEND_LIST = 222;
}
