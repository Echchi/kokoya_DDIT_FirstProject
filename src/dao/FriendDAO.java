package dao;

import java.util.List;
import java.util.Map;

import controller.Controller;
import util.JDBCUtil;

public class FriendDAO {
	
	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	private static FriendDAO instance = null;
	private FriendDAO() {}
	public static FriendDAO getinstace() {
		if(instance == null) instance = new FriendDAO();
		return instance;
	}
	
	
	public int follow (List<Object> param) {
		String sql = " INSERT INTO FRIEND(F_NUM,MEM_ID,FRIEND_ID) VALUES(FRIEND_SEQ.NEXTVAL,?,?) ";
		return jdbc.update(sql, param);
		
	}
	
	public List<Map<String,Object>> flist (List<Object> param){
		String sql=" SELECT * " + 
				    "  FROM MEMBER A, FRIEND B " + 
			     	" WHERE A.MEM_ID = B.MEM_ID " + 
				    "   AND B.ID = ? ";
		return jdbc.selectList(sql, param);
	}
	public Map<String,Object> friendCheck(List<Object> param) {
		
		String sql = "SELECT FRIEND_ID FROM FRIEND WHERE MEM_ID = '" + Controller.loginInfo.get("MEM_ID") + "'" ;
		return jdbc.selectOne(sql);
	}
	
	
	
	
	

}
