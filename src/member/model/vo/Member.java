package member.model.vo;

import java.sql.*;

//vo객체
/* 1. 반드시 직렬화 한다.
 * 2. 캡슐화 할 것 : 필드는 반드시 프라이빗
 * 3. 기본 생성자와 매개변수 있는 생성자 작성할 것
 * 4. 모든 필드에 대한 게터 세터 작성할 것
 * 
 * */

public class Member implements java.io.Serializable {
	private final static long serialVersionUID = 7L;
	
	private String userId;
	private String userPwd;
	private String userName;
	private String gender;
	private int age;
	private String phone;
	private String email;
	private String hobby;
	private String etc;
	private Date enroll;
	private Date lastModified;
	
	public Member() {
		
	}
	
	
	
	public Member(String userId, String userPwd, String userName, String gender, int age, String phone, String email,
			String hobby, String etc, Date enroll, Date lastModified) {
		
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.gender = gender;
		this.age = age;
		this.phone = phone;
		this.email = email;
		this.hobby = hobby;
		this.etc = etc;
		this.enroll = enroll;
		this.lastModified = lastModified;
		
	}
	
	public Member(String userId, String userPwd, String userName, String gender, int age, String phone, String email,
			String hobby, String etc) {
		
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.gender = gender;
		this.age = age;
		this.phone = phone;
		this.email = email;
		this.hobby = hobby;
		this.etc = etc;
		
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
	public Date getEnroll() {
		return enroll;
	}
	public void setEnroll(Date enroll) {
		this.enroll = enroll;
	}
	public Date getLastModified() {
		return lastModified;
	}
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		
		return this.userId + "," + this.userPwd + "," + 
				this.userName + "," + this.gender + "," + 
				this.age + "," + this.email + "," + 
				this.phone + "," + this.hobby + "," + 
				this.etc + "," + this.enroll + "," + 
				this.lastModified;
		
			}	
}
