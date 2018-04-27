package member.model.service;

import member.model.dao.MemberDao;
import member.model.vo.Member;
//import static common.JDBCTemplate.*;
import java.sql.*;
import java.util.ArrayList;

public class MemberService {

	public MemberService() {}

	public Member loginCheck(Member member) {
		return new MemberDao().loginCheck(member);
	}

	public int insertMember(Member member) {
		return new MemberDao().insertMember(member);
	}
	
	public int updateMember(Member member) {
		return new MemberDao().updateMember(member);
	}
	
	public int deleteMember(String userId) {
		return new MemberDao().deleteMember(userId);
	}
	
	public Member selectMember(String userId) {
		return new MemberDao().selectMember(userId);
	}
	
	public ArrayList<Member> selectAll(){
		return new MemberDao().selectList();
	}
	
	public int selectCheckId(String userId) {
		return new MemberDao().selectCheckId(userId);
	}
	
	/*public Member loginCheck(String userId, String userPwd) {

		Connection con = getConnection();
		System.out.println("con : " + con);
		Member member = new MemberDao().loginMember(con, userId, userPwd);

		close(con);

		return member;
	}

	public int insertMember(Member member) {
		Connection con = getConnection();
		int result = new MemberDao().insertMember(con, member);
		if (result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		return result;
	}

	
	public int updateMember(Member member) {
		Connection con = getConnection();
		int result = new MemberDao().updateMember(con, member);
		if (result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		return result;
	}
	


	public int deleteMember(String userId) {
		Connection con = getConnection();
		int result = new MemberDao().deleteMember(con, userId);
		if (result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		return result;
	}

	public Member selectMember(String userId) {
		Connection con = getConnection();
		Member member = new MemberDao().selectMember(con, userId);
		close(con);
		return member;
	}

	public ArrayList<Member> selectList() {

		Connection con = getConnection();
		ArrayList<Member> list = new MemberDao().selectList(con);
		close(con);

		return list;
	}

	public int selectCheckId(String userId) {
		Connection con = getConnection();
		int result = new MemberDao().selectCheckId(con, userId);
		close(con);
		return result;
	}*/
}
