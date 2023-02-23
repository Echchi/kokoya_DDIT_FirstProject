package dao;

import java.util.List;
import java.util.Map;

import util.JDBCUtil;

//Class 테이블 : CLASS_CODE, CLASS_TITLE, CLASS_TRAINER, PET_TYPE, CLASS_CONTENT, CLASS_DATE
//Register 테이블 : REGISTER_CODE, MEM_ID, CLASS_CODE

public class ClassDAO {
	private static ClassDAO instance = null;
	private ClassDAO() {}
	public static ClassDAO getInstance() {
		if(instance == null) instance = new ClassDAO();
		return instance;
	}
	
	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	
	public List<Map<String, Object>> list () {
		return jdbc.selectList("SELECT * FROM CLASS ORDER BY CLASS_NUM ASC ");
	}
	
	public Map<String, Object> detail (List<Object> param) {
		return jdbc.selectOne(" SELECT * FROM CLASS WHERE CLASS_NUM = ? ",param);
	}
	
	public List<Map<String, Object>> check (List<Object> param) {
		return jdbc.selectList(" SELECT * FROM REGISTER WHERE CLASS_CODE = (SELECT CLASS_CODE FROM CLASS WHERE CLASS_NUM = ?)  AND MEM_ID = ?",param);
	}
	
	public Map<String, Object> classtime (int num) {
		return jdbc.selectOne(" SELECT * FROM CLASS WHERE CLASS_NUM = " + num);
	}
	
	public int rsv (List<Object> param) {
		return jdbc.update(" INSERT INTO REGISTER(REGISTER_CODE, CLASS_CODE, MEM_ID) "
    						+ " VALUES (REG_SEQ.NEXTVAL,(SELECT CLASS_CODE FROM CLASS WHERE CLASS_NUM = ?),?) ",param);
	}
	
	public List<Map<String, Object>> rsvlist (List<Object> param) {
		return jdbc.selectList(" SELECT * " + 
							   "   FROM CLASS A,REGISTER B " + 
							   "  WHERE A.CLASS_CODE=B.CLASS_CODE " +
							   "    AND B.MEM_ID = ? ",param);
	}
	
	public int delete (List<Object> param) {
		return jdbc.update(" DELETE REGISTER WHERE MEM_ID = ? AND CLASS_CODE = (SELECT CLASS_CODE FROM CLASS WHERE CLASS_NUM = ?) ",param);
	}
	
	// 리뷰
	public List<Map<String, Object>> review (List<Object> param) {
		return jdbc.selectList(" SELECT * FROM CLASS_REVIEW WHERE CLASS_CODE = (SELECT CLASS_CODE FROM CLASS WHERE CLASS_NUM = ?) ",param);
	}
	
	public Map<String, Object> reviewcheck (int num, String id) {
		return jdbc.selectOne(" SELECT * FROM REGISTER WHERE CLASS_CODE = (SELECT CLASS_CODE FROM CLASS WHERE CLASS_NUM = "+num+") " + 
								" AND MEM_ID = '"+id+"'");
	}
	
	public int insertreview (List<Object> param) {
		return jdbc.update(" INSERT INTO CLASS_REVIEW (MEM_ID, CREVIEW_NUM, CLASS_CODE, CREVIEW_TITLE, CREVIEW_CONTENTS, CREVIEW_DATE)"
							+ " VALUES (?,CLASS_REVIEW_SEQ.NEXTVAL,(SELECT CLASS_CODE FROM CLASS WHERE CLASS_NUM = ?),?,?,SYSDATE) ",param);
	}
	
	public List<Map<String, Object>> reviewlist (String id) {
		return jdbc.selectList(" SELECT A.*, B.CLASS_TITLE" + 
							    "  FROM CLASS_REVIEW A, CLASS B" + 
							    " WHERE A.CLASS_CODE = B.CLASS_CODE" + 
							    "   AND A. MEM_ID = '"+id+"'");
	}
	
	public int deletereview (List<Object> param) {
		return jdbc.update(" DELETE FROM CLASS_REVIEW WHERE MEM_ID = ? AND CREVIEW_NUM = ? ",param);
	}
	public int classdelete(List<Object> list) {
		String sql = "DELETE FROM CLASS WHERE CLASS_NUM = ?";
		return jdbc.update(sql, list);
	}
	
	
	
	
	
	
	
	
	// 추가
	public List<Map<String, Object>> reviewcheck (String id, int cc) {
		return jdbc.selectList(" SELECT *" + 
				"  FROM CLASS_REVIEW" + 
				" WHERE CLASS_CODE = (SELECT CLASS_CODE FROM CLASS WHERE CLASS_NUM = " + cc + ")"+
				"   AND MEM_ID = '"+id+"' ");
	}

}
