package listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class SessionCountListener
 *
 */
@WebListener
public class SessionCountListener 
implements HttpSessionListener, HttpSessionAttributeListener {

	//로그인한 사용자수 카운트용 필드 선언
	private static int loginCount;
	
    /**
     * Default constructor. 
     */
    public SessionCountListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0)  { 
         // 세션 객체가 만들어질 때 자동 작동되는 메소드임
    	System.out.println("session created...");
    	loginCount++;
    	System.out.println("현재 로그인 접속한 사용자수 : " + loginCount);
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
         // 세션 객체가 파기될 때 자동 작동되는 메소드임
    	System.out.println("session destroied....");
    	loginCount--;
    	System.out.println("1명 로그아웃함...로그인 접속한 사용자수 : " 
    					+ loginCount);
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent arg0)  { 
         // 세션 객체에 setAttribute() 될 때 자동 작동됨
    	System.out.println("session setAttribute() run...");
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent arg0)  { 
         // 세션 객체에 removeAttribute() 사용할 때 자동 작동
    	System.out.println("session removeAttribute() run...");
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent arg0)  { 
         // 세션 객체에 저장된 객체를 덮어쓰기할 때 자동 작동
    	System.out.println("session setAttribute() replaced...");
    }
	
}











