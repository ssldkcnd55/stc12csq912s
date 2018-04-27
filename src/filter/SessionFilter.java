package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import member.model.vo.Member;

/**
 * Servlet Filter implementation class SessionFilter
 */
//@WebFilter(filterName = "session", urlPatterns = { "/samp.ss" })
public class SessionFilter implements Filter {
//로그인 상태인지 체크하는 필터
    /**
     * Default constructor. 
     */
    public SessionFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 로그인 상태이면 서블릿으로 요청 전달하고
		// 로그인 상태가 아니면, memberError.jsp 를 response함
		System.out.println("SessionFilter 의 doFilter() run...");
		
		HttpServletRequest hRequest = (HttpServletRequest)request;
		
		String action = request.getParameter("action");		
		if(action != null && action.equals("loginCheck") == true) {
			Member loginUser = (Member)hRequest.getSession().getAttribute("loginUser");
			if(loginUser == null) {				
				RequestDispatcher view = hRequest.getRequestDispatcher("views/member/memberError.jsp");
				request.setAttribute("message", "로그인하셔야 합니다.");
				view.forward(request, response);				
			}else{				
				chain.doFilter(request, response);
			}
		}else{				
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
