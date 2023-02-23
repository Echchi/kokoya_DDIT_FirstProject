package dao;

import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class MemberDAO {
	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	private static MemberDAO instance = null;
	private MemberDAO() {}
	public static MemberDAO getInstance() {
		if(instance == null) instance = new MemberDAO();
		return instance;
	}
	public int signUp(List<Object> param) {
		
		String sql = "INSERT INTO MEMBER(MEM_ID, MEM_PASS, MEM_NAME, PET_ID, MEM_MILE) VALUES(?,?,?,?,0)";
		
		return jdbc.update(sql, param);
		
	}
	public Map<String, Object> login(List<Object> param) {
		
		String str = "SELECT MEM_ID, MEM_PASS FROM MEMBER WHERE MEM_ID = ? AND MEM_PASS = ? ";
		
		
		return jdbc.selectOne(str, param);
	}
	public Map<String, Object> userInfo(Object id) {
		
		String sql = " SELECT * FROM MEMBER WHERE MEM_ID = '" + id + "'";
		return jdbc.selectOne(sql);
	}
	public int addMile(List<Object> param) {
		
		String sql = "UPDATE MEMBER SET MEM_MILE = MEM_MILE + ? WHERE MEM_ID = ?";
		
		return jdbc.update(sql,param);
	}
	
	// 충전시 바로 적용이 안됨
	public int updateInfo(String sql, List<Object> param) {
		
		
		return jdbc.update(sql, param);
	}
	
}
