<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="./boardError.jsp" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%-- <%@ page import="member.model.vo.Member, board.model.vo.Board, java.util.ArrayList, java.sql.Date" %> 
<%
	ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
	int listCount = ((Integer)request.getAttribute("listCount")).intValue();
	int startPage = ((Integer)request.getAttribute("startPage")).intValue();
	int endPage = ((Integer)request.getAttribute("endPage")).intValue();
	int maxPage = ((Integer)request.getAttribute("maxPage")).intValue();			
	int currentPage = ((Integer)request.getAttribute("currentPage")).intValue();
	
	Member loginUser = (Member)session.getAttribute("loginUser");
%>  --%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardListView</title>
<script type="text/javascript">
	function showWriteForm(){
		location.href = "views/board/boardWriteForm.jsp";
	}
</script>
</head>
<body>
<c:import url="../../header.jsp" />
<%-- <%@ include file="../../header.jsp" %> --%>
<hr style="clear:both;">
<h2 align="center">게시글 목록</h2>
<h4 align="center">총 게시글 갯수 : ${listCount}<%-- <%= listCount %> --%></h4>
<%-- <% if(loginUser != null){ %> --%>
<c:if test="${ !empty loginUser }">
	<div style="align:center; text-align:center;">
	<button onclick="showWriteForm();">글쓰기</button>
	</div>
</c:if>
<%-- <% } %> --%>
<br>
<table align="center" border="1" cellspacing="0" width="700">
<tr><th>번호</th><th>제목</th><th>작성자</th><th>날짜</th>
<th>조회수</th><th>첨부파일</th></tr>
<%-- <% for(Board b : list){ %> --%>
<c:forEach var="b" items="${list }">
<tr>
<td align="center"><%-- <%= b.getBoardNum() %> --%>${b.boardNum }</td>
<td>
<!-- 댓글일때는 제목을 들여쓰기함 -->
<%-- <% if(b.getBoardLevel() == 1){ //원글의 댓글일 때 %>
&nbsp; &nbsp; ▶
<% }else if(b.getBoardLevel() == 2){ //댓글의 댓글일 때 %>
&nbsp; &nbsp; &nbsp; &nbsp; ▶▶
<% } %> --%>
<c:if test="${b.boardLevel eq 1 }">
	&nbsp; &nbsp; ▶
</c:if>
<c:if test="${b.boardLevel eq 2 }">
	&nbsp; &nbsp; &nbsp; &nbsp; ▶▶
</c:if>
<!-- 로그인한 상태일 때만 상세보기 링크 처리함 -->
<%-- <% if(loginUser != null){ %>
	<a href="/first/bdetail?bnum=<%= b.getBoardNum() %>&page=<%= currentPage %>">
	<%= b.getBoardTitle() %></a>
<% }else{ %>
	<%= b.getBoardTitle() %>
<% } %> --%>
<c:if test="${ !empty sessionScope.loginUser }">
	<c:url var="bdetail" value="bdetail">
		<c:param name="bnum" value="${b.boardNum }"/>
		<c:param name="page" value="${currentPage }"/>
	</c:url>
	<a href="${bdetail }">${b.boardTitle }</a>
</c:if>
<c:if test="${ empty sessionScope.loginUser }">
	${b.boardTitle }
</c:if>
</td>
<td align="center"><%-- <%= b.getBoardWriter() %> --%>${b.boardWriter }</td>
<td align="center"><%-- <%= b.getBoardDate() %> --%>${b.boardDate }</td>
<td align="center"><%-- <%= b.getBoardReadCount() %> --%>${b.boardReadCount }</td>
<td align="center">
<%-- <% if(b.getBoardOriginalFileName() != null){ //첨부파일이 있다면 %>
	◎
<% }else{ %>
	&nbsp;
<% } %> --%>
<c:if test="${ !empty b.boardOriginalFileName }">
	◎
</c:if>
<c:if test="${ empty b.boardOriginalFileName }">
	&nbsp;
</c:if>
</td>
</tr>
<%-- <% }  //for closed %> --%>
</c:forEach>
</table>
<br>
<!-- 페이징 처리 -->
<div style="text-align:center;">
<%-- <% if(currentPage <= 1){ %>
	[맨처음]&nbsp;
<% }else{ %>
	<a href="/first/blist?page=1">[맨처음]</a>
<% } %> --%>
<c:if test="${currentPage <= 1 }">
	[맨처음]&nbsp;
</c:if>
<c:if test="${ currentPage > 1 }">
	<c:url var="blist" value="blist">
		<c:param name="page" value="1"/>
	</c:url>
	<a href="blist">[맨처음]</a>
</c:if>
<%-- <% if((currentPage - 10) < startPage 
		&& (currentPage - 10) > 1){ %>
	<a href="/first/blist?page=<%= startPage - 10 %>">[prev]</a>	
<% }else{ %>
	[prev]&nbsp;
<% } %> --%>
<c:if test="${(currentPage -10) < startPage and (currentPage - 10) > 1 }">
	<c:url var="first" value="blist">
		<c:param name="page" value="${startPage - 10 }"/>
	</c:url>
	<a href="${first }">[prev]</a>
</c:if>
<c:if test="${!((currentPage -10) < startPage and (currentPage - 10) < 1 )}">
	[prev]&nbsp;
</c:if>
<!-- 현재 페이지가 포함된 그룹의 페이지 숫자 출력 -->
<%-- <% for(int p = startPage; p <= endPage; p++){ 
		if(p == currentPage){
%>
	<font color="red" size="4"><b>[<%= p %>]</b></font>
<% }else{ %>
	<a href="/first/blist?page=<%= p %>"><%= p %></a>
<% }} %> --%>
<c:forEach var="p" begin="${startPage }" end="${endPage}" >
	<c:if test="${p eq currentPage }">
		<font color="red" size="4"><b>[${p }]</b></font>
	</c:if>
	<c:if test="${p ne currentPage }">
		<c:url var="page" value="blist">
			<c:param name="page" value="${p }"/>
		</c:url>
		<a href="page">${p }</a>
	</c:if>
</c:forEach>
<%-- <% if((currentPage + 10) > endPage 
		&& (currentPage + 10) < maxPage){ %>
	<a href="/first/blist?page=<%= endPage + 10 %>">[next]</a>	
<% }else{ %>
	[next]&nbsp;
<% } %> --%>
<c:if test="${(currentPage + 10) > endPage and (currentPage + 10) < maxPage }">
	<c:url var="next" value="blist">
		<c:param name="page" value="${endPage +10 }"/>
	</c:url>
	<a href="${next }">[next]</a>
</c:if>
<c:if test="${!((currentPage + 10) > endPage and (currentPage + 10) < maxPage) }">
	[next]&nbsp;
</c:if>

<%-- <% if(currentPage >= maxPage){ %>
	[맨끝]&nbsp;
<% }else{ %>
	<a href="/first/blist?page=<%= maxPage %>">[맨끝]</a>
<% } %> --%>
<c:if test="${currentPage >= maxPage }">
	[맨끝]&nbsp;
</c:if>
<c:if test="${currentPage < maxPage }">
	<c:url var="last" value="blist">
		<c:param name="page" value="${maxPage }"/>
	</c:url>
	<a href="${last }">[맨끝]</a>
</c:if>
</div>
<hr>
<%@ include file="../../footer.html" %>
</body>
</html>








