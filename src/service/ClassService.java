package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import controller.Controller;
import dao.ClassDAO;
import util.ScanUtil;
import util.SpaceUtil;
import util.View;

public class ClassService { 
	public static ClassService instance = null;
	private ClassService() {}
	public static ClassService getInstance() {
		if(instance == null) instance = new ClassService();
		return instance;
	}  //싱글톤
	
	ClassDAO dao = ClassDAO.getInstance();
	
	
	//Class 테이블 컬럼명: CLASS_CODE, CALSS_TITLE, CLASS_TRAINER, PET_TYPE, CLASS_CONTENT, CLASS_DATE
	
	
	public int list() {
		clearScreen2();
		System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
		System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⣴⣶⣶⣶⣶⣶⣶⣶⣤⣤⣤⣄⣀⡀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣤⣤⣤⣴⣶⣶⣶⣶⣶⣶⣶⣤⣤⠀⠀⠀⠀⠀⠀⠀⠀");
		System.out.println("⠀⠀⠀⠀⠀⣤⣴⣶⣾⣿⡏⠉⠉⠉⠉⠉⠉⠉⠉⠙⠛⠛⠿⢿⣿⣶⣤⣠⣴⣶⣿⠿⠟⠛⠛⠉⠉⠉⠉⠉⠉⠉⠉⠉⢹⣿⣶⣶⣤⣄⠀⠀⠀⠀");
		System.out.println("⠀⠀⠀⠀⠀⣿⡏⠉⢹⣿⡇⠀ ⠀⠀⠀⠀⠀⢀⣀⡀⠀⠀⠀⠀  ⠀⠉⢻⣿⠋⠁⠀⠀⠀⠀      ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⠉⠉⣿⣿⠀⠀⠀⠀");
		System.out.println("⠀⠀⠀⠀⠀⣿⡇⠀⢸⣿⡇⠀⠀ ⠀⠀⠀⣾⣿⠻⣿⡆⠀⠀⠀⠀ ⠀ ⢸⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀       ⠀⠀⠀⠀⠀⠀⢸⣿⠀⠀⣿⣿⠀⠀⠀⠀");
		System.out.println("⠀⠀⠀⠀⠀⣿⡇⠀⢸⣿⡇⣠⣶⣶⣤⠀⠻⣿⣶⡿⠃⢀⣴⣶⣦⡄⢸⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀       ⠀⠀⢸⣿⠀⠀⣿⣿⠀⠀⠀⠀");
		System.out.println("⠀⠀⠀⠀⠀⣿⡇⠀⢸⣿⡇⣿⣏⣹⣿⡇⠀⣀⣀⣀⠀⢺⣿⣉⣿⣿⢸⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀        ⢸⣿⠀⠀⣿⣿⠀⠀⠀⠀");
		System.out.println("⠀⠀⠀⠀⠀⣿⡇⠀⢸⣿⡇⠙⠻⠟⢋⣴⣿⠿⠿⠿⣷⣮⠛⠿⠛⠁⢸⣿⠀⠀⠀⠀     ⠀ ⣶⣶⣶⣶⣶⣶⣶⣶⠄⠀  ⢸⣿⠀⠀⣿⣿⠀⠀⠀⠀");
		System.out.println("⠀⠀⠀⠀⠀⣿⡇⠀⢸⣿⡇⠀⠀⠀⣾⡟⠁⠀  ⠀⠀⠈⣿⣧⠀   ⠀⢸⣿⠀⠀⠀⠀⠀⠀       ⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⠀⢸⣿⠀⠀⣿⣿⠀⠀⠀⠀");
		System.out.println("⠀⠀⠀⠀⠀⣿⡇⠀⢸⣿⡇⠀⠀⠀⣿⣧⣶⣶⣶⣶⣶⣿⡿⠀⠀  ⠀⢸⣿⠀⠀     ⠀⢾⣿⣿⣿⣿⣿⣿⡿⠀⠀⠀ ⠀  ⢸⣿⠀⠀⣿⣿⠀⠀⠀⠀");
		System.out.println("⠀⠀⠀⠀⠀⣿⡇⠀⢸⣿⡇⠀⠀⠀⠈⠉⠉⠁⠀⠈⠉⠉⠁⠀⠀  ⠀⢸⣿⠀⠀⠀⠀⠀        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⠀⠀⣿⣿⠀⠀⠀⠀");
		System.out.println("⠀⠀⠀⠀⠀⣿⡇⠀⢸⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀    ⠀⢸⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀        ⠀⠀⠀⠀⠀⠀⠸⠿⠀⠀⠿⠟⠀⠀⠀⠀");
		System.out.println("⠀⠀⠀⠀⠀⣿⡇⠀⢸⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀    ⠸⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
		System.out.println("⠀⠀⠀⠀⠀⣿⡇⠀⢸⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀   ⠀⠀⢠⣤⣄⣀⡀⠀⠀⢀⣤⣴⣶⣶⣶⣶⣶⣶⣦⣤⡀⠀⠀⢀⣀⣠⣤⣄⠀⠀⠀⠀");
		System.out.println("⠀⠀⠀⠀⠀⣿⡇⠀⠸⣿⣿⠿⠿⠿⠿⣿⣿⣿⣷⣶⣶⣤⣄⠀⠘⣿⣟⠻⠿⢿⣿⡿⠛⠉⠉⠀⠀⠀⠀⠉⠉⠻⢿⣿⡿⠿⠟⣿⣿⠃⠀⠀⠀⠀");
		System.out.println("⠀⠀⠀⠀⠀⣿⣇⣀⣤⣤⣤⣤⣤⣤⣀⣀⣀⣀⠀⠈⠉⠙⠋⠀   ⠀⠹⣿⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⣿⠏⠀⠀⠀⠀⠀");
		System.out.println("⠀⠀⠀⠀⠀⠻⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠿⠿⠿⣿⣷⣦⠀⠀⠀   ⠀⢹⣿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⡏⠀⠀⠀⠀⠀⠀");
		System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀       ⠀⣀ ⣀⣿⣇⣀⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⣀⣽⣿⣀⣀⠀⠀⠀⠀⠀");
		System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀        ⠀⠛⠛⣿⣿⠛⠛⠛⠃⠀⠀⠀⠀⠀⠀⠀⠀⠘⠛⠛⠛⣿⣿⠛⠛⠀⠀⠀⠀⠀");
		System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀        ⠀⠹⣿⣆⣀⣤⣶⣶⠀⠀⠀⠀⠀⠀⣶⣶⣤⣀⣰⣿⠇⠀⠀⠀⠀⠀⠀⠀");
		System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀         ⠀⣠⣤⣿⣿⣿⣟⠉⠀⠀⠀⠀⠀⠀⠀⠀⠉⣻⣿⣿⣿⣤⣄⠀⠀⠀⠀⠀⠀");
		System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀       ⠈⠛⠋⠁⠈⠛⠿⣷⣶⣤⣤⣤⣤⣤⣤⣶⣾⠿⠋⠀⠉⠛⠛⠀⠀⠀⠀⠀⠀");
		System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀           ⠀⠀⠀⠀⠉⠙⠛⠛⠛⠛⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
		System.out.println("==================================== 클래스 =======================================");
		System.out.println("번호 강의코드       강의명        \t\t강사\t  동물종류 강의시간  수강일");
		System.out.println("------------------------------------------------------------------------------------");
		List<Map<String, Object>> list = dao.list();
		for(Map<String, Object>item : list) {

		System.out.println("------------------------------------------------------------------------------------");
		System.out.print(SpaceUtil.format(item.get("CLASS_NUM").toString(), 5));
		System.out.print(SpaceUtil.format(item.get("CLASS_CODE").toString(), 10));
		System.out.print(SpaceUtil.format(item.get("CLASS_TITLE").toString(), 30));
		System.out.print(SpaceUtil.format(item.get("CLASS_TRAINER").toString(), 10));
		System.out.print("  "+SpaceUtil.format(item.get("PET_TYPE").toString(), 10));
		System.out.print("  "+SpaceUtil.format(item.get("CLASS_TIME").toString(), 5));
		System.out.print("  "+SpaceUtil.format(item.get("CLASS_DATE").toString(), 10));
		System.out.println();
		
	
		}
		System.out.println("====================================================================================");
		while(true) {
			
			if(Controller.admin) {
				System.out.println("1. 상세정보    2. 강의등록    3. 강의삭제   4.돌아가기");
				System.out.println("선택 >> ");
				switch(ScanUtil.nextInt()) {
				case 1:
					return View.CLASS_DETAIL;
				case 2:
					return View.ADMIN_CALSS_ADD;
				case 3:
					System.out.println("삭제할 강의명을 입력해 주세요.");
					List<Object> param = new ArrayList<>();
					param.add(ScanUtil.nextLine());
					
					return classdelete(param);
				case 4:
					return View.ADMIN_CLASSMAIN;
				default:
					System.out.println("잘못입력");
					break;
				}
				
			}else {
				System.out.println("1. 상세정보   2. 예약(수강)하기   3. 수강내역 확인   0. 이전화면");
				System.out.println("선택 >> ");
				switch(ScanUtil.nextInt()) {
					case 1: return View.CLASS_DETAIL;
					case 2: return View.CLASS_RSV;
					case 3: return View.CLASS_RSVLIST;
					case 0: return View.MAINPAGE;
					default: System.out.println("잘못된 입력입니다"); 
					for(int i = 0; i < 2; i++) {
						try {
							TimeUnit.SECONDS.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
			
			
		}
		

	}
	public int detail() {
		System.out.println("순번입력 >>");
		List<Object> param = new ArrayList();
		int num = ScanUtil.nextInt();
		param.add(num);
		Map<String, Object> detail = dao.detail(param);
		List<Map<String, Object>> review = dao.review(param);		
		clearScreen2();
		System.out.println("=======================================================================================");
		System.out.println("강의명 : " + detail.get("CLASS_TITLE"));
    	 System.out.println("---------------------------------------------------------------------------------------");
		System.out.print("강사 : " + detail.get("CLASS_TRAINER"));
		System.out.println("\t\t\t\t동물종류 : " + detail.get("PET_TYPE"));
    	 System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("강의 설명 : "+detail.get("CLASS_CONTENT"));
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("수강일 : " + detail.get("CLASS_DATE"));
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("한 줄 수강평 ");
		System.out.println("---------------------------------------------------------------------------------------");
		if(review == null) {
			System.out.println("\n\t 등록된 수강평이 없습니다 \t\n");
			System.out.println("=======================================================================================");

		}else {
			for(Map<String, Object>item : review) {
			System.out.println(item.get("MEM_ID") +" : " + item.get("CREVIEW_CONTENTS") + " ("+item.get("CREVIEW_DATE")+")");
			}
			System.out.println("=======================================================================================");
		}
		while(true) {
			if(Controller.admin) {
				System.out.println("1. 강의삭제    2. 돌아가기");
				System.out.println("입력 >> ");
				switch(ScanUtil.nextInt()) {
				case 1:
					return classdelete(param);
				case 2:
					return View.ADMIN_CLASSMAIN;
				default:
					System.out.println("잘못입력");
					break;
				}
			}else {
				System.out.println("1. 예약(수강)하기      2. 이전화면");
				switch(ScanUtil.nextInt()) {
					case 1: 
							param.add(Controller.loginInfo.get("MEM_ID"));
							List<Map<String, Object>> check = dao.check(param);
							if(check == null) {
								int result =  dao.rsv(param);
								if(result == 0) {
									System.out.println("\n\t예약 실패\t\n");
									for(int i = 0; i < 2; i++) {
										try {
											TimeUnit.SECONDS.sleep(1);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
									}
									return View.CLASS_LIST;
								}else {
									System.out.println("\n\t"+Controller.loginInfo.get("MEM_ID") + " 님! " + detail.get("CLASS_TITLE") + " 예약이 완료되었습니다!\t\n");
									Map<String, Object> time = dao.classtime(num);
									System.out.println("\n\t"+Controller.loginInfo.get("MEM_ID") + " 님! 예약이 완료되었습니다!\t\n");
									System.out.println("\t\t수강중");
									for(int i = 0; i < Integer.valueOf(time.get("CLASS_TIME").toString()); i++) {
										try {
											System.out.println("\t\t  .");
											TimeUnit.SECONDS.sleep(1);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
									}
									System.out.println("\n\t"+Controller.loginInfo.get("MEM_ID") + " 님! 수강이 완료되었습니다!\t\n");
									for(int i = 0; i < 2; i++) {
										try {
											TimeUnit.SECONDS.sleep(1);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
									}
									return View.CLASS_LIST;
								}
							}else {
								System.out.println();
								System.out.println("이미 수강한 강의입니다");
								System.out.println();
								for(int i = 0; i < 2; i++) {
									try {
										TimeUnit.SECONDS.sleep(1);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
								return View.CLASS_LIST; 
							}
						
					case 2: return View.CLASS_LIST;
					default: System.out.println("잘못된 입력입니다");
					}
				}
			}
			
	}
	
	// 관리자 강의 삭제
	private int classdelete(List<Object> param) {
		
		List<Object> list = new ArrayList<>();
		list.add(param.get(0));
		
		int result = dao.classdelete(list);
		
		if(result > 0) {
			System.out.println("삭제 완료");
			return View.CLASS_LIST;
		}else {
			System.out.println("삭제 실패");
			return View.CLASS_DETAIL;
		}

	}
	
	
	public int rsv() {
		System.out.println("번호입력 >>");
		int num = ScanUtil.nextInt();
		List<Object> param = new ArrayList();
		param.add(num);
		param.add(Controller.loginInfo.get("MEM_ID"));
		List<Map<String, Object>> check = dao.check(param);
		if(check == null) {
			int result = dao.rsv(param);
			if(result > 0 ) {
				Map<String, Object> time = dao.classtime(num);
				System.out.println("\n\t"+Controller.loginInfo.get("MEM_ID") + " 님! 예약이 완료되었습니다!\t\n");
				System.out.println("\t\t수강중");
				for(int i = 0; i < Integer.valueOf(time.get("CLASS_TIME").toString()); i++) {
					try {
						System.out.println("\t\t.");
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("\n\t"+Controller.loginInfo.get("MEM_ID") + " 님! 수강이 완료되었습니다!\t\n");
				for(int i = 0; i < 2; i++) {
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				return View.CLASS_LIST;
			}else {
				System.out.println("\n\t예약실패\t\n");
				for(int i = 0; i < 2; i++) {
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				return View.CLASS_LIST;
			}
		}else {
			System.out.println();
			System.out.println("이미 신청된 강의입니다");
			System.out.println();
			for(int i = 0; i < 2; i++) {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return View.CLASS_LIST;
		}
	}
	
	public int rsvlist() {
		Map<String, Object> check2 = new HashMap();
		List<Map<String, Object>> checking = new ArrayList<>();

		clearScreen2();
		System.out.println("=============================== "+ Controller.loginInfo.get("MEM_ID") +" 님 수강 내역 ====================================");
		System.out.println("번호 강의코드       강의명        \t\t강사\t  동물종류 강의시간  수강일");
		System.out.println("-------------------------------------------------------------------------------------");
		List<Object> param = new ArrayList();
		param.add(Controller.loginInfo.get("MEM_ID"));
		List<Map<String, Object>> rsvlist = dao.rsvlist(param);
		if(rsvlist == null) {
			System.out.println();
			System.out.println("수강 내역이 없습니다");
			System.out.println();
			for(int i = 0; i < 2; i++) {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return View.CLASS_LIST;
		}else {
			for(Map<String, Object>item : rsvlist) {
				System.out.print(SpaceUtil.format(item.get("CLASS_NUM").toString(), 5));
				System.out.print(SpaceUtil.format(item.get("CLASS_CODE").toString(), 10));
				System.out.print(SpaceUtil.format(item.get("CLASS_TITLE").toString(), 30));
				System.out.print(SpaceUtil.format(item.get("CLASS_TRAINER").toString(), 10));
				System.out.print("  "+SpaceUtil.format(item.get("PET_TYPE").toString(), 10));
				System.out.print("  "+SpaceUtil.format(item.get("CLASS_TIME").toString(), 5));
				System.out.print("  "+SpaceUtil.format(item.get("CLASS_DATE").toString(), 10));
				System.out.println();
				check2 = item;
				for(int i = 0; i < rsvlist.size(); i++) {
					checking.add(check2);
				}
				
			}
		}
		
		System.out.println("=====================================================================================");
				while(true) {
					System.out.println("1. 수강내역삭제  2. 리뷰작성  3. 리뷰관리  0. 이전화면");
					System.out.println("선택 >> ");
					switch(ScanUtil.nextInt()) {
					case 1: 
						System.out.print("순번입력 >> ");
						param.add(ScanUtil.nextLine());
						int result = dao.delete(param);
						if(result > 0) {
							System.out.println("\n 삭제되었습니다 \n");
							for(int i = 0; i < 2; i++) {
								try {
									TimeUnit.SECONDS.sleep(1);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							return View.CLASS_RSVLIST;
						}else {
							System.out.println("\n 삭제 실패 \n");
							for(int i = 0; i < 2; i++) {
								try {
									TimeUnit.SECONDS.sleep(1);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							return View.CLASS_RSVLIST;
						}
					case 2: 
						System.out.println("=============== 리뷰 작성 =============== ");
						System.out.println("순번입력 >> ");
						int num = ScanUtil.nextInt();
						Map<String, Object> check = dao.reviewcheck(num, Controller.loginInfo.get("MEM_ID").toString());
						List<Map<String, Object>> empty = dao.reviewcheck(Controller.loginInfo.get("MEM_ID").toString(), num);        //추가

						try {
						if(checking.contains(check.get("CLASS_CODE"))){
							System.out.println("리뷰를 작성할 수 없습니다");
							for(int i = 0; i < 2; i++) {
								try {
									TimeUnit.SECONDS.sleep(1);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							return View.CLASS_RSVLIST;
						}else if( empty!=null){
							System.out.println("리뷰는 한 번만 작성 가능합니다");
							for(int i = 0; i < 2; i++) {
								try {
									TimeUnit.SECONDS.sleep(1);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							return View.CLASS_RSVLIST;
						
						}else {
							param.add(num);
							System.out.println("리뷰 제목 >> ");
							param.add(ScanUtil.nextLine());
							System.out.println("리뷰 내용 >> ");
							param.add(ScanUtil.nextLine());
							
							int result2 = dao.insertreview(param);
							if(result2 > 0 ) {
								System.out.println("\n 등록되었습니다 \n");
								for(int i = 0; i < 2; i++) {
									try {
										TimeUnit.SECONDS.sleep(1);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
								return View.CLASS_RSVLIST;
							}else {
								System.out.println("\n 등록 실패 \n");
								for(int i = 0; i < 2; i++) {
									try {
										TimeUnit.SECONDS.sleep(1);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
								return View.CLASS_RSVLIST;
							}
						}
						}catch(NullPointerException e) {
							System.out.println("해당하는 강의가 없습니다");
							for(int i = 0; i < 2; i++) {
								try {
									TimeUnit.SECONDS.sleep(1);
								} catch (InterruptedException k) {
									k.printStackTrace();
								}
							}
							return View.CLASS_RSVLIST;
						}
					case 3: 
						clearScreen2();
						System.out.println("============================== 리뷰 리스트 ============================== ");
						List<Map<String, Object>> list = dao.reviewlist(String.valueOf(Controller.loginInfo.get("MEM_ID")));
							if(list == null) {
								System.out.println("\n\t 등록된 수강평이 없습니다 \t\n");
							}else {
								for(Map<String, Object>item : list) {
								System.out.println(item.get("CREVIEW_NUM") + ".  [ "+item.get("CLASS_TITLE")+" ] " +" : " + item.get("CREVIEW_CONTENTS") + " ("+item.get("CREVIEW_DATE")+")");
								}
							}
						System.out.println("=================================================================================");
						System.out.println("1. 삭제       0. 돌아가기");
						switch(ScanUtil.nextInt()) {
						case 1:
							
							List<Object> param3 = new ArrayList<>();
							param3.add(Controller.loginInfo.get("MEM_ID"));
							System.out.print("삭제할 리뷰 순번 >> ");
							param3.add(ScanUtil.nextInt());
							
							int result3 = dao.deletereview(param3);
							if(result3 > 0 ) {
								System.out.println("\n 삭제되었습니다 \n");
								for(int i = 0; i < 2; i++) {
									try {
										TimeUnit.SECONDS.sleep(1);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
								return View.CLASS_RSVLIST;
								
							}else {
								System.out.println("\n 삭제 실패 \n");
								for(int i = 0; i < 2; i++) {
									try {
										TimeUnit.SECONDS.sleep(1);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
								return View.CLASS_RSVLIST;
							}
						
						}
					case 0: 
						if(Controller.mypage) {
							return View.MYPAGE;
						}else {
							return View.CLASS_LIST;
						}
				}
			}
		}
		

				
	public static void clearScreen1() {
		for(int i = 0 ; i < 10; i++) {
			System.out.println();
		}
	}
	
	public static void clearScreen2() {
		for(int i = 0 ; i < 45; i++) {
			System.out.println();
		}
	}
	
}
