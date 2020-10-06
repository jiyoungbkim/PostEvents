package dto;

public class Notice_DTO {
	String notice_no, title, content, file_name_1, reg_id, reg_date;
	int hit;
	
	public Notice_DTO() {
		super();
	}
	public Notice_DTO(String notice_no, String title, String content, String reg_date) {
		super();
		this.notice_no 		= notice_no;
		this.title 			= title;
		this.content 		= content;
		this.reg_date 		= reg_date;
	}
	
	public Notice_DTO(String notice_no, String title, String content, String reg_id, String reg_date, int hit) {
		super();
		this.notice_no 		= notice_no;
		this.title 			= title;
		this.content 		= content;
		this.reg_id 		= reg_id;
		this.reg_date 		= reg_date;
		this.hit 			= hit;
	}
	
	public Notice_DTO(String notice_no, String title, String content, String file_name_1, String reg_id,
			String reg_date, int hit) {
		super();
		this.notice_no 		= notice_no;
		this.title 			= title;
		this.content 		= content;
		this.file_name_1    = file_name_1;	
		this.reg_id 		= reg_id;
		this.reg_date 		= reg_date;
		this.hit 			= hit;
	}

	public String getNotice_no() {
		return notice_no;
	}
	public void setNotice_no(String notice_no) {
		this.notice_no = notice_no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFile_name_1() {
		return file_name_1;
	}
	public void setFile_name_1(String file_name_1) {
		this.file_name_1 = file_name_1;
	}
	public String getReg_id() {
		return reg_id;
	}
	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
}