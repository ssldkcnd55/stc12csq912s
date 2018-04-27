<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardError</title>
</head>
<body>
<% if(exception != null){ //jsp 파일에서 예외 발생시 %>
에러 발생 : <%= exception.getMessage() %>
<% }else{ //서블릿에서 전달된 에러메세지 처리%>
에러 발생 : <%= (String)request.getAttribute("message") %>
<% } %>
</body>
</html>