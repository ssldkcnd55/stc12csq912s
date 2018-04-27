package wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

//request 객체 값 가공처리용 공용 래퍼 클래스
public class CommonWrapper extends HttpServletRequestWrapper{
	//부모클래스에 기본생성자가 없음
	//매개변수 있는 생성자만 만들 수 있음
	public CommonWrapper(HttpServletRequest request) {
		super(request);
	}
	
	//request 객체의 값 가공처리에 getParameter() 메소드 오버라이딩함
	@Override
	public String getParameter(String name) {
		System.out.println("CommonWrapper 의 getParameter() run...");
		return super.getParameter(name);
	}
}
