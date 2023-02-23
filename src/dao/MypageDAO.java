package dao;



public class MypageDAO {
	

	private static MypageDAO instance = null;
	private  MypageDAO() {}
	public static MypageDAO getInstance() {
		if(instance == null) instance = new MypageDAO();
		return instance;
	}

}
