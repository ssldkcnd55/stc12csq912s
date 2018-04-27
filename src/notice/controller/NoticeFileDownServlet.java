package notice.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet implementation class NoticeFileDownServlet
 */
@WebServlet("/fdown")
public class NoticeFileDownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeFileDownServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//공지글 첨부 파일 다운 로드 처리용 컨트롤러
		//request.setCharacterEncoding("UTF-8");
		
		//웹 프로젝트 내에 파일이 저장된 폴더의 경로 정보 얻어오기
		String readFolder = request.getSession().getServletContext().getRealPath("/notice_upload");
		
		String originalFileName = request.getParameter("ofile");
		String renameFileName = request.getParameter("rfile");
		
		//클라이언트로 내보낼 출력 스트림 생성
		ServletOutputStream downOut = response.getOutputStream();
		
		File downFile = new File(readFolder + "/" + renameFileName);
		
		response.setContentType("text/plain; charset=UTF-8");
		//한글 파일명 인코딩 처리함
		response.addHeader("Content-Disposition", "attachment; filename=\"" + 
							new String(originalFileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
		response.setContentLength((int)downFile.length());
		
		//폴더에서 파일 데이터 읽어서, 클라이언트로 내보냄
		BufferedInputStream bin = new BufferedInputStream(new FileInputStream(downFile));
		
		int read = -1;
		while((read = bin.read()) != -1) {
			downOut.write(read);
			downOut.flush();
		}		
		downOut.close();
		bin.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
