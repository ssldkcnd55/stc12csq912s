package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import member.model.service.*;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberDetailServlet
 */
@WebServlet("/mdetail")
public class MemberDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//회원 정보 상세 보기 게리롱푸리롱
		
		String userId = request.getParameter("userid");
		Member member = new MemberService().selectMember(userId);
		response.setContentType("text/html; charset=utf-8");
		RequestDispatcher view = null;
		if(member != null) {
			view = request.getRequestDispatcher("views/member/MemberDetailView.jsp");
			request.setAttribute("member", member);
			view.forward(request, response);
		} else {
			view = request.getRequestDispatcher("views/member/MemberError.jsp");
			request.setAttribute("message", "내 정보 보기 요청 처리 실패");
			view.forward(request, response);
		}
//		String userPwd = request.getParameter("username");
//		String userName = request.getParameter("userpwd");
//		String gender = request.getParameter("gender");
//		int Age = Integer.parseInt(request.getParameter("age"));
//		String email = request.getParameter("email");
//		String phone = request.getParameter("phone");		
//		String etc = request.getParameter("etc");
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
