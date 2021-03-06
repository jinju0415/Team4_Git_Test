package ncontrollers;

import java.sql.SQLException;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.JoinService;
import vo.Member;

@Controller
@RequestMapping("/joinus/") //   /joinus/join.htm
public class JoinController {
  
	@Autowired
	private JoinService joinService;
	
	@Autowired
	private JavaMailSender mailSender;
	
	
	//회원가입 페이지 화면
	@RequestMapping(value="join.htm" , method=RequestMethod.GET)
	public String join(){
		System.out.println("회원가입 페이지 요청");	
		//이전
		//return "join.jsp";
		
		//Tiles
		return "joinus.join"; //{1}.{2}
	}
	
	
	//회원가입 요청 처리
	@RequestMapping(value="join.htm" , method=RequestMethod.POST)
	public String join(Member member) throws ClassNotFoundException, SQLException {
		
		int result = joinService.JoinOk(member);
		
		// 가입처리 (DAO 단)
		System.out.println("회원가입");
		System.out.println(member.toString());

		/*if (result > 0) {

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
		}*/

		return "redirect:../index.htm"; // 수정 요망(주의 사항)
	}
	
	
	//로그인 페이지 이동
	@RequestMapping(value="login.htm", method=RequestMethod.GET)
	public String login()
	{
		return "joinus.login";
	}
	
	
	//비밀번호_이메일_화면
	@RequestMapping(value="forget.htm" , method=RequestMethod.GET)
	public String sendEmail()
	{
		System.out.println("비밀번호_이메일 화면");
		return"joinus.forget";
	}
	
	//비밀번호_이메일
	@RequestMapping(value="forget.htm" , method=RequestMethod.POST)
	public String sendEmailOk(String userid, String email, Model model) throws ClassNotFoundException, SQLException
	{
		System.out.println("회원 email_check 실행");
		System.out.println("ID : " + userid + "/" + "email : " + email);

		String view = null;
		
		Member member = joinService.memberEmailCheck(userid, email);
		System.out.println("이메일 체크 여부:  " + member.getEmail()); 
		String usermail = member.getEmail();
		
		//System.out.println("이메일 체크 여부:  " + member.getEmail()); //true면 정상적으로 DB에 insert됨
	
		if(usermail != null)
		{
			try {
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

				messageHelper.setFrom("betakosta1@gmail.com");
				messageHelper.setTo(email);
				messageHelper.setSubject("비밀번호변경");
				messageHelper.setText("환영합니다", userid
						+ " <html> 비밀번호 변경시 <a href='http://192.168.0.9:8090/SpringMVC_Convert_Maven_Mybatis_Security_Mail/joinus/change.htm'>비밀번호변경</a></html>");

				// messageHelper.setText("가입을 환영합니다");
				mailSender.send(message);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			view = "joinus.login";
		}//end if	
		
		else 
		{
			view ="redirect:..index.htm";
		}
		
		return view;		
	}
	
	
	//비밀번호 변경_화면
	@RequestMapping(value="change.htm", method=RequestMethod.GET)
	public String changePWD()
	{
		System.out.println("비밀번호 변경 화면");
		return "joinus.change";
	}
	
	//비밀번호 변경_로직
	@RequestMapping(value="change.htm", method=RequestMethod.POST)
	public String changePWDOk(Member member) throws ClassNotFoundException, SQLException
	{
		System.out.println("비밀번호 변경 처리");
		System.out.println("비밀번호 : " + member.getUserid() + "/ " + member.getPwd());
		
		int result = joinService.updateOk(member);
		System.out.println("비밀번호 결과 : " + result);
		String view = null;
		
		
		if(result > 0 )
		{
			view = "joinus.login";
		}
		else
		{
			view = "joinus.error";
		}
		
		return view;
	}
}
