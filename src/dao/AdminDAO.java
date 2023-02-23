package dao;

import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class AdminDAO {

	
	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	private static AdminDAO instance = null;
	private AdminDAO() {}
	public static AdminDAO getInstance() {
		if(instance == null) instance = new AdminDAO();
		
		return instance;
	}
	public int delete(List<Object> param) {
		
		String sql = "DELETE FROM CLASS WHERE CLASS_CODE = ?";		
		return jdbc.update(sql, param);
	}
	
	// 마켓 
	
	// 메뉴 목록
	public List<Map<String, Object>> marketmenu() {
		
		String sql = "SELECT * FROM PROD ";
	
		return jdbc.selectList(sql);
	}
	public List<Map<String, Object>> adminReply() {
		
		String sql = "SELECT * FROM REPLY";		
		return jdbc.selectList(sql);
	}
	public List<Map<String, Object>> adminReview() {
		
		String sql = "SELECT * FROM REVIEW";
		
		return jdbc.selectList(sql);
	}
	public int adminMarketProddelete(List<Object> param) {
		
		String sql = "DELETE FROM PROD WHERE PROD_NUM = ?";
		
		return jdbc.update(sql, param);
	}
	public List<Map<String, Object>> adminMarketProdReview() {
		
		String sql = "SELECT * FROM REVIEW";
		
		return jdbc.selectList(sql);
	}
	public int adminReviewDelete(List<Object> param) {
		
		String sql = "DELETE FROM REVIEW WHERE REVIEW_NUM = ?";
		
		
		return jdbc.update(sql, param);
	}
	
	
}
