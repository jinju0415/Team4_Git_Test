package service;

import java.sql.SQLException;

import javax.mail.internet.MimeMessage;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import dao.MemberDao;
import vo.Member;

@Service
public class JoinService {
	
	@Autowired
	private SqlSession sqlsession;
	
	@Autowired
	private JavaMailSender mailSender;
	
	//회원정보 얻기
	public Member getMember(String userid) throws ClassNotFoundException, SQLException
	{
		MemberDao memberDao = sqlsession.getMapper(MemberDao.class);
		Member member = memberDao.getMember(userid);
		
		return member;
	}
	
	//회원가입
	public int JoinOk(Member member) throws ClassNotFoundException, SQLException
	{
		MemberDao memberDao = sqlsession.getMapper(MemberDao.class);
		int result = memberDao.insert(member);
		
		if (result > 0) {

			try {
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

				messageHelper.setFrom("betakosta1@gmail.com");
				messageHelper.setTo(member.getEmail());
				messageHelper.setSubject("환영합니다");
				messageHelper.setText("환영합니다", member.getUserid()
						+ " <html> 님 가입을 환영합니다<a href='http://localhost:8090/SpringMVC_Convert_Maven_Mybatis_Security_Mail/index.htm'>홈페이지</a></html>");

				// messageHelper.setText("가입을 환영합니다");

				mailSender.send(message);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		
		return result;		
	}
	
	//비밀번호 변경
	public int updateOk(Member member) throws ClassNotFoundException, SQLException
	{
		MemberDao memberDao = sqlsession.getMapper(MemberDao.class);
		int result = memberDao.update(member);
		
		return result;
	}
	
	//이메일여부 확인
	public Member memberEmailCheck(String userid, String email) throws ClassNotFoundException, SQLException
	{
		System.out.println("service 실행: " + userid +"/" + email);
		MemberDao memberDao = sqlsession.getMapper(MemberDao.class);		
		Member member = memberDao.memberEmail(userid, email);
		System.out.println(member.toString());
		
		return member;
	}
	

}
