<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "member.model.vo.Member" %>
<% 
	Member loginUser = (Member)session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>noticeWriteForm</title>
</head>
<body>
<%@ include file="../../header.jsp" %>
<hr style="clear:both;">
<br>
<h2 align="center">공지 글쓰기 페이지</h2>
<br>
<form action="/first/nwrite" method="post" enctype="multipart/form-data">
	<table align="center" width="600">
		<tr>
			<th>제목</th>			
			<td><input type="text" name="title" size="60"></td>			
		</tr>
		<tr>
			<th>작성자</th>			
			<td><input type="text" name="writer" value="<%= loginUser.getUserId() %>" readonly></td>			
		</tr>
		<tr>
			<th>첨부 파일</th>
			<td><input type="file" name="upfile"></td>
		</tr>
		<tr>
			<th>내 용</th>			
			<td><textarea rows="5" cols="50" name="content"></textarea></td>			
		</tr>
		<tr>
			<th colspan = "2">			
			<input type = "submit" value="등록하기"> &nbsp;
				<input type = "reset" value="작성취소"> &nbsp;
				<input type = "button" value="이전 페이지로 이동"
				onclick = "history.go(-1); return false;">
			</th>
		</tr>
	</table>
</form>
<hr>
<%@ include file="../../footer.html" %>
</body>
</html>