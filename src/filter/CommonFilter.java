package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import wrapper.CommonWrapper;

/**
 * Servlet Filter implementation class CommonFilter
 */
//@WebFilter(filterName = "encoding", urlPatterns = { "/*" })
public class CommonFilter implements Filter {
	//필터 클래스는 반드시 Filter 인터페이스를 상속받아서 재구현해야 함

    /**
     * Default constructor. 
     */
    public CommonFilter() {
        // TODO Auto-generated constructor stub
    	System.out.println("CommonFilter 객체 생성됨....");
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("CommonFilter 객체 소멸됨...");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, 
			ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 매개변수는 반드시 3개여야 함
		// request, response, chain
		
		System.out.println("CommonFilter 의 doFilter() run...");
		
		//request 매개변수를 이용하여 요청 값에 대한 필터링 담당
		HttpServletRequest hRequest = (HttpServletRequest)request;
		//post 방식으로 전송시에만, 요청값 한글 인코딩 처리 필요함
		if(hRequest.getMethod().equalsIgnoreCase("post")) {
			System.out.println("post 전송시 인코딩 처리됨...");
			request.setCharacterEncoding("utf-8");
		}

		CommonWrapper requestWrapper = 
				new CommonWrapper(hRequest);
				
		// pass the request along the filter chain
		//chain.doFilter(request, response);
		chain.doFilter(requestWrapper, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// 필터 초기화 처리용 메소드
		// 필터 등록시 init parameter 처리하는 역할을 담당함
		System.out.println("CommonFilter 의 init() run...");
	}

}
