package Service;

import DAO.MemberDAO;
import VO.MemberVO;

public class MemberService {
	
	// DAO 연결용 참조변수
	MemberDAO dao = null;

	// Singleton Start
	private static MemberService instance = new MemberService();
	
	private MemberService() {
		dao = MemberDAO.getInstance();
	};
	
	public static MemberService getInstance() {
		
		if(instance == null) {
			instance = new MemberService();
		}
		
		return instance;
	}
	// Singleton End
	
	// Join 함수
	public boolean MemberJoin(MemberVO vo) {
		return dao.MemberJoin(vo);
	}
	
	// Search 함수
	public MemberVO MemberSearch(String email) {
		return dao.MemberSearch(email);
	}
	
	// List 함수
	
	// Delete 함수
	
	// Update 함수
}
