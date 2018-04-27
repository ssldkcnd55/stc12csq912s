package board.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BoardFileDownServlet
 */
@WebServlet("/bfdown.ss")
public class BoardFileDownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardFileDownServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 게시글 첨부파일 다운로드 처리용 컨트롤러
		//request.setCharacterEncoding("utf-8");

		// 프로젝트 내에 파일이 저장된 폴더의 경로정보 얻어옴
		String readFolder = request.getSession()
				.getServletContext().getRealPath("/board_upload");

		String originalFileName = request.getParameter("ofile");
		String renameFileName = request.getParameter("rfile");

		// 클라이언트로 내보낼 출력 스트림 생성
		ServletOutputStream downOut = 
							response.getOutputStream();

		File downFile = new File(readFolder + "/" 
								+ renameFileName);

		response.setContentType("text/plain; charset=utf-8");
		// 한글 파일명 인코딩 처리함
		response.addHeader("Content-Disposition",
				"attachment; filename=\"" 
				+ new String(
				originalFileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
		response.setContentLength((int) downFile.length());

		BufferedInputStream bin = new BufferedInputStream(
				new FileInputStream(downFile));
		int read = -1;
		while ((read = bin.read()) != -1) {
			downOut.write(read);
			downOut.flush();
		}
		downOut.close();
		bin.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
