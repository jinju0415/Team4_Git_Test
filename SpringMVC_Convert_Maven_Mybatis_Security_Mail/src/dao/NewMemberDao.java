package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import vo.Member;


public class NewMemberDao  implements MemberDao{
	private JdbcTemplate template;
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	@Override
	public Member getMember(String userid) throws ClassNotFoundException,
			SQLException {
    //코드 완성
    //new RowMapper 구현하는 코드로
		List<Member> results = template.query(
				"SELECT * FROM MEMBER WHERE USERID=?",
				new RowMapper<Member>() {
					@Override
					public Member mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Member member = new Member( //생성자
								 rs.getString("USERID"),
								 rs.getString("PWD"),
								 rs.getString("NAME"),
								 rs.getString("GENDER"),
								 rs.getString("BIRTH"),
								 rs.getString("IS_LUNAR"),
								 rs.getString("CPHONE"),
								 rs.getString("EMAIL"),
								 rs.getString("HABIT")
								);
						return member;
					}
				},
				userid);

		return results.isEmpty() ? null : results.get(0);
	}

	public int insert(final Member member) throws ClassNotFoundException, SQLException
	{
	 return	template.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				String sql = "INSERT INTO MEMBER(USERID, PWD, NAME, GENDER, BIRTH, IS_LUNAR, CPHONE, EMAIL, HABIT, REGDATE) VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, member.getUserid());
				pstmt.setString(2, member.getPwd());
				pstmt.setString(3, member.getName());
				pstmt.setString(4, member.getGender());
				pstmt.setString(5, member.getBirth());
				pstmt.setString(6, member.getIsLunar());
				pstmt.setString(7, member.getCPhone());
				pstmt.setString(8, member.getEmail());
				pstmt.setString(9, member.getHabit());
				return pstmt;
			}
		});
		
	}
	
	@Override
	public int update(final Member member) throws ClassNotFoundException, SQLException {
		
		return	template.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				String sql = "update member set pwd=? where userid=?";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, member.getPwd());
				pstmt.setString(2, member.getUserid());
			
				return pstmt;
			}
		});
	}

	//이메일 여부 확인
	@Override
	public boolean memberEmail(String userid, String email) throws ClassNotFoundException, SQLException {
		
		String sql = "select * from member where userid=? and email=?";
		
		boolean result = false;
		Object[] params ={userid,email};
		//params[0] = kkk
		//params[0] = 1004
		
		if(this.template.queryForList(sql,params).size() >0) //queryForList가 있으면 여러 건에 데이터 처리할 때 사용
		{
			//size가 0보다 크다면 DB에 회원이 존재 한다는 것
			result = true; //true 면 회원 존재!
		}
		
		return result;
	}
}
