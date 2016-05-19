package vo;

import java.util.Date;

public class Member {
	private String userid;
	private String pwd;
	private String name;
	private String gender;
	private String birth;
	private String is_lunar;
	private String cphone;
	private String email;
	private String habit;
	private Date regDate;
	private int point;

	public Member() {
	}

	public Member(String userid, String pwd, String name, String gender, String birth, String is_lunar, String cphone,
			String email, String habit) {

		this.userid = userid;
		this.pwd = pwd;
		this.name = name;
		this.gender = gender;
		this.birth = birth;
		this.is_lunar = is_lunar;
		this.cphone = cphone;
		this.email = email;
		this.habit = habit;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getIs_lunar() {
		return is_lunar;
	}

	public void setIs_lunar(String is_lunar) {
		this.is_lunar = is_lunar;
	}

	public String getCphone() {
		return cphone;
	}

	public void setCphone(String cphone) {
		this.cphone = cphone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHabit() {
		return habit;
	}

	public void setHabit(String habit) {
		this.habit = habit;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	@Override
	public String toString() {
		return "Member [userid=" + userid + ", pwd=" + pwd + ", name=" + name + ", gender=" + gender + ", birth="
				+ birth + ", is_lunar=" + is_lunar + ", cphone=" + cphone + ", email=" + email + ", habit=" + habit
				+ ", regDate=" + regDate + "]";
	}

}
