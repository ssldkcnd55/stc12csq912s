<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "notice.model.vo.Notice, member.model.vo.Member" %>
<%-- <% 
   Notice notice = (Notice)request.getAttribute("notice");
   Member loginUser = (Member)session.getAttribute("loginUser");
%> --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>noticeDetailView</title>
<script type="text/javascript">
   function movePage() {
      <%-- location.href = "/first/nupview?no=" + <%= notice.getNoticeNo() %>; --%>
      location.href = "/first/nupview?no=" + ${notice.noticeNo};
      
      return false;
   }
   
   function deleteNotice() {
      <%-- location.href = "/first/ndel?no=" + <%= notice.getNoticeNo() %>; --%>
      location.href = "/first/ndel?no=" + ${notice.noticeNo};
      
      return false;
   }
</script>
</head>
<body>
<c:import url="../../header.jsp"/>
<%-- <%@ include file="../../header.jsp" %> --%>
<hr style="clear:both;">
<br>
<%-- <h2 align="center"><%= notice.getNoticeNo() %>번 공지글 상세보기</h2> --%>
<h2 align="center">${notice.noticeNo}번 공지글 상세보기</h2>
<br>
   <table align="center" width="600">
      <tr>
         <th>제목</th>         
         <%-- <td><%= notice.getNoticeTitle() %></td>          --%>
         <td>${notice.noticeTitle }</td>         
      </tr>
      <tr>
         <th>작성자</th>         
         <%-- <td><%= notice.getNoticeWriter() %></td> --%>         
         <td>${notice.noticeWriter }</td>         
      </tr>
      <tr>
         <th>첨부 파일</th>
         <td>
            <%-- <% if(notice.getOriginalFilePath() != null) { %>
               <a href = "/first/fdown?ofile=<%= notice.getOriginalFilePath() %>&rfile=<%= notice.getRenameFilePath() %>">
               <%= notice.getOriginalFilePath() %>
               </a>
            <% } else { %>
               첨부 파일 없음
            <% } %> --%>
            <c:if test="${!empty notice.originalFilePath }">
               <a href = "/first/fdown?ofile=${ notice.originalFilePath}&rfile=${notice.renameFilePath}">
               ${ notice.originalFilePath}
               </a>
            </c:if>
            <c:if test="${empty notice.originalFilePath}">
               첨부 파일 없음
            </c:if>
         </td>
      </tr>
      <tr>
         <th>내 용</th>         
         <%-- <td><%= notice.getNoticeContent() %></td>       --%>   
         <td>${notice.noticeContent }<td>         
      </tr>
      <tr>
         <th colspan = "2">
      <%--    <% if(loginUser.getUserId().equals(notice.getNoticeWriter())) { %>
            <button onclick = "movePage();">수정페이지로 이동</button> &nbsp;      
            <button onclick = "deleteNotice();">글 삭제</button> &nbsp;
         <% } %> --%>
         <c:if test="${loginUser.userId == notice.noticeWriter }">
            <button onclick = "movePage();">수정페이지로 이동</button> &nbsp;   
            <button onclick = "deleteNotice();">글 삭제</button> &nbsp;
         </c:if>
            <button onclick = "history.go(-1); return false;">이전 페이지로 이동</button>            
         </th>
      </tr>
   </table>

<hr>
<c:import url="../../footer.html"/>
<%-- <%@ include file="../../footer.html" %> --%>
</body>
</html>