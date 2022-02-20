package VO;

public class MemberVO {
	// VO: getter 밖에 못 사용
	// DTO: getter, setter 모두 사용
	
	private String email;
	private String password;
	private String address01;
	private String address02;
	private int zipcode;
	private String role;
	
	// 생성자 생성
	public MemberVO(String email, String password, String address01, String address02, int zipcode) {
		this.email = email;
		this.password = password;
		this.address01 = address01;
		this.address02 = address02;
		this.zipcode = zipcode;
	}	
	
	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getAddress01() {
		return address01;
	}
	
	public String getAddress02() {
		return address02;
	}
	
	public int getZipcode() {
		return zipcode;
	}
	
	public String getRole() {
		return role;
	}
	
	
	
}
