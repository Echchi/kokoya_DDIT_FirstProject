package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCUtil {
	
	/*
	 * JDBC를 좀더 쉽고 편하게 사용하기 위한 Utility 클래스 
	 * 
	 * Map<String, Object> selectOne(String sql) 
	 * Map<String, Object> selectOne(String sql, List<object> param) 로우 하나만 반환 param은 ?를 채워줄 변수
	 * List<Map<String, Object>> selectList(String sql)
	 * List<Map<String, Object>> selectList(String sql, List<object> param) 여러줄의 로우를 반환
	 * int update(String sql)
	 * int update(String sql, List<Object> param)
	 * 
	 * */
	//싱글톤 패턴 : 인스턴스 생성을 제한하여 하나의 인스턴스만 사용하는 디자인패턴
	
	// 인스턴스를 보관할 변수 싱글턴을 사용시 아래 접근자와 변수설정을 기억하라 프라이빗 스태틱
	private static JDBCUtil instance = null;
	//JDBCUtil 객체를 만들수 없게(인스턴스화 할 수 없게) private으로 제한함
	private JDBCUtil() {}
	//싱글톤
	public static JDBCUtil getInstance() {
		if(instance == null) {
			instance = new JDBCUtil();
		}
		return instance;
	}

	
	private String URL = "jdbc:oracle:thin:@192.168.144.14:1521:xe";
	private String USER = "PRO_JHS";
	private String PASSWORD = "java";
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement ps =null;
	
	public List<Map<String, Object>> selectList(String sql, List<Object> param){
		// sql => "SELECT * FROM MEMBER WHERE MEM_ADD1 LIKE '%'||?||'%'"
		// sql => "SELECT * FROM JAVA_BOARD WHERE WRITER =?"
		// sql => "SELECT * FROM JAVA_BOARD WHERE BOARD_NUM > ?"
		
		List<Map<String, Object>> result = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			ps =conn.prepareStatement(sql);
			for(int i = 0; i<param.size(); i++) {
				ps.setObject(i+1, param.get(i));
			}
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while(rs.next()) {
				if(result ==null) result = new ArrayList<>();
				Map<String, Object> row = new HashMap<>();
				for(int i = 1; i <= columnCount; i++) {
					String key = rsmd.getColumnLabel(i);
					Object value = rs.getObject(i);
					row.put(key, value);
				}
				result.add(row);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(rs != null)try {rs.close();}catch(Exception e) {}
			if(ps != null)try {ps.close();}catch(Exception e) {}
			if(conn != null)try {conn.close();}catch(Exception e) {}
		}
		
		return result;
	}
	
	public List<Map<String, Object>> selectList(String sql){
		// sql => "SELECT * FROM MEMBER"
		// sql => "SELECT * FROM JAVA_BOARD"
		// sql => "SELECT * FROM JAVA_BOARD WHERE BOARD_NUM > 10"
		
		List<Map<String, Object>> result = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			ps =conn.prepareStatement(sql);
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while(rs.next()) {
				if(result ==null) result = new ArrayList<>();
				Map<String, Object> row = new HashMap<>();
				for(int i = 1; i <= columnCount; i++) {
					String key = rsmd.getColumnLabel(i);   //  
					Object value = rs.getObject(i);
					row.put(key, value);
				}
				result.add(row);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(rs != null)try {rs.close();}catch(Exception e) {}
			if(ps != null)try {ps.close();}catch(Exception e) {}
			if(conn != null)try {conn.close();}catch(Exception e) {}
		}
		
		return result;
	}
	
	public int update(String sql, List<Object> param) { //인서트에 적용한다 ?를 PARAM이 받는다
		// sql = > "DELETE FROM JAVA_BOARD WHERE BOARD_NUMBER=?"
		// sql = > "UPDATE JAVA_BOARD SET TITLE='하하' WHERE BOARD_NUMBER=?"
		// sql => "INSERT MY_MEMBER (MEM_ID, MEM_PASS, MEM_NAME) VALUES(?,?,?)"
		int result = 0;
		try {
			conn = DriverManager.getConnection(URL,USER,PASSWORD);
			ps = conn.prepareStatement(sql);
			for(int i = 0; i < param.size(); i++) {
				ps.setObject(i+1, param.get(i));
			}
			result = ps.executeUpdate();
				
				//{DATETIME=2022-08-05 21:33:08.0, WRITER=조현수, TITLE=가입인사, CONTENT=안녕하세요, ID=JHS2559, BOARD_NUMBER=1}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
		
			if(rs != null) try{	rs.close();}catch(Exception e) {}
			if(ps != null) try{	ps.close();}catch(Exception e) {}			
			if(conn != null) try{conn.close();}catch(Exception e) {	}
		
		}
		return result;
		
	}
	
	public int update(String sql) { //인서트에 적용한다 파람이 없기 때문에 ?을 안씀
		// sql = > "DELETE FROM JAVA_BOARD"
		// sql = > "UPDATE JAVA_BOARD SET TITLE='하하' WHERE BOARD_NUMBER=1"
		// sql => "INSERT MY_MEMBER (MEM_ID, MEM_PASS, MEM_NAME) VALUES('admin','1234','홍길동')"
		
		int result = 0;
		try {
			conn = DriverManager.getConnection(URL,USER,PASSWORD);
			ps = conn.prepareStatement(sql);
			result = ps.executeUpdate();
				
				//{DATETIME=2022-08-05 21:33:08.0, WRITER=조현수, TITLE=가입인사, CONTENT=안녕하세요, ID=JHS2559, BOARD_NUMBER=1}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
		
			if(rs != null) try{	rs.close();}catch(Exception e) {}
			if(ps != null) try{	ps.close();}catch(Exception e) {}			
			if(conn != null) try{conn.close();}catch(Exception e) {	}
		
		}
		return result;
		
	}
	
	public Map<String, Object> selectOne(String sql, List<Object> param){
		// sql => "SELECT * FROM JAVA_BOARD2 WHERE BOARD_NUMBER=?"
		// param => [1]
		//
		// sql => "SELECT * FROM JAVA_BOARD2 WHERE WRITER=? AND TITLE=?"
		// param => ["홍길동", "안녕"]
		Map<String, Object> row = null;
		
		try {
			conn = DriverManager.getConnection(URL,USER,PASSWORD);
			ps = conn.prepareStatement(sql);
			for(int i = 0; i < param.size(); i++){
					ps.setObject(i+1, param.get(i));
			}
			rs =ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData(); 
			int columnCount = rsmd.getColumnCount();
			while(rs.next()) {//rs.next()는 블린으로 참과 거짓값을 반환함
				row = new HashMap<>();
				for(int i = 1; i <=columnCount; i++) {
					String key = rsmd.getColumnLabel(i); // as를 사용하기 때문에 라벨 사용
					//getColumnName vs getColumnLabel
					//getColumnName : 원본 컬럼명을 가져옴
					//getColumnLabel : as 로 선언된 별명을 가져옴, 없으면 원본 컬럼명
					Object value = rs.getObject(i);
					row.put(key, value);
				}
				//{DATETIME=2022-08-05 21:33:08.0, WRITER=조현수, TITLE=가입인사, CONTENT=안녕하세요, ID=JHS2559, BOARD_NUMBER=1}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
		
			if(rs != null) try{	rs.close();}catch(Exception e) {}
			if(ps != null) try{	ps.close();}catch(Exception e) {}			
			if(conn != null) try{conn.close();}catch(Exception e) {	}
		
		}
		
		return row;
	}
	
	public Map<String, Object> selectOne(String sql){
//		sql => "SELECT * FROM JAVA_BOARD2 
//				WHERE BOARD_NUMBER=(SELECT MAX(BOARD_NUMBER) FROM JAVA_BOARD2)"
//		sql => "SELECT * FROM MEMBER MEM_ID='a001' AND MEM_PASS='123'" 
		Map<String, Object> row = null;
		
		try {
			conn = DriverManager.getConnection(URL,USER,PASSWORD);
			ps = conn.prepareStatement(sql);
			rs =ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while(rs.next()) {
				row = new HashMap<>();
				for(int i = 1; i <=columnCount; i++) {
					String key = rsmd.getColumnLabel(i); // as를 사용하기 때문에 라벨 사용
					//getColumnName vs getColumnLabel
					//getColumnName : 원본 컬럼명을 가져옴
					//getColumnLabel : as 로 선언된 별명을 가져옴, 없으면 원본 컬럼명
					Object value = rs.getObject(i);
					row.put(key, value);
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
		
			if(rs != null) try{	rs.close();}catch(Exception e) {}
			if(ps != null) try{	ps.close();}catch(Exception e) {}			
			if(conn != null) try{conn.close();}catch(Exception e) {	}
		
		}
		
		return row;
	}
	
	
	
	
	
}
