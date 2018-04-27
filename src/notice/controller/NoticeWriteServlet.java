package notice.controller;

import java.io.*;
import java.text.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeWriteServlet
 */
@WebServlet("/nwrite.ss")
public class NoticeWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파일 업로드 기능이 추가된 공지글 등록 처리용 컨트롤러
		//1. 전송온 값에 한글이 있을 경우 인코딩 처리함
		//request.setCharacterEncoding("UTF-8");
		
		//업로드할 파일의 용량 제한들 10M로 제한
		int maxSize = 1024 * 1024 * 10;
		
		//enctype= "multipart/form-data" 로 전송 됬는지 확인
		RequestDispatcher view = null;
			if(!ServletFileUpload.isMultipartContent(request)) {
				//enctype 설정이 되지 않았다면				
				view = request.getRequestDispatcher("views/notice/noticeError.jsp");
				request.setAttribute("message", "form 태그에 enctype 속성이 설정되지 않았습니다.");
				view.forward(request, response);				
			}
			
		//해당 웹 컨테이너(was : 톰캣) 에서 구동중인 웹 애플리케이션의
		//루트 경로(content directory) 알아냄
		String root = request.getSession().getServletContext().getRealPath("/");
		System.out.println("root : "+root);
		//업로드 되는 파일이 저장될 폴더명과 루트 경로 연결
		String savePath = root + "notice_upload";
		System.out.println("savePath : "+savePath);		
		//web/notice_upload 로 출력됨.
		//cos.jar 라이브러리를 사용할 ㅕㅇ우, MultipartReauest 객체 생성
		//객체 생성과 동시에 자동 파일 업로드됨
		//request 객체는 MultipartReauest 객체로 변환됨
		MultipartRequest mrequest = new MultipartRequest(
				request, savePath, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		
		/*//2. 전송온 값 꺼내서 변수 또는 객체에 저장하기
		
		Notice notice = new Notice();
		notice.setNoticeTitle(request.getParameter("title"));
		notice.setNoticeWriter(request.getParameter("writer"));
		notice.setNoticeContent(request.getParameter("content"));
		System.out.println(notice.getNoticeWriter().length());*/
		
		Notice notice = new Notice();
		notice.setNoticeTitle(mrequest.getParameter("title"));
		notice.setNoticeWriter(mrequest.getParameter("writer"));
		notice.setNoticeContent(mrequest.getParameter("content"));
		notice.setOriginalFilePath(mrequest.getFilesystemName("upfile"));
		//notice.setRenameFilePath(mrequest.getFilesystemName("upfile"));
		
		//만약, 클라이언트간 파일명이 같을 경우
		//저장폴더에 기록되는 파일명을 `년월일시분초,확장자` 형식으로 이름을 바꾸어 기록 저장
		String originFileName = notice.getOriginalFilePath();
		if(originFileName != null) {
			//첨부된 파일이 있을 경우, 폴더에 기록된 해당 파일의 이름을 바꾸도록 처리함
			//새로운 파일명 만들기 : "년월일시분초"
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String renameFileName = sdf.format(new java.sql.Date(System.currentTimeMillis()))
			+ "." + originFileName.substring(originFileName.lastIndexOf(".") + 1);
			
			//파일명 바꾸려면 File 객체의 renameTo() 사용함
			File originFile = new File(savePath + "\\" + originFileName);
			File renameFile = new File(savePath + "\\" + renameFileName);
			
			//파일 이름 바꾸기 실행함
			//이름바꾸기 실패할 경우에는 직접 바꾸기 함
			//직접 바꾸기는 원본 파일에 대한 복사본 파일을 만들고 원본은 삭제.
			if(!originFile.renameTo(renameFile)) {
				//파일 이름 바꾸기 실패했다면
				int read = -1;
				byte[] buf = new byte[1024];
				//한번에 읽을 배열 크기 지정
				//원본을 읽기 위한 파일스트림 생성
				FileInputStream fin = new FileInputStream(originFile);
				//읽은 내용 기록할 본사본 파일 출력용 스트림 생성
				FileOutputStream fout = new FileOutputStream(renameFile);
				
				//원본 읽어서 복사본에 기록 처리
				while((read = fin.read(buf, 0, buf.length)) != -1) {
					fout.write(buf, 0, read);
				}
				
				//스트림 반납
				fin.close();
				fout.close();
				originFile.delete();//원본 삭제.
								
			}//rename if			
			notice.setRenameFilePath(renameFileName);
		}
		
		//3. 서비스 클래스 메소드로 값 전달하고, 결과 받기
		int result = new NoticeService().insertNotice(notice);
		
		//4. 받은 결과를 가지고 성공/실패에 대한 뷰를 선택해서 내보냄
		response.setContentType("text/html; charset=UTF-8");
		if(result > 0) {
			response.sendRedirect("/first/nlist");
		} else {
			view = request.getRequestDispatcher("views/notice/noticeError.jsp");
			request.setAttribute("message", "게시글 등록 실패");
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
