<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%-- <%
	String message = (String)request.getAttribute("message");
%> --%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 서비스 에러</title>
</head>

<body>
	<c:import url="../../header.jsp"/>
	<%-- <%@ include file="../../header.jsp" %> --%>
	<hr style="clear:both;">
	<h2>에러 발생 : ${requestScope.message }</h2>
	<a href="/first/index.jsp">시작페이지로 이동</a>
	<%-- <%@ include file= "../../footer.html" %> --%>
	<c:import url="../../footer.jsp"/>
</body>
</html>