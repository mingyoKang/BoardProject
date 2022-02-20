package tmp;

import org.mindrot.jbcrypt.BCrypt;

public class Bcrypt {

	public static void main(String[] args) {
		
		String tmp = BCrypt.hashpw("1234", BCrypt.gensalt());
		// password를 해쉬화시키는 과정
		// 비밀번호 원문이 그대로 노출되지 않도록 암호화시켜준다.
		// gensalt(): generate salt 솔트를 생성한다.
		// 아이디간의 비밀번호가 같은 경우, 솔트값을 추가해 다르게 암호화되도록한다.
		
		System.out.println("PW: " + tmp);
		
		System.out.println("PW CHECK: " + BCrypt.checkpw("1234", tmp));
		// 해쉬화된 비밀번호와 로그인된 비밀번호가 같은지 검사
		
	}
	
}
