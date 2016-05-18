package ncontrollers;

import java.sql.SQLException;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dao.MemberDao;
import vo.Member;

@Controller
@RequestMapping("/joinus/") //   /joinus/join.htm
public class JoinController {
  
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private JavaMailSender mailSender;

	
	@RequestMapping(value="join.htm" , method=RequestMethod.GET)
	public String join(){
		System.out.println("회원가입 페이지 요청");
		
		//이전
		//return "join.jsp";
		
		//Tiles
		return "joinus.join"; //{1}.{2}
	}
	
	/*@RequestMapping(value="join.htm" , method=RequestMethod.POST)
	public String join(Member member) throws ClassNotFoundException, SQLException{
		//가입처리 (DAO 단)
		System.out.println("회원가입");
		System.out.println(member.toString());
		memberDao.insert(member);
		return "redirect:../index.htm"; //수정 요망(주의 사항)
	}*/
	
	@RequestMapping(value="join.htm" , method=RequestMethod.POST)
	   public String join(Member member) throws ClassNotFoundException, SQLException{
	      //가입처리 (DAO 단)
	      System.out.println("회원가입");
	      System.out.println(member.toString());
	   
	      int result = memberDao.insert(member);
	      
	      if(result > 0){
	         
	         try{
	            MimeMessage message = mailSender.createMimeMessage();
	            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
	         
	            messageHelper.setFrom("betakosta1@gmail.com");
	            messageHelper.setTo(member.getEmail());
	            messageHelper.setSubject("환영합니다");
	            messageHelper.setText("환영합니다", member.getUserid() + " <html> 님 가입을 환영합니다<a href='http://localhost:8090/SpringMVC_Convert_Maven_Mybatis_Security_Mail/index.htm'>홈페이지</a></html>");
	         
	            //messageHelper.setText("가입을 환영합니다");          
	            
	            mailSender.send(message);
	         } catch(Exception e){
	            e.printStackTrace();
	         }
	      }
	      
	      return "redirect:../index.htm"; //수정 요망(주의 사항)
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
		return"joinus.forget";
	}
	
	//비밀번호_이메일
	@RequestMapping(value="forget.htm" , method=RequestMethod.POST)
	public String sendEmailOk(
			@RequestParam(value="userid", required=false) String userid, //required default=true
			@RequestParam(value="email", required=false) String email, 
			HttpSession session
			) throws ClassNotFoundException, SQLException
	{
		System.out.println("회원 email_check 실행");
		System.out.println("ID : " + userid + "/" + "email : " + email);
	
		//DAO 사용, 실제 DB에 insert
		String view = null;
		boolean emailresult = memberDao.memberEmail(userid, email);
		System.out.println("이메일 체크여부 결과 :  " + emailresult); //true면 정상적으로 DB에 insert됨
	
		if(emailresult==true)
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
			
		}//end if
	
		
		return "joinus.login";		
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
		
		int result = memberDao.update(member);
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
