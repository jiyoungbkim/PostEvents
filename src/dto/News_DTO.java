package dto;

public class News_DTO {
	String news_no, title, content, reg_id, reg_date, file_name_1;
	int hit;
		
	public News_DTO() {
		super();
	}
	public News_DTO(String news_no, String title, String content, String reg_date) {
		super();
		this.news_no 		= news_no;
		this.title 			= title;
		this.content 		= content;
		this.reg_date 		= reg_date;
	}
	
	public News_DTO(String news_no, String title, String content, String reg_id, String reg_date, String file_name_1, int hit) {
		super();
		this.news_no = news_no;
		this.title = title;
		this.content = content;
		this.reg_id = reg_id;
		this.reg_date = reg_date;
		this.file_name_1 = file_name_1;
		this.hit = hit;

	}
	public String getNews_no() {
		return news_no;
	}
	public void setNews_no(String news_no) {
		this.news_no = news_no;
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
	public String getFile_name_1() {
		return file_name_1;
	}
	public void setFile_name_1(String file_name_1) {
		this.file_name_1 = file_name_1;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	
	
}
