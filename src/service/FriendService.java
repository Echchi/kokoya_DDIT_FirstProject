package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import controller.Controller;
import dao.FriendDAO;
import util.ScanUtil;
import util.View;

public class FriendService {
	
	FriendDAO dao = FriendDAO.getinstace();
	
	
	private static FriendService instance = null;
	private FriendService() {}
	public static FriendService getinstance() {
		if(instance == null) instance = new FriendService();
		return instance;
	}
	
	
	public int follow(List<Object> param) {
		Map<String,Object> row = dao.friendCheck(param);
		
		if(row == null) {
			int result = dao.follow(param);
			return result;
		}else {
			System.out.println("본인이거나 이미 등록되었습니다.");
			return 0;
		}
		
	}
	
	
	
	public int flist() {
		List<Object> param = new ArrayList();
		param.add(Controller.loginInfo.get("MEM_ID"));
		System.out.println("========== 친구 목록 ==========");
		List<Map<String,Object>>list = dao.flist(param);
		if(list == null) {
			System.out.println("\n\t친구가 없습니다\t\n");
		}else {
			for(Map<String, Object> item:list) {
				System.out.printf("\t[%s] %s\t\n",item.get("F_NUM"),item.get("ID"));
			}
		} 
		
		System.out.println("===============================");
		System.out.println("\n1. 상세정보    2.애니그램 확인    0. 돌아가기");
		System.out.println("선택 >>");
		switch(ScanUtil.nextInt()) {
		case 1: 
			System.out.println("확인할 친구 아이디 >> ");
			
			
			
			break;
		case 2:
			break;
		case 0: 
			break;
		}
		
		return View.FRIEND_LIST;
	}
	
	
	
	
	
	

}
