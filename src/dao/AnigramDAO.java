package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import controller.Controller;
import util.JDBCUtil;

public class AnigramDAO {
	private static AnigramDAO instance = null;
	private AnigramDAO() {}
	public static AnigramDAO getInstance() {
		if(instance == null) instance = new AnigramDAO();
		return instance;
	}
	
JDBCUtil jdbc = JDBCUtil.getInstance();

	// 기본 DAO

	public List<Map<String, Object>> list1() {
		return jdbc.selectList("SELECT DISTINCT(MEM_ID) FROM ANIMALGRAM");	
	}


	public List<Map<String, Object>> list() {
		return jdbc.selectList("SELECT * FROM ANIMALGRAM ORDER BY LPAD(ANI_NUM, 4, 0) ASC");
	}
	
	public Map<String, Object> select(int num) {
		return jdbc.selectOne("SELECT * FROM ANIMALGRAM WHERE ANI_NUM='"+num+"'");
	}
	
	public List<Map<String, Object>> aniList(Object object){
		return jdbc.selectList("SELECT * FROM ANIMALGRAM WHERE MEM_ID="+"'"+object+"'");
	}	
	
	public int myaniInsert(List<Object> param) {
		return jdbc.update("INSERT INTO ANIMALGRAM (ANI_NUM, MEM_TITLE, ANI_CONTENT, MEM_ID, ANI_DATE,ANI_LIKE) "
				+ "VALUES (SEQ_NUMBER.NEXTVAL, ?, ?, ?, sysdate,0)",param);
	}
		
	public int myaniUpdateTitle(List<Object> param) {
		return jdbc.update("UPDATE ANIMALGRAM SET MEM_TITLE=? WHERE ANI_NUM=?",param);
	}
	
	public int myaniUpdateContent(List<Object> param2) {
		return jdbc.update("UPDATE ANIMALGRAM SET ANI_CONTENT=? WHERE ANI_NUM=?",param2);
	}
	
	public int myaniDelete(List<Object> param) {
		return jdbc.update("DELETE FROM ANIMALGRAM WHERE ANI_NUM=? AND MEM_ID=?",param);
	} 
	
	
	public int addFriend(List<Object> param) {
		return jdbc.update("INSERT INTO FRIEND (FRIEND_ID, MEM_ID) "
				+ "VALUES (?, ?)",param);
	}
	
	public int addFriend2(List<Object> param4) {
		return jdbc.update("INSERT INTO FRIEND (FRIEND_ID, MEM_ID) "
				+ "VALUES (?, ?)",param4);
	}
	
	public int deleteFriend(ArrayList<Object> param) {
		return jdbc.update("DELETE FROM FRIEND WHERE FRIEND_ID = ? AND MEM_ID=?",param);		
	}
	
	public List<Map<String, Object>> selectFriend(String friendId) {
		
		return jdbc.selectList("SELECT * FROM ANIMALGRAM WHERE MEM_ID='"+friendId+"'");
	}
	
	public List<Map<String, Object>> friendlist(String userId) {
		return jdbc.selectList("SELECT * FROM ANIMALGRAM A, FRIEND B WHERE A.MEM_ID = B.FRIEND_ID AND B.MEM_ID = '"+userId+"' ORDER BY LPAD(ANI_NUM, 4, 0)");
	} 
	
	// 좋아요 DAO
	
	public int like(List<Object> param) {
		
		String sql = "INSERT INTO HEART(ANI_NUM, MEM_ID) "
				+ "VALUES(?, ?) ";
		
		return jdbc.update(sql, param);
	}
	
	public int dislike(List<Object> param) {
		
		String sql = "DELETE FROM HEART WHERE ANI_NUM = ? AND MEM_ID = ? ";
		
		return jdbc.update(sql, param);
	}
	
	public List<Map<String, Object>> likelist(List<Object> param) {
		
		String str = "SELECT * FROM HEART WHERE ANI_NUM =? AND MEM_ID = ?";
		
		return jdbc.selectList(str, param);
		
	}
	
	public List<Map<String, Object>> friendList(String userId) {
		return jdbc.selectList("SELECT FRIEND_ID FROM FRIEND WHERE MEM_ID='"+userId+"'");
	}
	
	// 댓글 DAO

	public int reply (List<Object> param) {
		
		String sql = "INSERT INTO REPLY(RE_NUM, ANI_NUM, MEM_ID, COMMENTS) "
				+ "VALUES(('R'||LPAD(REPLY_SEQ.NEXTVAL, 2, '0')), ?, ?, ?) ";
		
		return jdbc.update(sql, param);
	}
	
	public int re_delete (List<Object> param) {
		
		return jdbc.update("DELETE FROM REPLY WHERE RE_NUM = ? AND MEM_ID = ?", param);
	}
	
	public List<Map<String, Object>> re_list(String sql) {
		

		
		return jdbc.selectList(sql);
		
	}
	public Map<String, Object> insertCheck(List<Object> param) {

		String sql = "SELECT * FROM FRIEND WHERE FRIEND_ID = ? AND MEM_ID = ? " ;
		
		
		return jdbc.selectOne(sql, param);
	}
	public int ad_delete(List<Object> param) {
		
		String sql = "DELETE REPLY WHERE RE_NUM = ?";
		
		return jdbc.update(sql, param);
	}
	public int adminDelete(ArrayList<Object> param) {
	
		return jdbc.update("DELETE FROM ANIMALGRAM WHERE ANI_NUM = ?", param);
	}

	//추가
	
	public Map<String, Object> preList(List<Object> param5){
        
        String sql = "SELECT *  FROM(SELECT ANI_NUM, MEM_ID, MEM_TITLE, ANI_CONTENT,"
              + "                           LAG(ANI_NUM,1) OVER(ORDER BY LPAD(ANI_NUM, 4, 0)) AS PRENUM,"
              + "                           LAG(MEM_TITLE,1,'이전 게시글이 없습니다') OVER(ORDER BY LPAD(ANI_NUM, 4, 0)) AS PRETITLE,"
              + "                           LAG(ANI_CONTENT,1,' ') OVER(ORDER BY LPAD(ANI_NUM, 4, 0)) AS PRECONTENT"
              + "                      FROM ANIMALGRAM)"
              + "                      WHERE ANI_NUM=?"
              + "                       ORDER BY LPAD(ANI_NUM, 4, 0)";
              
              return jdbc.selectOne(sql, param5);
  }
     
     public Map<String, Object> nextList(List<Object> param6){
        
           String sql = "SELECT *  FROM(SELECT ANI_NUM, MEM_ID, MEM_TITLE, ANI_CONTENT,"
                   + "                        LEAD(ANI_NUM,1) OVER(ORDER BY LPAD(ANI_NUM, 4, 0)) AS NEXTNUM,"
                   + "                       LEAD(MEM_TITLE,1,'다음 게시글이 없습니다') OVER(ORDER BY LPAD(ANI_NUM, 4, 0)) AS NEXTTITLE,"
                   + "                        LEAD(ANI_CONTENT,1,' ') OVER(ORDER BY LPAD(ANI_NUM, 4, 0)) AS NEXTCONTENT"
                   + "                      FROM ANIMALGRAM)"
                   + "                      WHERE ANI_NUM=?"
                   + "                       ORDER BY LPAD(ANI_NUM, 4, 0)";
        return jdbc.selectOne(sql, param6);
     }
	
	
	
	
	
	
	
	
	
	
	
	
	// 관리자
	
	
	
	
}
