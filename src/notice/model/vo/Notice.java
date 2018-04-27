package notice.model.vo;

import java.sql.*;

public class Notice implements java.io.Serializable {
	private static final long serialVersionUID = 1000L;
	
	private int noticeNo;
	private String noticeTitle;
	private Date noticeDate;
	private String noticeContent;
	private String noticeWriter;
	private String originalFilePath;
	private String renameFilePath;
	
	public Notice() {
		
	}

	public Notice(int noticeNo, String noticeTitle, Date noticeDate, String noticeContent, String noticeWriter,
			String originalFilePath, String renameFilePath) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeDate = noticeDate;
		this.noticeContent = noticeContent;
		this.noticeWriter = noticeWriter;
		this.originalFilePath = originalFilePath;
		this.renameFilePath = renameFilePath;
	}

	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public Date getNoticeDate() {
		return noticeDate;
	}

	public void setNoticeDate(Date noticeDate) {
		this.noticeDate = noticeDate;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public String getNoticeWriter() {
		return noticeWriter;
	}

	public void setNoticeWriter(String noticeWriter) {
		this.noticeWriter = noticeWriter;
	}

	public String getOriginalFilePath() {
		return originalFilePath;
	}

	public void setOriginalFilePath(String originalFilePath) {
		this.originalFilePath = originalFilePath;
	}

	public String getRenameFilePath() {
		return renameFilePath;
	}

	public void setRenameFilePath(String renameFilePath) {
		this.renameFilePath = renameFilePath;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		
		return this.noticeNo +", "+ this.noticeTitle +", "+
				this.noticeDate +", "+ this.noticeContent +", "+ 
				this.noticeWriter +", "+ this.originalFilePath +", "+ 
				this.renameFilePath;
	}
	
}


