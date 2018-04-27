package board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;

/**
 * Servlet implementation class BoardReplyUpdate
 */
@WebServlet("/breplyup")
public class BoardReplyUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardReplyUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 댓글 수정 처리용 컨트롤러
		//request.setCharacterEncoding("utf-8");
		
		String boardTitle = request.getParameter("btitle");
		String boardContent = request.getParameter("bcontent");
		int boardNum = Integer.parseInt(request.getParameter("bnum"));
		int  currentPage = Integer.parseInt(request.getParameter("page"));
		
		Board board = new Board(boardNum, boardTitle, 
								boardContent);
		
		int result = new BoardService().updateReply(board);
		
		response.setContentType("text/html; charset=utf-8");
		if(result > 0) {
			response.sendRedirect("/first/blist?page=" 
									+ currentPage);
		}else {
			RequestDispatcher view = request.getRequestDispatcher(
					"views/board/boardError.jsp");
			request.setAttribute("message", 
					boardNum + "번 댓글 수정 실패!");
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
