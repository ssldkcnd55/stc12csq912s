package notice.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import board.model.vo.Board;
import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeNewWrite3Servlet
 */
@WebServlet("/ntop3")
public class NoticeNewWrite3Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeNewWrite3Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 게시글 조회수 탑 3 조회 Ajax 요청 처리용 컨트롤러
		
		List<Notice> list = new NoticeService().selectTop3List();
		System.out.println("list : " + list);
		
		// 전송할 최종 json 객체
		JSONObject json = new JSONObject();
		JSONArray jarr = new JSONArray();

		for (Notice n : list) {
			// 한 사람의 정보를 저장할 json 객체
			JSONObject job = new JSONObject();
			job.put("no", n.getNoticeNo());			
			// json에서 한글 깨짐을 막으려면, java.net.URLEncoder 클래스의 encode() 메소드로 인코딩 처리
			job.put("title", URLEncoder.encode(n.getNoticeTitle(), "UTF-8"));
			job.put("writer", n.getNoticeWriter());
			job.put("date", n.getNoticeDate().toString());			
			
			jarr.add(job);
		}

		json.put("list", jarr);
		System.out.println("json : " + json.toJSONString());
		
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.write(json.toJSONString());
		out.flush();
		out.close();
	}

}
