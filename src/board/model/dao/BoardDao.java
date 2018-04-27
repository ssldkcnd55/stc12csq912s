package board.model.dao;

import java.sql.*;
import java.util.ArrayList;

import board.model.vo.Board;

import static common.JDBCTemplate.*;

public class BoardDao {
	public BoardDao() {}
	
	public ArrayList<Board> selectTop3(Connection con){
		ArrayList<Board> list = new ArrayList<Board>();
		Statement stmt = null;
		ResultSet rset = null;

		String query = "select * from " 
				+ "(select rownum rnum, board_num, " 
				+ "board_title, board_writer, board_content, "
				+ "board_original_filename, board_rename_filename, " 
				+ "board_date, board_readcount, board_level, " 
				+ "board_ref, board_reply_ref, board_reply_seq " 
				+ "from (select * from board "
				+ "where board_level = 0 " 
				+ "order by board_readcount desc)) " 
				+ "where rnum >= 1 and rnum <= 3";		

		try {
			stmt = con.createStatement();		

			rset = stmt.executeQuery(query);
			
			while (rset.next()) {
				Board b = new Board();
				b.setBoardNum(rset.getInt("board_num"));
				b.setBoardTitle(rset.getString("board_title"));
				b.setBoardWriter(rset.getString("board_writer"));
				b.setBoardContent(rset.getString("board_content"));
				b.setBoardDate(rset.getDate("board_date"));
				b.setBoardReadCount(rset.getInt("board_readcount"));
				b.setBoardOriginalFileName(rset.getString("board_original_filename"));
				b.setBoardRenameFileName(rset.getString("board_rename_filename"));
				b.setBoardLevel(rset.getInt("board_level"));
				b.setBoardRef(rset.getInt("board_ref"));
				b.setBoardReplyRef(rset.getInt("board_reply_ref"));
				b.setBoardReplySeq(rset.getInt("board_reply_seq"));
				
				list.add(b);
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}

		return list;
	}

	public int getListCount(Connection con) {
		int listCount = 0;
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) from board";
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		
		return listCount;
	}

	public ArrayList<Board> selectList(Connection con, 
			int currentPage, int limit) {
		ArrayList<Board> list = new ArrayList<Board>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from "
				+ "(select rownum rnum, board_num, "
				+ "board_title, board_writer, board_content, "
				+ "board_original_filename, board_rename_filename, "
				+ "board_date, board_readcount, board_level, "
				+ "board_ref, board_reply_ref, board_reply_seq "
				+ "from (select * from board order by "
				+ "board_ref desc, board_reply_ref desc, "
				+ "board_level asc, board_reply_seq asc)) "
				+ "where rnum >= ? and rnum <= ?";
		
		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board b = new Board();
				b.setBoardNum(rset.getInt("board_num"));
				b.setBoardTitle(rset.getString("board_title"));
				b.setBoardWriter(rset.getString("board_writer"));
				b.setBoardContent(rset.getString("board_content"));
				b.setBoardDate(rset.getDate("board_date"));
				b.setBoardReadCount(rset.getInt("board_readcount"));
				b.setBoardOriginalFileName(rset.getString("board_original_filename"));
				b.setBoardRenameFileName(rset.getString("board_rename_filename"));
				b.setBoardLevel(rset.getInt("board_level"));
				b.setBoardRef(rset.getInt("board_ref"));
				b.setBoardReplyRef(rset.getInt("board_reply_ref"));
				b.setBoardReplySeq(rset.getInt("board_reply_seq"));
				
				list.add(b);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int insertBoard(Connection con, Board b) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "insert into board values " 
				+ "((select max(board_num) + 1 from board), "
				+ "?, ?, ?, ?, ?, sysdate, 0, " 
				+ "(select max(board_num) + 1 from board), NULL, " 
				+ "default, default)";

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardWriter());
			pstmt.setString(3, b.getBoardContent());
			pstmt.setString(4, b.getBoardOriginalFileName());
			pstmt.setString(5, b.getBoardRenameFileName());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int addReadCount(Connection con, int boardNum) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "update board " 
			+ "set board_readcount = board_readcount + 1 " 
			+ "where board_num = ?";

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, boardNum);

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public Board selectBoard(Connection con, int boardNum) {
		Board board = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from board "
				+ "where board_num = ?";

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, boardNum);

			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				board = new Board();

				board.setBoardNum(boardNum);
				board.setBoardTitle(rset.getString("board_title"));
				board.setBoardWriter(rset.getString("board_writer"));
				board.setBoardContent(rset.getString("board_content"));
				board.setBoardDate(rset.getDate("board_date"));
				board.setBoardOriginalFileName(rset.getString("board_original_filename"));
				board.setBoardRenameFileName(rset.getString("board_rename_filename"));
				board.setBoardReadCount(rset.getInt("board_readcount"));
				board.setBoardRef(rset.getInt("board_ref"));
				board.setBoardReplyRef(rset.getInt("board_reply_ref"));
				board.setBoardLevel(rset.getInt("board_level"));
				board.setBoardReplySeq(rset.getInt("board_reply_seq"));
			} 
			
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			close(rset);
			close(pstmt);
		}

		return board;
	}

	public int updateReplySeq(Connection con, Board replyBoard) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update board "
				+ "set board_reply_seq = board_reply_seq + 1 "
				+ "where board_ref = ? "
				+ "and board_level = ? "
				+ "and board_reply_ref = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, replyBoard.getBoardRef());
			pstmt.setInt(2, replyBoard.getBoardLevel());
			pstmt.setInt(3, replyBoard.getBoardReplyRef());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertReply(Connection con, Board replyBoard) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = null;
		
		//원글의 댓글일 때
		if(replyBoard.getBoardLevel() == 1) {
			query = "insert into board values ("
					+ "(select max(board_num) + 1 from board), "
					+ "?, ?, ?, NULL, NULL, sysdate, ?, ?, "
					+ "(select max(board_num) + 1 from board), "
					+ "1, default)";
		}
		
		//댓글의 댓글일 때
		if(replyBoard.getBoardLevel() == 2) {
			query = "insert into board values ("
					+ "(select max(board_num) + 1 from board), "
					+ "?, ?, ?, NULL, NULL, sysdate, ?, ?, ?,"
					+ "1, default)";
		}
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, replyBoard.getBoardTitle());
			pstmt.setString(2, replyBoard.getBoardWriter());
			pstmt.setString(3, replyBoard.getBoardContent());
			pstmt.setInt(4, replyBoard.getBoardLevel());
			pstmt.setInt(5, replyBoard.getBoardRef());
			
			if(replyBoard.getBoardLevel() == 2)
				pstmt.setInt(6, replyBoard.getBoardReplyRef());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteBoard(Connection con, int boardNum) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "delete from board "
				+ "where board_num = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, boardNum);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateReply(Connection con, Board board) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update board "
				+ "set board_title = ?, "
				+ "board_content = ? "
				+ "where board_num = ?";				
				
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, board.getBoardTitle());
			pstmt.setString(2, board.getBoardContent());
			pstmt.setInt(3, board.getBoardNum());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		
		return result;
	}
	
	public int updateBoard(Connection con, Board board) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update board "
				+ "set board_title = ?, "
				+ "board_content = ?, "
				+ "BOARD_ORIGINAL_FILENAME = ?, "
				+ "BOARD_RENAME_FILENAME = ? "
				+ "where board_num = ?";				
				
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, board.getBoardTitle());
			pstmt.setString(2, board.getBoardContent());
			pstmt.setString(3, board.getBoardOriginalFileName());
			pstmt.setString(4, board.getBoardRenameFileName());
			pstmt.setInt(5, board.getBoardNum());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		
		return result;
	}
}














