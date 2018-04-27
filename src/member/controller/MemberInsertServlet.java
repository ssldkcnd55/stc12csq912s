package member.controller;

import java.io.*;
import java.util.*;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberInsertServlet
 */
@WebServlet("/minsert")
public class MemberInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//회원 가입 처리용 컨트롤러
		//1. 전송 온 값에 한글이 있을때, 인코딩 처리함
		//request.setCharacterEncoding("utf-8");
				
		//2. 전송 온 값 꺼내서 변수에 또는 객체 안에 기록 저장하기.
		Member member = new Member();
		member.setUserId(request.getParameter("userid"));
		member.setUserName(request.getParameter("username"));
		member.setUserPwd(request.getParameter("userpwd"));
		member.setGender(request.getParameter("gender"));
		member.setAge(Integer.parseInt(request.getParameter("age")));
		member.setEmail(request.getParameter("email"));
		member.setPhone(request.getParameter("phone"));		
		member.setEtc(request.getParameter("etc"));	
		
		//한개의 이름으로 여러개의 값이 전송 온 경우
		String[] hobbies = request.getParameterValues("hobby");
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < hobbies.length; i++) {
			if(i < hobbies.length - 1) {
				sb.append(hobbies[i] + ",");
			} else {
				sb.append(hobbies[i]);
			}
		}
		
		member.setHobby(sb.toString());
		
		//String.join 사용
		
		/*List<String> list = ArrayList<String>();
		for(String s : hobbies) {			
			list.add(s);			
		}
		
		member.setHobby(String.join(",", list));*/		
		
		System.out.println(member);
		
		//3. 비즈니스 로직 처리하는 서비스로 객체 또는 값 전달하고
		//처리결과 받아서, 결과에 따라 뷰 선택해서 응답 처리함.
		int result = new MemberService().insertMember(member);
		
		//4. 받은 결과에 따라 뷰 선택해서 응답 처리함
		response.setContentType("text/html; charset=utf-8");
		if(result > 0) {			
			response.sendRedirect("index.jsp");
		} else {
			//뷰 파일 지정시 절대경로 사용 못하는 메소드임
			//상대 경로만 사용 가능함.
			RequestDispatcher view = request.getRequestDispatcher("views/member/memberError.jsp");
			request.setAttribute("message", "회원 가입 실패");
			view.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
