<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="boardError.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- <%@ page import="member.model.vo.Member, board.model.vo.Board, java.util.*, java.sql.Date" %>
<%	
	Board board = (Board)request.getAttribute("board");	
	int currentPage = ((Integer)request.getAttribute("currentPage")).intValue();	

	Member loginUser = (Member)session.getAttribute("loginUser");
%> --%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardDetailView</title>
</head>
<body>
<c:import url="../../header.jsp"></c:import>
<%-- <%@ include file="../../header.jsp" %> --%>
<hr style="clear:both;">
<br>
<table align="center" cellpadding="10" cellspacing="0" border="1" width="500"> 
    <tr align="center" valign="middle">  
          <th colspan="2">
          <%-- <%= board.getBoardNum() %> --%>${board.boardNum } 번글 상세보기</th> 
    </tr>      
    <tr><td height="15" width="100">제 목</td>          
        <td><%-- <%= board.getBoardTitle() %> --%>${board.boardTitle }</td> 
    </tr>     
    <tr><td>내 용</td> 
        <td><%-- <%= board.getBoardContent() %> --%>${board.boardContent }</td>         
    </tr> 
    <tr><td>첨부파일</td> 
        <td><%-- <% if(board.getBoardOriginalFileName() == null){ %> --%>
        	<c:if test="${ empty board.boardOriginalFileName  }">
        		첨부파일 없음 
        	<%-- <% }else{ %> --%>
        	</c:if>
        	<c:if test="${ !empty board.boardOriginalFileName  }">
        		<%-- <a href="/first/bfdown?ofile=<%= board.getBoardOriginalFileName() %>&rfile=<%= board.getBoardRenameFileName() %>">
        		<%= board.getBoardOriginalFileName() %></a> --%>
        		<c:url var="fdown" value="/first/bfdown">
        			<c:param name="ofile" value="${board.boardOriginalFileName }"></c:param>
        			<c:param name="rfile" value="${board.boardRenameFileName }"></c:param>
        		</c:url>
        		<a href="${fdown }">${board.boardOriginalFileName }</a>
        	<%-- <% } %>  --%>
        	</c:if>
        </td> 
    </tr>
    <tr align="center" valign="middle"> 
        <td colspan="2"> 
         <%-- <% if(loginUser != null){ %> --%>
         <c:if test="${ !empty sessionScope.loginUser }">
            <%-- <a href="/first/views/board/boardReplyForm.jsp?bnum=<%= board.getBoardNum() %>&page=<%= currentPage %>"> 
            
            [댓글달기] </a> &nbsp;&nbsp;  --%>
            <c:url var="replyForm" value="views/board/boardReplyForm.jsp">
        		<c:param name="bnum" value="${board.boardNum }"></c:param>
        		<c:param name="page" value="${currentPage }"></c:param>
        	</c:url>
        	<a href="${replyForm }">[댓글달기]</a>&nbsp;&nbsp;
         <%-- <% if(loginUser.getUserId().equals(board.getBoardWriter()) == true){ %> --%>
	         <c:if test="${sessionScope.loginUser.userId eq board.boardWriter }">
	            <%-- <a href="/first/bupview?bnum=<%= board.getBoardNum() %>&page=<%= currentPage %>"> 
	            [수정페이지로 이동] </a> &nbsp;&nbsp; 
	            <a href="/first/bdelete?bnum=<%= board.getBoardNum() %>"> 
	            [글삭제] </a> &nbsp;&nbsp;  --%>
		        <c:url var="bupview" value="bupview">
	        		<c:param name="bnum" value="${board.boardNum }"></c:param>
	        		<c:param name="page" value="${currentPage }"></c:param>
	        	</c:url>
	        	<a href="${bupview }">[수정페이지로 이동]</a>&nbsp;&nbsp;
	        	<c:url var="bdelete" value="bdelete">
	        		<c:param name="bnum" value="${board.boardNum }"></c:param>
	        	</c:url>
	        	<a href="${bdelete }">[글삭제]</a>&nbsp;&nbsp;
	         <%-- <% }} %> --%>
	         </c:if>
         </c:if>
            <%-- <a href="/first/blist?page=<%= currentPage %>">[목록]</a> --%>
            <c:url var="blist" value="blist">
	        	<c:param name="page" value="${currentPage }"></c:param>
	        </c:url>
	        <a href="${blist }">[목록]</a>
        </td> 
    </tr> 
</table>
<br>
<%@ include file="../../footer.html" %>
</body>
</html>





