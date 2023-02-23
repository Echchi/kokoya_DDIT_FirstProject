package service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import controller.Controller;
import dao.AnigramDAO;
import util.ScanUtil;
import util.View;

public class AnigramService  {
	private static AnigramService  instance = null;
	private AnigramService () {}
	public static AnigramService  getInstance() {
		if(instance == null) instance = new AnigramService();
		return instance;
	}
	
	
	
	static Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
	
	AnigramDAO dao = AnigramDAO.getInstance();
	FriendService friendservice = FriendService.getinstance();

	
	
	
	public int list() {
		clearScreen();
		System.out.println("==================================애니멀그램=========================================");
		System.out.println("-------------------------------------------------------------------------------------");
		
		List<Map<String, Object>> list = dao.list();
		String str = " ";
		
		if(list == null) {
			System.out.println("애니멀그램에 등록된 게시물이 없습니다.");
		}else {
			System.out.printf("번호\t\t\t  제목 \t\t\t 작성자     \t작성일  \t좋아요");
			System.out.println();
			System.out.println("-------------------------------------------------------------------------------------");
		for(Map<String, Object> item : list) {
			System.out.printf("%3s",item.get("ANI_NUM"));
			if(str.valueOf(item.get("MEM_TITLE")).length() > 10) {
				System.out.printf("\t%-30s",str.valueOf(item.get("MEM_TITLE")).substring(0, 10) + "···");
			}else {
				System.out.printf("\t%-30s",item.get("MEM_TITLE"));
			}
			
			System.out.printf("\t%-8s",item.get("MEM_ID"));
			System.out.printf("\t%10s",sdf.format(item.get("ANI_DATE")));
			System.out.printf("\t%3s","❤"+item.get("ANI_LIKE"));
			System.out.println();
			System.out.println("-------------------------------------------------------------------------------------");
//			System.out.printf("%s%20s%20s%20s%20s\n",item.get("ANI_NUM"),item.get("MEM_TITLE"),item.get("MEM_ID"),sdf.format(item.get("ANI_DATE")),item.get("ANI_LIKE") );
			
			}
		}
		if(Controller.admin) {
			System.out.println();
			System.out.println("1. 조회   2. 게시글삭제   3.모든댓글조회    0. 돌아가기");
			System.out.print("입력  >> ");
			switch(ScanUtil.nextInt()) {
			case 1: 
					return View.ANI_DETAIL;
			case 2: 
					clearScreen();
					return View.ANI_MYDELETE;
			case 3: 
					clearScreen();
					return View.ADMIN_REPLY;
			case 0: default: return View.ADMIN;
			}
		}else {
			System.out.println();
			System.out.println("1. 조회    2. 나의 애니멀그램    3. 친구 애니멀그램    0. 돌아가기");
			System.out.print("번호 선택 >> ");
			switch(ScanUtil.nextInt()) {
			case 1: 
					return View.ANI_DETAIL;
			case 2: 
					clearScreen();
					return View.ANI_MYANI;
			case 3: 
					clearScreen();
					return View.ANI_FRANI;
			case 0: default: return View.MAINPAGE;
		}
	}
	}
	
	  public int detail() {
          System.out.println("=======================================================================================");
	      System.out.println("                     조회할 애니멀그램 게시번호를 입력해주세요.");
          System.out.println("=======================================================================================");

	         System.out.println("번호 입력 >> ");
	         int num = ScanUtil.nextInt();
	         clearScreen();
	         
	         List<Object> param = new ArrayList<>();
	         param.add(num); //공통 파라미터 ANI_NUM 추가
	         param.add(Controller.loginInfo.get("MEM_ID"));  //공통 파라미터 MEM_ID 추가
	         
	         List<Object> param2 = new ArrayList<>();  //댓글을 위한 파라미터 리스트
	         param2.add(num);
	         
	         detail:
	         while(true) {
	         Map<String, Object> list =  dao.select(num); 
	         int h = Integer.parseInt(String.valueOf(param2.get(0)));

	         System.out.println("<<<<6. 이전글 보기                                                  7. 다음글 보기>>>>");
	         System.out.println("=======================================================================================");
	            //
         
         	 System.out.print("\t\tNO." + list.get("ANI_NUM")); // null
         	 System.out.println("\t\t\t\t\t작성자:"+list.get("MEM_ID"));
         	 System.out.println("---------------------------------------------------------------------------------------");
             System.out.println("\t\t\t\t제목 >> " + list.get("MEM_TITLE"));   // 없습니다
             System.out.println("---------------------------------------------------------------------------------------");
 	         System.out.println();
 	         System.out.println();
 	         System.out.println("\t\t\t\t내용 >> "+list.get("ANI_CONTENT"));  // 빈칸
 	         System.out.println();
 	         System.out.println();
             System.out.println("=======================================================================================");
                     
    	        	String str = "SELECT * FROM REPLY WHERE ANI_NUM = " +num+"";
    	    		
    	            List<Map<String, Object>> rlist = dao.re_list(str);   
    	            if(rlist == null) {
    	               System.out.println("\n작성된 댓글이 없습니다\n");
    	            }else {
    	               for(Map<String, Object> items:rlist) {
    	                  System.out.printf("[%s] %8s : %s\n",items.get("RE_NUM"),items.get("MEM_ID"),items.get("COMMENTS"));
    	               }
    	            }
    	         

    	        if(Controller.admin) {
				
				while(true) {
					System.out.println("1. 게시글 삭제    2. 댓글삭제     3.돌아가기(관리자)");
					switch(ScanUtil.nextInt()) {
					case 1:
						return View.ANI_MYDELETE;
					case 2:
						return View.ANI_REDELETE;
					case 3:
						return View.ADMIN_ANIGRAM;
					default:
						System.out.println("잘못입력");
						break;
						
					}
				}
				
				
			}else{
 	            
    	            System.out.println();
    	            System.out.println("\n1. 좋아요    2. 좋아요 취소    3. 댓글달기   4. 댓글 삭제    5. 친구 추가    0. 돌아가기");
    	            System.out.print("선택 >> ");
            	 
        
	         
	            switch(ScanUtil.nextInt()) {
	            case 1: 
	            	
	               if(list.get("MEM_ID").equals(Controller.loginInfo.get("MEM_ID"))) {
	            	   clearScreen();
	                  System.out.println("\n\t본인 글은 좋아요 할 수 없습니다\t\n");
	                  break;
	               }else {
	               List<Map<String, Object>> llist = dao.likelist(param);
	               if(llist == null) {
	               int result = dao.like(param);
	                  if(result > 0) {
	                	  clearScreen();
//	                     System.out.println("\n\t + ❤ \t\n");
	                     System.out.println("\t\t\t. ⠀⠀⣀⣴⣾⣿⣷⣦⣀⠀⠀⠀⠀⣀⣴⣾⣿⣿⣷⣦⣀");
	                     System.out.println("\t\t\t⠀⣼⣿⣿⣿⣿⣿⣿⣿⣿⣧⠀⠀⣼⣿⣿⣿⣿⣿⣿⣿⣿⣧");
	                     System.out.println("\t\t\t⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷");
	                     System.out.println("\t\t\t⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
	                     System.out.println("\t\t\t⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿");
	                     System.out.println("\t\t\t⠀⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟");
	                     System.out.println("\t\t\t⠀⠀⠙⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠋");
	                     System.out.println("\t\t\t ⠀⠀⠀⠀⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟");
	                     System.out.println("\t\t\t ⠀⠀⠀⠀⠀⠙⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠋");
	                     System.out.println("\t\t\t ⠀⠀⠀⠀⠀⠀⠀⠙⣿⣿⣿⣿⣿⣿⣿⣿⠋");
	                     System.out.println("\t\t\t⠀ ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⣿⣿⣿⣿⠟");
	                     System.out.println("\t\t\t⠀ ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢿⡿⠋⠀");
	                     System.out.println("");
	                     System.out.println("");
	                     break;
	                  }else {
		            	   clearScreen();
	                     System.out.println("\n\t좋아요 실패\t\n");
	                     break;
	                  }
	               }else {
	            	   clearScreen();
	                  System.out.println("\n\t좋아요는 한 번만 가능합니다\t\n");
	                  break;
	               }
	               }
	            case 2: 
	               List<Map<String, Object>> llist = dao.likelist(param);
	               if(llist == null) {
	            	   clearScreen();
	                  System.out.println("\n\t좋아요를 하지 않았습니다\t\n");

	                  break;
	               }else {
	               int result = dao.dislike(param);
	                  if(result > 0) {
	                	  clearScreen();
//	                     System.out.println("\n\t - ❤ \t\n");
	                     System.out.println("\t\t\t⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
	                     System.out.println("\t\t\t⠀⠀⠀⠀⠀⢀⣠⣤⣤⣤⣤⣀⡀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣤⣤⣀⡀⠀⠀⠀⠀⠀⠀");
	                     System.out.println("\t\t\t⠀⠀⠀⢀⣴⣿⣿⣿⣿⣿⣿⣿⣿⠆⠀⠀⠀⢀⣴⣾⣿⣿⣿⣿⣿⣿⣷⣄⠀⠀⠀⠀");
	                     System.out.println("\t\t\t⠀⠀⢠⣿⣿⣿⣿⣿⣿⣿⣿⣿⣁⠀⠀⠀⠰⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⠀⠀⠀");
	                     System.out.println("\t\t\t⠀⠀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣦⡄⠀⠀⠙⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀");
	                     System.out.println("\t\t\t⠀⠀⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⠀⠀⠀⣠⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀");
	                     System.out.println("\t\t\t⠀⠀⠘⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⠀⠀⢀⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠁⠀⠀");
	                     System.out.println("\t\t\t⠀⠀⠀⠹⣿⣿⣿⣿⣿⣿⣿⣿⡿⠁⠀⢠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠀");
	                     System.out.println("\t\t\t⠀⠀⠀⠀⠘⢿⣿⣿⣿⣿⣿⣿⣿⣄⠀⠈⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠁⠀⠀⠀⠀");
	                     System.out.println("\t\t\t⠀⠀⠀⠀⠀⠀⠙⢿⣿⣿⣿⣿⣿⣿⣷⡄⠈⢻⣿⣿⣿⣿⣿⣿⡿⠋⠀⠀⠀⠀⠀⠀");
	                     System.out.println("\t\t\t⠀⠀⠀⠀⠀⠀⠀⠀⠙⢿⣿⣿⣿⣿⣿⣿⠀⢠⣿⣿⣿⣿⠟⠋⠀⠀⠀⠀⠀⠀⠀⠀");
	                     System.out.println("\t\t\t⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠻⢿⣿⣿⡇⢀⣾⣿⠿⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
	                     System.out.println("\t\t\t⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠻⠃⠘⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
	                     System.out.println();
	                     break;
	                  }else {
	                     System.out.println("\n\t좋아요 취소 실패\t\n");
	                     break;
	                  }
	               }
	               
	            case 3:
	            	List<Object> aninum = new ArrayList<>();
	            	aninum.add(num);
	            	aninum.add(Controller.loginInfo.get("MEM_ID"));
	               System.out.print("\n댓글 입력 (최대 글자수: 50) >> ");
	               String comment = ScanUtil.nextLine();
	               clearScreen();
	               if(comment.length() <= 50) {
	            	   aninum.add(comment);
	                  dao.reply(aninum);
	                  break;
	               }else{
	                  System.out.println("\n\t댓글은 50자를 초과할 수 없습니다\t\n");
	                  break;
	               }

	            case 4:
	               List<Object> param3 = new ArrayList<>();  //댓글삭제를 위한 파라미터 리스트
	               System.out.print("삭제할 댓글 번호 >>");
	               String re_num = ScanUtil.nextLine();
	               param3.add(re_num);   
	               param3.add(Controller.loginInfo.get("MEM_ID"));   
	               int result = dao.re_delete(param3);
	               
	               if(result > 0) {
	                  System.out.println("\n\t\t\t삭제 성공\t\n");
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
	                  System.out.println("\n\t\t\t본인 댓글만 삭제할 수 있습니다.\t\n");
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
	               
	               break;
	            
	            case 5:
	               
	               ArrayList<Object> param4 = new ArrayList();
	               param4.add(list.get("MEM_ID"));                  // 친구 아이디
	               param4.add(Controller.loginInfo.get("MEM_ID"));   // 내 아이디
	               Map<String, Object> row = dao.insertCheck(param4); 
	   	               
	               if(list.get("MEM_ID").equals(Controller.loginInfo.get("MEM_ID")) || row != null) {
	                  System.out.println("본인이거나 이미 친구입니다.");
	               }else {
	                  int result2 = dao.addFriend2(param4);
	                  if(result2 > 0){
	                	  clearScreen();
	                     System.out.println(list.get("MEM_ID")+"님이 친구로 등록되었습니다.");
	                  }else {
	                	  clearScreen();
	                     System.out.println("친구 추가에 실패하였습니다.");
	                  }
	                  
	               }
	               
	            case 6:   
	               List<Object> param5 = new ArrayList<>();
	               param5.add(num);
	               Map<String, Object> prelist = dao.preList(param5);
	      
	               
	               if(prelist.get("PRENUM") == null){
	            	   clearScreen();
		              System.out.println("=======================================================================================");
	                  System.out.println("\t\t\t이전 게시물이 없습니다.");
	                  System.out.println("=======================================================================================");

	               }else {
	            	   clearScreen();
	                  num = Integer.parseInt(String.valueOf(prelist.get("PRENUM")));
	                  list = prelist;

	               }
	                
	                  break;
	               
	                  
	            case 7:   
	               List<Object> param6 = new ArrayList<>();
	               param6.add(num);
	               Map<String, Object> nextlist = dao.nextList(param6);
	               
	              
	               if(nextlist.get("NEXTNUM") == null){
	            	   clearScreen();
		              System.out.println("=======================================================================================");
	                  System.out.println("\t\t\t\t다음 게시물이 없습니다.");
	                  System.out.println("=======================================================================================");

	               }else {
	            	   clearScreen();
//		              System.out.println("=======================================================================================");
//	                  System.out.print("\t\t제목 >> " + nextlist.get("NEXTTITLE"));
//	                  System.out.println();
//	                  System.out.println();
//	                  System.out.println("\t\t내용 >> "+nextlist.get("NEXTCONTENT"));
//	                  System.out.println("=======================================================================================");
	                  num = Integer.parseInt(String.valueOf(nextlist.get("NEXTNUM")));
	                  list = nextlist;
	               }
	                  break;
	               
	            case 0:
	               break detail;
	            default:
	                break;   
	               
	            }
	         }
	         }
	         return View.ANIGRAM;
	      }
	
	public int myAnigram() {
		System.out.println("============================나의 애니멀그램============================");
		System.out.println("-----------------------------------------------------------------------");

		List<Map<String, Object>> list = dao.aniList(Controller.loginInfo.get("MEM_ID"));
		
		if(list == null) {
			System.out.println("\t\t\t\t내가 올린 게시물이 없습니다.");
		}else {
			System.out.printf("번호\t\t\t  제목 \t\t\t 작성일  \t좋아요");

			System.out.println();
			System.out.println("-----------------------------------------------------------------------");

			for(Map<String, Object> item : list) {
				System.out.printf("%3s",item.get("ANI_NUM"));
				System.out.printf("\t%-30s",item.get("MEM_TITLE"));
				System.out.printf("\t%10s",sdf.format(item.get("ANI_DATE")));
				System.out.printf("\t%3s","❤"+item.get("ANI_LIKE")+("\n"));
				System.out.println("\t :"+item.get("ANI_CONTENT"));
				System.out.println("-----------------------------------------------------------------------");
			}
		}
		
		ani:
		while(true) {
			System.out.println();
			System.out.println("1. 등록    2. 수정    3. 삭제    0.돌아가기");
			System.out.print("번호 선택 >> ");
			
			switch(ScanUtil.nextInt()) {
			case 1: 
				clearScreen();
				return View.ANI_MYINSERT;
			case 2: 
				return View.ANI_MYUPDATE;
			case 3: 
				return View.ANI_MYDELETE;
			case 0: 
				if(Controller.mypage) {
					return View.MYPAGE;
				}else {
					return View.ANIGRAM;
				}
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
				break;
			}
		}
		
		
	}
	
	public int insertAni() {
		
		System.out.println("============================애니멀그램 등록============================");
		
	
			System.out.println("새 애니멀그램 제목 입력 >> ");
			String title = ScanUtil.nextLine();

			System.out.println("내용 입력 >> ");
			String content = ScanUtil.nextLine();
			clearScreen();

				
			ArrayList<Object> param = new ArrayList<>();
			param.add(title);
			param.add(content);
			param.add(Controller.loginInfo.get("MEM_ID"));
				
			int result = dao.myaniInsert(param);
			if(0 < result){
				System.out.println("\t\t\t\t게시글이 등록되었습니다.");
			}else {
				System.out.println("\t\t\t\t게시글 등록에 실패하였습니다.");
			}
			return View.ANI_MYANI;
	}
		
	
	
	public int updateAni() {
		System.out.println("============================내 게시물 수정============================\r\n" + 
				"");
		System.out.println("\t\t\t수정할 게시물 번호 입력 >> ");
		System.out.println("======================================================================");

		
		int num = ScanUtil.nextInt();
		
		System.out.println("1. 제목 수정    2. 내용 수정    0. 돌아가기");
		int input = ScanUtil.nextInt();
		switch(input) {
			case 1:  
			System.out.println("수정할 제목 입력 >> ");
			String title = ScanUtil.nextLine();
	
			ArrayList<Object> param = new ArrayList<>();
			param.add(title);
			param.add(num);
			
			
			int result = dao.myaniUpdateTitle(param);
			if(0 < result){
				System.out.println(num+"번 게시글의 제목이 수정되었습니다.");
			}else {
				System.out.println("게시글 수정에 실패하였습니다.");
			}
			return View.ANI_MYANI;
			
			case 2:
				System.out.println("수정할 내용 입력 >> ");
				String content = ScanUtil.nextLine();
				
				ArrayList<Object> param2 = new ArrayList<>();
				param2.add(content);
				param2.add(num);
				
				int result2 = dao.myaniUpdateContent(param2);
				if(0 < result2){
					System.out.println(num+"번 게시글의 내용이 수정되었습니다.");
				}else {
					System.out.println("게시글 수정에 실패하였습니다.");
				}
				return View.ANI_MYANI;
		
			case 3:default: return View.ANI_MYANI;
		}
		
		
	}
	
	public int deleteAni() {

		System.out.println("============================내 게시물 삭제============================");
		System.out.println("\t\t\t삭제할 게시물 번호 입력 >> ");
		System.out.println("======================================================================");

		int num = ScanUtil.nextInt();
		
		ArrayList<Object> param = new ArrayList<>();
		param.add(num);
	
		if(Controller.admin) {
			int result = dao.adminDelete(param);
			if(0 < result){
				System.out.println(num+"번 게시글이 삭제되었습니다.");
			}else {
				System.out.println("게시글 삭제에 실패하였습니다.");
			}
			
			return View.ADMIN_ANIGRAM;
			
		}else {
			param.add(Controller.loginInfo.get("MEM_ID"));
			int result = dao.myaniDelete(param);
			if(0 < result){
				System.out.println(num+"번 게시글이 삭제되었습니다.");
			}else {
				System.out.println("게시글 삭제에 실패하였습니다.");
			}
			
			return View.ANI_MYANI;
		}
	}
	
	public int friendAni() {
		System.out.println("===========================  친구 목록  ===========================");

		String userId=(String)Controller.loginInfo.get("MEM_ID");
		List<Map<String, Object>> list = dao.friendList(userId);

		if(list == null) {
			System.out.println("\t\t\t\t등록된 친구가 없습니다.");
		}else {		
		for(Map<String, Object> item : list) {
			System.out.print("\t\t     " + item.get("FRIEND_ID") +"\n");
			
			}
		System.out.println("===================================================================");
		System.out.println();
		}
		while(true) {
			System.out.println();
			System.out.println("1. 친구등록    2. 삭제    3. 친구 게시글 보기    0.돌아가기");
		switch(ScanUtil.nextInt()) {
		case 1: return View.ANI_FRINSERT; // 친구등록
		case 2: return View.ANI_FRDELETE;  // 친구삭제
		case 3: 
			clearScreen();
			return View.ANI_FRDVIEW;
		case 0: return View.ANIGRAM;
		default: 
			System.out.println("\t\t\t잘못 입력하셨습니다.");
			break;

		} 
			
		}
		
	}
	public int addFriend() {
		List<Map<String, Object>> list = dao.list1();
		
		
		
		
		if(list == null) {
			System.out.println("애니멀그램에 등록된 사용자가 없습니다.");
		}else {
			System.out.println("============================사용자 목록============================");
		for(Map<String, Object> item : list) {
			if(Controller.loginInfo.get("MEM_ID").equals(item.get("MEM_ID"))) {
				continue;
			}else {
				System.out.printf("\t%s\n", item.get("MEM_ID"));
			}
			
		}
		System.out.println("=======================================================================");
		System.out.println();

		}
		System.out.println("\t\t\t친구 추가할 아이디 입력 >> ");		
		String friendId = ScanUtil.nextLine();  

		List<Object> param = new ArrayList<>();
		param.add(friendId); 
		param.add(Controller.loginInfo.get("MEM_ID"));
		Map<String, Object> row = dao.insertCheck(param); 
		
		
		
		if(friendId.equals(Controller.loginInfo.get("MEM_ID")) || row != null) {
			System.out.println("\t\t본인 ID이거나 이미 등록된 친구입니다. 다시 입력해주세요.");
		}else {
			int result = dao.addFriend(param);
			if(result > 0){
				System.out.println(friendId+"님이 친구로 등록되었습니다.");
			}else {
				System.out.println("\t\t친구 추가에 실패하였습니다.");
			}
			
		}
		
		
		
		
		return View.ANI_FRANI;
	}
	
	public int deleteFriend() {
		System.out.println("\t\t삭제할 친구의 아이디를 입력해주세요.");		
		String friendId = ScanUtil.nextLine();

		ArrayList<Object> param = new ArrayList<>();
		param.add(friendId);
		param.add(Controller.loginInfo.get("MEM_ID"));
		
		int result = dao.deleteFriend(param);
		if(result > 0){
			System.out.println(friendId+"님이 친구 목록에서 삭제 되었습니다.");
		}else {
			System.out.println("\t\t친구 삭제에 실패하였습니다.");
		}
		
		
		return View.ANI_FRANI;
	}



	
	public int viewFriend() {
		System.out.println("================================친구 애니멀그램=======================================");
		System.out.println("--------------------------------------------------------------------------------------");
		String userId=(String)Controller.loginInfo.get("MEM_ID");
		List<Map<String, Object>> list = dao.friendlist(userId);
		
		if(list == null) {
			System.out.println("\t\t\t\t\t등록된 친구 게시물이 없습니다.");
		}else {
			System.out.printf("번호\t\t\t  제목 \t\t\t 작성자     \t작성일  \t좋아요");
			System.out.println(); 
			System.out.println("--------------------------------------------------------------------------------------");
		for(Map<String, Object> item : list) {
			System.out.printf("%3s",item.get("ANI_NUM"));
			System.out.printf("\t%-30s",item.get("MEM_TITLE"));
			System.out.printf("\t%-8s", item.get("FRIEND_ID"));
			System.out.printf("\t%10s",sdf.format(item.get("ANI_DATE")));
			System.out.printf("\t%3s","❤"+item.get("ANI_LIKE"));
			System.out.println();
			System.out.println("--------------------------------------------------------------------------------------");
			}
		
		}
		
		return View.ANI_FRANI;
	}
	
	public static void clearScreen() {
		for(int i = 0; i < 50 ; i++) {
			System.out.println();
		}
	}
	
	public int replyDelete() {
		
		
		List<Object> param = new ArrayList<>();  //댓글삭제를 위한 파라미터 리스트
		System.out.print("삭제할 댓글 번호 >>");
		String re_num = ScanUtil.nextLine();
		param.add(re_num);	
		int result = dao.ad_delete(param);
		
		if(result > 0) {
			System.out.println("\n\t삭제 성공!\t\n");
		}else {
			System.out.println("\n\t삭제 실패!\t\n");
		}
		
		
		return View.ADMIN_REPLY;
	}
	
	
	
}
