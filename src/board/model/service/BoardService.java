package board.model.service;

import static common.JDBCTemplate.*;
import java.sql.*;
import java.util.ArrayList;

import board.model.dao.BoardDao;
import board.model.vo.Board;

public class BoardService {
	public BoardService() {}
	
	public ArrayList<Board> selectTop3(){
		Connection con = getConnection();
		ArrayList<Board> list = 
				new BoardDao().selectTop3(con);
		close(con);
		return list;
	}

	public int getListCount() {
		Connection con = getConnection();
		int listCount = new BoardDao().getListCount(con);
		close(con);
		return listCount;
	}

	public ArrayList<Board> selectList(int currentPage, int limit) {
		Connection con = getConnection();
		ArrayList<Board> list = 
			new BoardDao().selectList(con, currentPage, limit);
		close(con);
		return list;
	}

	public int insertBoard(Board b) {
		Connection con = getConnection();
		int result = new BoardDao().insertBoard(con, b);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}

	public void addReadCount(int boardNum) {
		Connection con = getConnection();
		int result = new BoardDao().addReadCount(con, boardNum);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return;
	}

	public Board selectBoard(int boardNum) {
		Connection con = getConnection();
		Board b = new BoardDao().selectBoard(con, boardNum);
		close(con);
		return b;
	}

	public void updateReplySeq(Board replyBoard) {
		Connection con = getConnection();
		int result = new BoardDao().updateReplySeq(con, replyBoard);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return;
	}

	public int insertReply(Board replyBoard) {
		Connection con = getConnection();
		int result = new BoardDao().insertReply(con, replyBoard);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}

	public int deleteBoard(int boardNum) {
		Connection con = getConnection();
		int result = new BoardDao().deleteBoard(con, boardNum);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}

	public int updateReply(Board board) {
		Connection con = getConnection();
		int result = new BoardDao().updateReply(con, board);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}
	
	public int updateBoard(Board board) {
		// 원글 수정 처리용
		Connection con = getConnection();
		int result = new BoardDao().updateBoard(con, board);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}
	
}








