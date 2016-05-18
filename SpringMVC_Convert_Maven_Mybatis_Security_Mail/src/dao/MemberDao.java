package dao;

import java.sql.SQLException;

import vo.Member;

public interface MemberDao {
	//회원정보 얻기
	public Member getMember(String userid) throws ClassNotFoundException, SQLException;
		
	//회원가입
	public int insert(Member member) throws ClassNotFoundException, SQLException;
	
	//비밀번호 변경
	public int update(Member member) throws ClassNotFoundException, SQLException;
	
	//이메일여부 확인
	boolean memberEmail(String userid, String email) throws ClassNotFoundException, SQLException;
	
}
