package dto;

public class Event_DTO {
	String event_no,
	title,
	content,
	reg_id,
	reg_date,
	reg_start,
	reg_end;
	int hit;
	
	public Event_DTO() {
		super();
	}
	
	public Event_DTO(String event_no, String title, String reg_start, String reg_end, String content, String reg_id,
			String reg_date) {		
		super();
		this.event_no = event_no;
		this.title = title;
		this.reg_start = reg_start;
		this.reg_end = reg_end;		
		this.content = content;
		this.reg_id = reg_id;
		this.reg_date = reg_date;
	}
	
	public Event_DTO(String event_no, String title, String reg_start, String reg_end, String content, String reg_id,
			String reg_date, int hit) {		
		super();
		this.event_no = event_no;
		this.title = title;
		this.reg_start = reg_start;
		this.reg_end = reg_end;		
		this.content = content;
		this.reg_id = reg_id;
		this.reg_date = reg_date;
		this.hit = hit;
	}

	public String getEvent_no() {
		return event_no;
	}

	public void setEvent_no(String event_no) {
		this.event_no = event_no;
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

	public String getReg_start() {
		return reg_start;
	}

	public void setReg_start(String reg_start) {
		this.reg_start = reg_start;
	}

	public String getReg_end() {
		return reg_end;
	}

	public void setReg_end(String reg_end) {
		this.reg_end = reg_end;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}
	
	
}
