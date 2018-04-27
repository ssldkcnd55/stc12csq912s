<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ page import="member.model.vo.Member" %>    
<%
	int boardNum = Integer.parseInt(request.getParameter("bnum"));
	int currentPage = Integer.parseInt(request.getParameter("page"));

	Member loginUser = (Member)session.getAttribute("loginUser");
%>     --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardReplyForm</title>
</head>
<body>
<c:import url="../../header.jsp" />
<%-- <%@ include file="../../header.jsp" %> --%>
<hr style="clear:both;">
<h2 align="center"><%-- <%= boardNum %> --%>${boardNum }번 글의 댓글달기</h2>
<br>
<form action="/first/breply" method="post">
<input type="hidden" name="bnum" value="${boardNum }">
<input type="hidden" name="page" value="${currentPage }">
<table align="center">
<tr><th>제 목</th><td><input type="text" name="btitle"></td></tr>
<tr><th>작성자</th>
<td><input type="text" name="bwriter" readonly value="${loginUser.userId }"></td></tr>
<tr><th>내 용</th>
<td><textarea rows="5" cols="50" name="bcontent"></textarea></td></tr>
<tr><th colspan="2">
<input type="submit" value="댓글등록"> &nbsp;
<a href="javascript:history.go(-1);">이전 페이지로 이동</a>
</th></tr>
</table>
</form>
<br>
<h4 align="center">
<%-- <a href="/first/blist?page=<%= currentPage %>">목록으로 이동</a> --%>
<c:url var="page" value="blist">
	<c:param name="page" value="${currentPage }"/>
</c:url>
<a href="${page }">목록으로 이동</a>
</h4>
<br>
<hr>
<%@ include file="../../footer.html" %>
</body>
</html>











