<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage = "./noticeError.jsp"%>
<%-- <%@ page import = "notice.model.vo.Notice, java.util.*, member.model.vo.Member" %>

 <% 
   List<Notice> list = (List<Notice>)request.getAttribute("noticeList");
   Member loginUser = (Member)session.getAttribute("loginUser");
%>  --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>noticeListView</title>
   <style type="text/css"></style>
   <script type="text/javascript">
      function moveWritePage() {
         //글쓰기 버튼을 클릭하면 notiveWriteForm.jsp 파일로 페이지 이동함
         location.href = "/first/views/notice/noticeWriteForm.jsp";         
      }
   </script>
</head>
<body>
<c:import url="../../header.jsp"/>
<%-- <%@ include file="../../header.jsp" %> --%>
<hr style="clear:both;">
<br>
<h2 align="center">공지글 전체 목록</h2>

<%-- <% if(loginUser.getUserId().equals("aaaa")) { %>
   <button id = "writeBtn" onclick="moveWritePage();">글쓰기</button>
<% } else { %>
   <button disabled>글쓰기</button>
<% } %> --%>

<c:if test="${loginUser.userId == 'admin' }">
   <button id = "writeBtn" onclick="moveWritePage();">글쓰기</button>
</c:if>
<c:if test="${loginUser.userId != 'admin' }">
   <button disabled>글쓰기</button>
</c:if>
<table align="center" width="650" cellspacing="0" border="1">
   <tr>
      <th>번호</th>
      <th>제목</th>
      <th>작성자</th>
      <th>날짜</th>
      <th>첨부파일</th>
   </tr>
   <%-- <% for( Notice n : list ) { %>
      <tr>
         <td align = "center"><%= n.getNoticeNo() %></td>
         <td align = "center">
         <% if(loginUser != null) { //로그인 된 경우 상세보기 연결%>
         <a href = "/first/ndetail?no=<%= n.getNoticeNo() %>" style = "text-decoration : hand"
         ><%= n.getNoticeTitle() %></a>         
         <% } else { //로그인 되지 않은 상태라면 제목만 보여줌.%>
            <%= n.getNoticeTitle() %>
         <% } %>
         </td>
         <td align = "center"><%= n.getNoticeWriter() %></td>
         <td align = "center"><%= n.getNoticeDate() %></td>
         <td align = "center">
         <% if(n.getOriginalFilePath() != null) { %>
            ◎
         <% } else { %>
            &nbsp;
         <% } %>
         </td>
      </tr>
   <% } %> --%>
   <c:forEach items="${noticeList }" var="noticelist">
      <tr>
         <td align = "center">${noticelist.noticeNo }</td>
         <td align = "center">
         <c:if test="${!empty loginUser }">
            <a href = "/first/ndetail?no=${noticelist.noticeNo }" style = "text-decoration : hand"
         >${noticelist.noticeTitle }</a>   
         </c:if>
         <c:if test="${empty loginUser }">
            ${noticelist.noticeTitle }
         </c:if>
         </td>
         <td align = "center">${noticelist.noticeWriter }</td>
         <td align = "center">${noticelist.noticeDate }</td>
         <td align = "center">
         <c:if test="${!empty noticelist.originalFilePath }">
         파일있음
         </c:if>
            &nbsp;
            </td>
         </tr>
   </c:forEach>
</table>
<c:import url="../../footer.html"/>
<%-- <%@ include file="../../footer.html" %> --%>
</body>
</html>