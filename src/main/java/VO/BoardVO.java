package VO;

public class BoardVO {

	private int number;
	private String email;
	private String password;
	private String title;
	private String content;
	private String regDate;
	private String ip;
	private int view;
	private String fileName;
	private int fileSize;
	
	
	public BoardVO(int number, String email, String password, String title, String content, String regDate, String ip, int view,
			String fileName, int fileSize) {
		
		this.number = number;
		this.email = email;
		this.password = password;
		this.title = title;
		this.content = content;
		this.regDate = regDate;
		this.ip = ip;
		this.view = view;
		this.fileName = fileName;
		this.fileSize = fileSize;
	}
	
	public int getNumber() {
		return number;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public String getRegDate() {
		return regDate;
	}
	public String getIp() {
		return ip;
	}
	public int getView() {
		return view;
	}
	public String getFileName() {
		return fileName;
	}
	public int getFileSize() {
		return fileSize;
	}

	public BoardVO(int number, String title, String content) {
		
		this.number = number;
		this.title = title;
		this.content = content;
	}
	
	
	
	
}
