package VO;

public class ReplyVO {

	private int reply_number;
	private int board_number;
	private String writer;
	private String comment;
	private String reg_date;
	
	public ReplyVO(int board_number, String writer, String comment) {
		
		this.board_number = board_number;
		this.writer = writer;
		this.comment = comment;
	}
	
	public ReplyVO(int board_number, String writer, String comment, String reg_date) {
		
		this.board_number = board_number;
		this.writer = writer;
		this.comment = comment;
		this.reg_date = reg_date;
	}	

	public int getReply_number() {
		return reply_number;
	}

	public int getBoard_number() {
		return board_number;
	}

	public String getWriter() {
		return writer;
	}

	public String getComment() {
		return comment;
	}

	public String getReg_date() {
		return reg_date;
	}
	
}
