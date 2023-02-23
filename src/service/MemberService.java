package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import controller.Controller;
import dao.MemberDAO;
import util.ScanUtil;
import util.View;

public class MemberService {

	MemberDAO dao = MemberDAO.getInstance();
	
	private static MemberService instance = null;
	private MemberService() {}
	public static MemberService getInstance() {
		if(instance == null) instance = new MemberService();
		return instance;
	}
	

	public int signUp() {
	
		
			System.out.println("=======================");
			System.out.println("회원가입 페이지 입니다.");
			System.out.println("=======================");
			System.out.println();
		id:
		while(true) {
			System.out.println("아이디 입력 (영문,숫자 5 ~ 10자리)>> ");
			String id = ScanUtil.nextLine();
			String pattern = "^[a-z0-9_]{5,10}$";
			boolean regex = Pattern.matches(pattern, id);
			if(regex) {
				System.out.println("비밀번호 입력 >> ");
				String pass = ScanUtil.nextLine();
				System.out.println("이름 입력 >> ");
				String name = ScanUtil.nextLine();
				System.out.println("동물이름 입력 >> ");
				String petname = ScanUtil.nextLine();
				
				List<Object> param = new ArrayList<>();
				param.add(id);
				param.add(pass);
				param.add(name);
				param.add(petname);
				
				int result = dao.signUp(param);

				if(result > 0 ) {
					System.out.println("회원가입 성공!");
					for (int i = 0; i < 1; i++) {
						try {
							TimeUnit.SECONDS.sleep(2);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					clearScreen();
				}else {
					System.out.println("회원가입 실패!");
					for (int i = 0; i < 1; i++) {
						try {
							TimeUnit.SECONDS.sleep(2);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					clearScreen();
				}
				
				return View.HOME;
				
			}else {
				System.out.println("양식을 다시 확인해 주세요.");
			}
			
		}
	
		
			
		

	}
	public int login() {
		Scanner sc = new Scanner(System.in);
		
		clearScreen();
		System.out.println("┌============로그인 페이지============┐");
		System.out.print("  아이디 입력 >> ");
		String id = ScanUtil.nextLine();
		System.out.println("├─────────────────────────────────────┤");
		System.out.print("  비밀번호 입력 >> ");
		String pass = ScanUtil.nextLine();
		System.out.println("└─────────────────────────────────────┘");
		for (int i = 0; i < 3; i++) {
			try {
				System.out.println("\t   .");
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		List<Object> param = new ArrayList<>();
		param.add(id);
		param.add(pass);
		
		Map<String, Object> map = dao.login(param);
	
		if(map == null) {
			Controller.count++;
			System.out.println("없는 회원 입니다.");
			return View.HOME;
		}else {
			Controller.loginInfo = dao.userInfo(id);
			Controller.login = true;
			clearScreen();
			System.out.println("로그인 성공!");
			for (int i = 0; i < 1; i++) {
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 clearScreen();
				
			}
			
			return View.MAINPAGE;
		}
		
		
		
	}
	public int info() {
		
		info:
		while(true) {
			clearScreen();
			System.out.println("=================나의 정보=====================");
			System.out.println();
			for(Object item : Controller.loginInfo.keySet()) {
				System.out.println(item + " : " +  Controller.loginInfo.get(item));
				System.out.println();
			}
			
			System.out.println("1.  돌아가기");
			switch(ScanUtil.nextInt()) {
			case 1:
				break info;
			default:
				System.out.println("잘못입력");
				break;

			}
			
		}
	
		
		return View.MYPAGE;
	}
	public int addMile() {
		System.out.println("=============마일리지 충전소================");
		System.out.println("얼마를 충전 하시겠습니까??");
		System.out.println("입력 >> ");
		
		List<Object> param = new ArrayList<>();
		Object mile = ScanUtil.nextLine();
		param.add(mile);
		param.add(Controller.loginInfo.get("MEM_ID"));
		
		int result = dao.addMile(param);
		            
		
		if(result > 0) {
			 
			System.out.println("충전 완료!");
		}else {
			System.out.println("충전 실패!");
		}
		
		
		return View.MYPAGE;
	}
	
	
	
	public int updateInfo() {
		
		System.out.println("=========개인정보 수정==========");
		System.out.println("1. 패스워드 변경");
		System.out.println("2. 주소 변경");
		System.out.println("3. 동물이름 변경");
		
		Map<String, Object> value = new HashMap<>();
		value.put("1", "MEM_PASS");
		value.put("2", "MEM_ADD");
		value.put("3", "PET_ID");
		
		Object obj = value.get(ScanUtil.nextLine());
		
		List<Object> param = new ArrayList<>();
		System.out.println("변경 내용 >> ");
		param.add(ScanUtil.nextLine());
		param.add(Controller.loginInfo.get("MEM_ID"));
		
		String sql = " UPDATE MEMBER SET " + obj +" = ? WHERE MEM_ID = ?";
		
		int result = dao.updateInfo(sql, param);
		
		if(result > 0) {
			System.out.println("수정 완료");
		}else {
			System.out.println("수정 실패 !");
		}
		
		
		return View.MYPAGE;
	}
	
	
	public static void clearScreen() {
		for(int i = 0; i < 50 ; i++) {
			System.out.println();
		}
	}
	
	
	
}
