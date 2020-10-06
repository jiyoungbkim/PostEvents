package dto;

public class Member_DTO {
	String id, pw, name, phone, email_1, email_2,
	agree, yak1, yak2,status;

	
	
	public Member_DTO() {
		super();
	}
	
	

	public Member_DTO(String id, String pw, String name, String phone, String email_1, String email_2, String agree, String status) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.phone = phone;
		this.email_1 = email_1;
		this.email_2 = email_2;
		this.agree = agree;
		this.status = status;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail_1() {
		return email_1;
	}

	public void setEmail_1(String email_1) {
		this.email_1 = email_1;
	}

	public String getEmail_2() {
		return email_2;
	}

	public void setEmail_2(String email_2) {
		this.email_2 = email_2;
	}

	public String getAgree() {
		return agree;
	}

	public void setAgree(String agree) {
		this.agree = agree;
	}

	public String getYak1() {
		return yak1;
	}

	public void setYak1(String yak1) {
		this.yak1 = yak1;
	}

	public String getYak2() {
		return yak2;
	}

	public void setYak2(String yak2) {
		this.yak2 = yak2;
	} 
	
	
	
}
