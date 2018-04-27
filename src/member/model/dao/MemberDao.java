package member.model.dao;

/*import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;*/

import member.model.vo.Member;
import java.sql.*;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MemberDao {
	public MemberDao() {}

	//로그인 확인 처리용 
	public Member loginMember(Member member) {
		Member m = null;
		SqlSession session = null;
		
		try {
			/* 마이바티스 config 설정파일의 내용을 읽어서
			 * db 연결하고, statement 객체 생성과 동일한
			 * 의미를 가진 코드*/
			session = new SqlSessionFactoryBuilder().build(
					Resources.getResourceAsStream(
					"mybatis/mybatis-config.xml")).openSession(false);
		
			//매퍼 파일안의 작성된 쿼리문을 실행시키고 결과받음
			m = session.selectOne(
					"member.loginMember", member);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return m;
	}
	
	public int insertMember(Member member) {
		int result = 0;
		SqlSession session = null;
		
		try {
			/* 마이바티스 config 설정파일의 내용을 읽어서
			 * db 연결하고, statement 객체 생성과 동일한
			 * 의미를 가진 코드*/
			session = new SqlSessionFactoryBuilder().build(
					Resources.getResourceAsStream(
					"mybatis/mybatis-config.xml")).openSession(false);
		
			//매퍼 파일안의 작성된 쿼리문을 실행시키고 결과받음
			result = session.insert(
					"member.insertMember", member);
			
			if(result > 0)
				session.commit();
			else
				session.rollback();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return result;
	}
	
	public int updateMember(Member member) {
		int result = 0;
		SqlSession session = null;
		
		try {
			/* 마이바티스 config 설정파일의 내용을 읽어서
			 * db 연결하고, statement 객체 생성과 동일한
			 * 의미를 가진 코드*/
			session = new SqlSessionFactoryBuilder().build(
					Resources.getResourceAsStream(
					"mybatis/mybatis-config.xml")).openSession(false);
		
			//매퍼 파일안의 작성된 쿼리문을 실행시키고 결과받음
			result = session.update(
					"member.updateMember", member);
			
			if(result > 0)
				session.commit();
			else
				session.rollback();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return result;
	}
	
	public int deleteMember(String userId) {
		int result = 0;
		SqlSession session = null;
		
		try {
			/* 마이바티스 config 설정파일의 내용을 읽어서
			 * db 연결하고, statement 객체 생성과 동일한
			 * 의미를 가진 코드*/
			session = new SqlSessionFactoryBuilder().build(
					Resources.getResourceAsStream(
					"mybatis/mybatis-config.xml")).openSession(false);
		
			//매퍼 파일안의 작성된 쿼리문을 실행시키고 결과받음
			result = session.delete(
					"member.deleteMember", userId);
			
			if(result > 0)
				session.commit();
			else
				session.rollback();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return result;
	}

	public Member selectMember(String userId) {
		Member m = null;
		SqlSession session = null;
		
		try {
			/* 마이바티스 config 설정파일의 내용을 읽어서
			 * db 연결하고, statement 객체 생성과 동일한
			 * 의미를 가진 코드*/
			session = new SqlSessionFactoryBuilder().build(
					Resources.getResourceAsStream(
					"mybatis/mybatis-config.xml")).openSession(false);
		
			//매퍼 파일안의 작성된 쿼리문을 실행시키고 결과받음
			m = session.selectOne(
					"member.selectMember", userId);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return m;
	}
	
	public ArrayList<Member> selectList() {
		List<Member> list = null;
		SqlSession session = null;
		
		try {
			session = new SqlSessionFactoryBuilder().build(
					Resources.getResourceAsStream("mybatis/mybatis-config.xml"))
					.openSession(false);
			
			list = session.selectList("member.selectAll");			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		return (ArrayList<Member>)list;
	}

	public int selectCheckId(String userId) {
		int count = 0;
		SqlSession session = null;
		
		try {			
			session = new SqlSessionFactoryBuilder().build(
					Resources.getResourceAsStream(
					"mybatis/mybatis-config.xml")).openSession(false);
		
			//매퍼 파일안의 작성된 쿼리문을 실행시키고 결과받음
			count = session.selectOne(
					"member.selectCheckId", userId);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}		
		
		return count;
	}
	
	/*private Properties prop;
	
	public MemberDao() {
		prop = new Properties();
		
		try {
			prop.load(new FileReader(
					MemberDao.class.getResource(
						"/dbresource/member.properties").getPath()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Member loginMember(Connection con, 
			String userId, String userPwd) {
		
		Member loginUser = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from member where userid= ? "
				+ "and userpwd = ?";
		
		try {
			//pstmt = con.prepareStatement(query);
			pstmt = con.prepareStatement(
					prop.getProperty("loginMember"));
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				loginUser = new Member();
				
				loginUser.setUserId(rset.getString("userid"));
				loginUser.setUserPwd(rset.getString("userpwd"));
				loginUser.setUserName(rset.getString("username"));
				loginUser.setGender(rset.getString("gender"));
				loginUser.setAge(rset.getInt("age"));
				loginUser.setPhone(rset.getString("phone"));
				loginUser.setEmail(rset.getString("email"));
				loginUser.setHobby(rset.getString("hobby"));
				loginUser.setEtc(rset.getString("etc"));
				loginUser.setEnroll(rset.getDate("enroll_date"));
				loginUser.setLastModified(rset.getDate("lastmodified"));
			}
			
			//System.out.println("dao : " + loginUser);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return loginUser;		
	}

	public int insertMember(Connection con, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		//String query = "insert into member values (?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate, sysdate)";
		
		 try {
			 
			//pstmt = con.prepareStatement(query);
			pstmt = con.prepareStatement(prop.getProperty("insertMember"));
			pstmt.setString(1, member.getUserId());
			pstmt.setString(2, member.getUserPwd());
			pstmt.setString(3, member.getUserName());
			pstmt.setString(4, member.getGender());
			pstmt.setInt(5, member.getAge());
			pstmt.setString(6, member.getPhone());
			pstmt.setString(7, member.getEmail());
			pstmt.setString(8, member.getHobby());
			pstmt.setString(9, member.getEtc());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateMember(Connection con, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;		
		String query = "update member set userpwd = ?, age = ?, "
				+ "phone = ?, email = ?, hobby = ?, etc = ?, "
				+ "lastmodified = sysdate where userid = ?";
		
		try {
			//pstmt = con.prepareStatement(query);
			pstmt = con.prepareStatement(prop.getProperty("updateMember"));
			
			pstmt.setString(1, member.getUserPwd());			
			pstmt.setInt(2, member.getAge());
			pstmt.setString(3, member.getPhone());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getHobby());
			pstmt.setString(6, member.getEtc());
			pstmt.setString(7, member.getUserId());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}		
		return result;
	}
	public int deleteMember(Connection con, String userId) {
		int result = 0;	
		PreparedStatement pstmt = null;
		
		//String query = "delete from member where userid = ?";
		
		try {
			//pstmt = con.prepareStatement(query);
			pstmt= con.prepareStatement(prop.getProperty("deleteMember"));
			
			pstmt.setString(1, userId);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public Member selectMember(Connection con, String userId) {
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		//String query = "select * from member where userid = ?";
		
		try {
			//pstmt = con.prepareStatement(query);
			pstmt = con.prepareStatement(prop.getProperty("selectMember"));
			
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				member = new Member();
				member.setUserId(userId);
				member.setUserPwd(rset.getString("userpwd"));
				member.setUserName(rset.getString("username"));
				member.setGender(rset.getString("gender"));
				member.setAge(rset.getInt("age"));
				member.setPhone(rset.getString("phone"));
				member.setEmail(rset.getString("email"));
				member.setHobby(rset.getString("hobby"));
				member.setEtc(rset.getString("etc"));
				member.setEnroll(rset.getDate("enroll_date"));
				member.setLastModified(rset.getDate("lastmodified"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
				
		return member;
	}

	public ArrayList<Member> selectList(Connection con) {
		ArrayList<Member> list = new ArrayList<Member>();
		
		Statement stmt = null;
		ResultSet rset = null;
		
		//String query = "select * from member";
		
		try {
			stmt = con.createStatement();
			//rset = stmt.executeQuery(query);
			rset = stmt.executeQuery(prop.getProperty("selectList"));
			
			while(rset.next()) {
				Member m = new Member();
				m.setUserId(rset.getString("userid"));
				m.setUserPwd(rset.getString("userpwd"));
				m.setUserName(rset.getString("username"));
				m.setGender(rset.getString("gender"));
				m.setAge(rset.getInt("age"));
				m.setPhone(rset.getString("phone"));
				m.setEmail(rset.getString("email"));
				m.setHobby(rset.getString("hobby"));
				m.setEtc(rset.getString("etc"));
				m.setEnroll(rset.getDate("enroll_date"));
				m.setLastModified(rset.getDate("lastmodified"));
				
				list.add(m);								
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(rset);
		}
		
		return list;
	}

	public int selectCheckId(Connection con, String userId) {
		int idCount = -1;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select count(userid) from member "
				+ "where userid = ?";
		
		try {
			//pstmt = con.prepareStatement(query);
			pstmt = con.prepareStatement(
					prop.getProperty("selectCheckId"));
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				idCount = rset.getInt(1);
				//System.out.println("아이디 갯수 : " + idCount);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return idCount;
	}*/
	
}
