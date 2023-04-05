package together.pet.domain.member.dto;


import java.util.Date;

import lombok.Data;

/**
 * 회원 DTO
 */
@Data
public class Member {
	private String id; 
	private String password ;
	private String name ;
	private String email; 
	private String birth ;
	private String gender ;
	private String phonenum ;
	private String admin;
	private String year;
	private String month;
	private String date;
	
	public Member() {}

	public Member(String id, 
			String password, 
			String name, 
			String email, 
			String birth, 
			String gender, 
			String phonenum,
			String admin, 
			String year, String month, String date) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
		this.birth = birth;
		this.gender = gender;
		this.phonenum = phonenum;
		this.admin = admin;
		this.year = year;
		this.month = month;
		this.date = date;
	}
	
	
	
	
}
