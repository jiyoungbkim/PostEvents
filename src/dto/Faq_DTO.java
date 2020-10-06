package dto;

public class Faq_DTO {
	String faq_no,
	title,
	content,
	reg_id,
	reg_date;

	public Faq_DTO() {
		super();
	}

	public Faq_DTO(String faq_no, String title, String content, String reg_id, String reg_date) {
		super();
		this.faq_no = faq_no;
		this.title = title;
		this.content = content;
		this.reg_id = reg_id;
		this.reg_date = reg_date;
	}

	public String getFaq_no() {
		return faq_no;
	}

	public void setFaq_no(String faq_no) {
		this.faq_no = faq_no;
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

	
	
}
