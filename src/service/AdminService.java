package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.AdminDAO;
import dao.ClassDAO;
import dao.MarketDAO;
import util.ScanUtil;
import util.View;

public class AdminService {
	
	// dao
	ClassDAO classdao = ClassDAO.getInstance();
	AdminDAO dao = AdminDAO.getInstance();
	MarketDAO marketdao = MarketDAO.getInstance();
	
	
	// 클래스
	MarketService marketservice = MarketService.getInstance();

	
	private static AdminService instance = null;
	private AdminService() {}
	public static AdminService getInstance() {
		if(instance == null) instance = new AdminService();
		return instance;
	}
	public int adminPage() {
		
		while(true) {
			System.out.println(" =====================관리자 페이지===================== ");
			
			System.out.println(" 1. 상품 관리");
			System.out.println(" 2. 클래스 관리");
			System.out.println(" 3. 게시글 관리");
			System.out.println(" 4. 로그아웃 ");
			
			switch(ScanUtil.nextInt()) {
			
			case 1:
				return View.ADMIN_MARKET;
			case 2:
				return View.ADMIN_CLASSMAIN;
			case 3:
				return View.ADMIN_ANIGRAM;
			case 4:
				return View.HOME;
			default:
				System.out.println("잘못 입력");
				break;
			}
			
		}
		
	}
	public int adminClass() {
		
		while(true) {
			System.out.println("================클래스 관리창=================");
			System.out.println("1. 강의관리");
			System.out.println("2. 회원별 예약 관리");
			System.out.println("3. 돌아가기");
			
			switch(ScanUtil.nextInt()) {
			case 1:
				return View.CLASS_LIST;
			case 2:
				return View.ADMIN_CLASS_MEMBER;
			case 3:
				return View.ADMIN;
			default:
				System.out.println("잘못입력");
				break;
			}
			
			
			}
			
		}
	
	
	
	public int adminMarket() {
	
		
		while(true) {
			
			System.out.println("===============상품 관리창====================");
		//	System.out.println("1. 상품관리");
			System.out.println("1. 리뷰관리");
			System.out.println("2. 돌아가기");
			
			switch(ScanUtil.nextInt()) {
			//case 1:
			//	return View.MARKET_PRODTYPE;
			case 1:
				return View.ADMIN_REVIEWMAIN;
			case 2:
				return View.ADMIN;
			default:
				System.out.println("잘못입력");
				break;
			
	     	}
	

	}
}
	
	public int adminAnigram() {
		
		
		while(true) {
			System.out.println("==============애니멀그램 관리창===============");
			System.out.println("1. 게시글관리");
			System.out.println("2. 댓글관리");
			System.out.println("3. 돌아가기");
			
			switch(ScanUtil.nextInt()) {
			case 1:
				return View.ANIGRAM;
			case 2:
				return View.ADMIN_REPLY;
			case 3:
				return View.ADMIN;
			default:
				System.out.println("잘못입력");
				break;
					
			
		}
	
			
		
	}

}
	public int adminReply() {
		System.out.println("==================모든댓글===================");
		List<Map<String, Object>> map = new ArrayList<>();
		
		map = dao.adminReply();
		
		if(map == null) {
			System.out.println("댓글이 없습니다.");
		}else {
			System.out.printf("%-10s%-10s%-10s\n", "번호","내용", "작성자");
			for(Map<String, Object> item : map) {
				System.out.printf("%-10s%-10s%-10s\n", item.get("RE_NUM"), item.get("COMMENTS"),item.get("MEM_ID"));
				
			}
		}	
			
			System.out.println("1. 댓글삭제       2. 모든 게시글 보기       3. 돌아가기");
			while(true) {
				switch(ScanUtil.nextInt()) {
				case 1:
					return View.ANI_REDELETE;
				case 2:
					return View.ANIGRAM;
				case 3:
					return View.ADMIN_ANIGRAM;
				default:
					System.out.println("잘못입력");
					break;
				
			}

		
			
			
		}
	}
	public int adminReview() {
		
			
		
		while(true) {
			System.out.println("================리뷰관리페이지===================");
			System.out.println("1. 모든상품리뷰");
			System.out.println("2. 돌아가기");
			
			switch(ScanUtil.nextInt()) {
	
			case 1:
				return View.ADMIN_PROD_REVIEW;
			case 2:
				return View.ADMIN;
			default:
				System.out.println("잘못 입력");
				break;

			}
		}
	
		

	}
	public int adminMarketProd() {
		
		System.out.println("추가할 상품 명을 입력해 주세요.");
		
		
		
		return 0;
	}
	public int adminMarketProddelete() {
		
		System.out.println("삭제할 상품을 선택해주세요");
		System.out.println("입력 >> ");
		List<Object> param = new ArrayList<>();
		param.add(ScanUtil.nextLine());
		
		int result = dao.adminMarketProddelete(param);
		
		if(result > 0) {
			System.out.println("삭제완료");
		}else {
			System.out.println("삭제실패");
		}
		
		
		return View.MARKET_PRODTYPE;
	}
	public int adminMarketProdReview() {
		List<Map<String, Object>> list = new ArrayList<>();
		list = dao.adminMarketProdReview();
		System.out.println("==============전체리뷰목록==============");
		System.out.printf("%s%10s%10s%10s\n", "순번", "제목", "작성자", "상품코드");
		for(Map<String, Object> item : list) {
			System.out.printf("%s%10s%10s%10s\n", item.get("REVIEW_NUM"), item.get("REVIEW_TITLE"), item.get("MEM_ID"), item.get("PROD_ID"));
			System.out.printf("리뷰내용 : " + item.get("REVIEW_CONTENT"));
			System.out.println();
		}

		
		while(true) {
			System.out.println("1. 리뷰삭제     2. 돌아가기");
			switch(ScanUtil.nextInt()) {
			case 1:
				return View.ADMIN_REVIEW_DELETE;
			case 2:
				return View.ADMIN_REVIEWMAIN;
			default:
				System.out.println("잘못입력");
				break;
			}
		}
		
	}
	public int adminReviewDelete() {
		
		List<Object> param = new ArrayList<>();
		
		
		System.out.println("삭제할 리뷰를 선택해 주세요.");
		System.out.println("번호 입력 >> ");
		param.add(ScanUtil.nextLine());
		
		int result = dao.adminReviewDelete(param);
		
		if(result > 0) {
			System.out.println("삭제 완료");
		}else {
			System.out.println("삭제 실패");
		}
		
		return View.ADMIN_PROD_REVIEW;
		
	}
	
	// 미구현
	public int adminClassAdd() {
		
		System.out.println("==============강의등록창==============");
		System.out.println("강의명,  강사명,  강의타입, 강의내용, 강의날짜(년월일 6자리)");
		
		
		
		
		return 0;
	}

	
	
	
	
	
	
	
	
	

	
}
