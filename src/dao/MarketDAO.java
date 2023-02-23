package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class MarketDAO {

	private static MarketDAO instance = null;
	private MarketDAO() {}
	public static MarketDAO getInstance() {
		if(instance == null) {instance = new MarketDAO();}
		return instance;
	}
	
	JDBCUtil jdbc = JDBCUtil.getInstance();
	

	
	public List<Map<String, Object>> dogInfo (int num){
		 return jdbc.selectList("SELECT *  FROM PROD" + 
				 " WHERE SUBSTR(PROD_ID,1,1) = 'd'" +
				 " AND PROD_NUM='" + num +
				 "' ORDER BY PROD_ID ASC");
	 }
	public List<Map<String, Object>> catInfo (int num){
		return jdbc.selectList("SELECT *  FROM PROD" + 
				" WHERE SUBSTR(PROD_ID,1,1) = 'c'" +
				" AND PROD_NUM='" + num +
				"' ORDER BY PROD_ID ASC");
	}
	public List<Map<String, Object>> etcInfo (int num){
		return jdbc.selectList("SELECT *  FROM PROD" + 
				" WHERE SUBSTR(PROD_ID,1,1) = 'h'" +
				" OR SUBSTR(PROD_ID,1,1) = 't'" +
				" AND PROD_NUM='" + num +
				"' ORDER BY PROD_ID ASC");
	}
	
	public List<Map<String, Object>> review (String prod_id){//미완성임 이거
		 return jdbc.selectList("SELECT *  FROM REVIEW" + 
				 " WHERE PROD_ID = '" +prod_id +
				 "'");
	 }
	
//	public List<Map<String, Object>> cartList(Object object){
//		return jdbc.selectList("SELECT *  FROM PROD" + 
//				 " WHERE SUBSTR(PROD_ID,1,1) = ? " + 
//				 " ORDER BY PROD_ID ASC", object);
//		
//	}
	
	
	public List<Map<String, Object>> dogList (){   // 메서드 합치기
		 return jdbc.selectList("SELECT *  FROM PROD" + 
				 " WHERE SUBSTR(PROD_ID,1,1) = 'd' " + 
				 " ORDER BY PROD_ID ASC");
	 }
	 
	public List<Map<String, Object>> catList (){
		return jdbc.selectList("SELECT *  FROM PROD" + 
				" WHERE SUBSTR(PROD_ID,1,1) = 'c'" + 
				" ORDER BY PROD_ID ASC");
	}

	public List<Map<String, Object>> etcList (){
		return jdbc.selectList("SELECT *  FROM PROD" + 
				" WHERE SUBSTR(PROD_ID,1,1) = 'h'" +
				" OR SUBSTR(PROD_ID,1,1) = 't'" + 
				" ORDER BY PROD_ID ASC");
	}
	
	public List<Map<String, Object>> cartList(String mem_id) {
		
		return jdbc.selectList("SELECT B.ORDER_NUM, A.PROD_TYPE, A.PROD_NAME," + 
				" A.PROD_PRICE,B.ORDER_QTY, (A.PROD_PRICE*B.ORDER_QTY) AS TOTAL " + 
				" FROM PROD A, CART B" + 
				" WHERE B.MEM_ID = '" + mem_id + 
				"' AND A.PROD_ID = B.PROD_ID" + 
				" AND B.ORDER_STATE = 1"+
				" ORDER BY B.ORDER_NUM ASC");
	}
	
	public int cartDelete(int order_num) {
		
		return jdbc.update("DELETE FROM CART WHERE ORDER_NUM = '" + order_num + "' AND ORDER_STATE = 1");
	}
	
	public int cartDelete2(String mem_id) {
		
		return jdbc.update("DELETE FROM CART WHERE MEM_ID ='" + mem_id + "' AND ORDER_STATE = 1");
	}
	
	public int cartInsert(int order_num, int order_qty, String mem_id) {
		return jdbc.update("INSERT INTO CART(ORDER_CODE,ORDER_STATE,MEM_ID,ORDER_DATE,ORDER_NUM,PROD_ID,BUY_CODE,ORDER_QTY)" 
							+ " VALUES(('P'||LPAD(CART_SEQ_NUMBER.NEXTVAL,3,0)),1,'" + mem_id 
							+ "',SYSDATE,CART_SEQ_NUMBER.NEXTVAL,(SELECT PROD_ID FROM PROD WHERE PROD_NUM = '"+order_num+"')"
							+ ",'','" + order_qty + "')");
	}
	
	public List<Map<String, Object>> totalPayment(String mem_id) {
		
		return jdbc.selectList("SELECT SUM(A.PROD_PRICE*B.ORDER_QTY) AS 총결제금액" + 
				"  FROM PROD A, CART B" + 
				" WHERE B.ORDER_STATE = 1" + 
				" AND B.MEM_ID = '"+ mem_id +"'" + 
				" AND A.PROD_ID = B.PROD_ID");
	}
	
	public List<Map<String, Object>> showMyInfo(Object object) {
		
		return jdbc.selectList("SELECT * FROM MEMBER WHERE MEM_ID ='"+object+"'");
	}
	
	public int cartPurchase(int myMile, String mem_id, int paidMoney) {
		
		jdbc.update("UPDATE MEMBER SET MEM_MILE ='"+myMile+"'"+
		            " WHERE MEM_ID = '"+ mem_id +"'");
		
		jdbc.update("INSERT INTO BUY VALUES('B'||TO_CHAR(SYSDATE,'YYYYMMDD')||LPAD(BUY_SEQ_NUMBER.NEXTVAL,3,0),"+ 
					" BUY_SEQ_NUMBER.NEXTVAL,'"+mem_id+"', SYSDATE, " + paidMoney + ")");
		
		return jdbc.update("UPDATE CART SET ORDER_STATE = 2, BUY_CODE =(SELECT DISTINCT LAST_VALUE(BUY_CODE) OVER() AS BUY_CODE_last " + 
					" FROM BUY WHERE MEM_ID = '" + mem_id +	"'), BUY_DATE =SYSDATE WHERE ORDER_STATE = 1" +
					" AND MEM_ID = '" + mem_id + "'");
		
		
	}
	
		
	public int cartPurchase2(int myMile, String mem_id, int choiceMoney, List<Object> selectCart) {
		int result = 0;
		
		jdbc.update("UPDATE MEMBER SET MEM_MILE ='"+myMile+"'"+
				" WHERE MEM_ID = '"+ mem_id +"'");
		
		jdbc.update("INSERT INTO BUY VALUES('B'||TO_CHAR(SYSDATE,'YYYYMMDD')||LPAD(BUY_SEQ_NUMBER.NEXTVAL,3,0),"+ 
				" BUY_SEQ_NUMBER.NEXTVAL,'"+mem_id+"', SYSDATE, " + choiceMoney + ")");
	
		
		
		for(Object item : selectCart) {
			result= jdbc.update("UPDATE CART SET ORDER_STATE = 2, BUY_CODE =(SELECT DISTINCT LAST_"
					+ "VALUE(BUY_CODE) OVER() AS BUY_CODE_last " + 
					" FROM BUY WHERE MEM_ID = '" + mem_id +	"'), BUY_DATE =SYSDATE WHERE ORDER_STATE = 1" +
					" AND MEM_ID = '" + mem_id + "'" +
					" AND ORDER_NUM = '"+ Integer.parseInt(item.toString()) + "'");
		}
		return result;
	}
	
	
	public List<Map<String, Object>> buytList(String mem_id) {
		
		return jdbc.selectList("SELECT DISTINCT A.BUY_CODE, A.BUY_NUM, A.BUY_DATE, A.BUY_AMOUNT FROM BUY A, CART B, PROD C" +
								" WHERE A.MEM_ID = '" + mem_id +"'" + 
								" AND B.ORDER_STATE = 2" +
								" ORDER BY A.BUY_NUM ASC");
	}
	public List<Map<String, Object>> Payment(Object object) {
		return jdbc.selectList("SELECT SUM(A.PROD_PRICE*B.ORDER_QTY) AS \"총 결제 금액\"" + 
				"  FROM PROD A, CART B" + 
				" WHERE B.ORDER_STATE = 2" + 
				" AND B.MEM_ID = '"+ object +"'" + 
				" AND A.PROD_ID = B.PROD_ID");
	}
	public List<Map<String, Object>> buyCount(String mem_id) {
		
		return jdbc.selectList("SELECT DISTINCT COUNT(*) AS COUNT, BUY_CODE FROM CART WHERE BUY_CODE IN" +
								" (SELECT DISTINCT BUY_CODE FROM BUY WHERE MEM_ID = '"+ mem_id +"')" +
				 				"GROUP BY BUY_CODE ORDER BY BUY_CODE");
		
		
	}
	
	public List<Map<String, Object>> list4review (String num) {
		
		return jdbc.selectList("  SELECT * " + 
							    "   FROM CART A, PROD B " + 
			                	"   WHERE A.PROD_ID = B.PROD_ID " + 
				                 "    AND A.ORDER_STATE = 2 " + 
			                	 "    AND A.BUY_CODE = (SELECT BUY_CODE FROM BUY WHERE BUY_NUM = '"+num+"')" );
	}
	
	
	
	public int insertReview (List<Object> param) {
		return jdbc.update("INSERT INTO REVIEW (MEM_ID, PROD_ID, REVIEW_NUM, REVIEW_TITLE, REVIEW_CONTENT, REVIEW_DATE)" + 
						 	" VALUES(?,(SELECT PROD_ID FROM CART WHERE ORDER_NUM = ? ),REVIEW_SEQ.NEXTVAL,?,?,SYSDATE) ", param);
	}
	
	
	
	public List<Map<String, Object>>  choiceMoney(List<Object> selectCart, String mem_id) {
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		for(Object item: selectCart) {
			result.add(jdbc.selectOne("SELECT (A.ORDER_QTY * B.PROD_PRICE) AS \"결제금액\"" + 
					"  FROM CART A, PROD B" + 
					" WHERE A.ORDER_NUM = '" +item +"'"+ 
					"   AND A.PROD_ID = B.PROD_ID" + 
					"   AND MEM_ID = '"+ mem_id +"'"));
		}
		
		
		
		return result;
	}
	
	

}
