<%-- <%@page import="java.util.ArrayList"%> --%>
<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, member.model.vo.Member"%> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%-- <%
	ArrayList<Member> list = 
		(ArrayList<Member>)request.getAttribute("list");
%> --%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 전체 조회</title>
	<style type="text/css">
	
		table, button {
		
			font-size: 11pt;
		
		}
	
	</style>
</head>
<body>
	<h2 align="center">회원 전체 관리 페이지</h2>
	<br>
	<table align="center" cellspacing="0" border="1">
		<tr>
			<th>순번</th>
			<th>아이디</th>
			<th>이름</th>
			<th>나이</th>
			<th>성별</th>
			<th>전화번호</th>
			<th>이메일</th>
			<th>취미</th>
			<th>특이사항</th>
			<th>가입날짜</th>
			<th>마지막 정보 수정 날짜</th>
			<th>로그인 권한 제한</th>
			<th>강제 탈퇴 처리</th>		
		</tr>
		
		<c:forEach var="list" items="${ list}" varStatus="status">
		<%-- <% for(int i = 0; i < list.size();i++) { %> --%>
			<tr>
				<td>${status.count }</td>
				<td>${list.userId }</td>
				<td><%-- <%= list.get(i).getUserName() %> --%>${list.userName }</td>
				<td><%-- <%= list.get(i).getAge() %> --%>${list.age }</td>
				<td><%-- <%= list.get(i).getGender() %> --%>${list.gender }</td>
				<td><%-- <%= list.get(i).getPhone() %> --%>${list.phone }</td>
				<td><%-- <%= list.get(i).getEmail() %> --%>${list.email }</td>
				<td><%-- <%= list.get(i).getHobby() %> --%>${list.hobby }</td>
				<td><%-- <%= list.get(i).getEtc() %> --%>${list.etc }</td>
				<td><%-- <%= list.get(i).getEnroll() %> --%>${list.enroll }</td>
				<td><%-- <%= list.get(i).getLastModified() %> --%>${list.lastModified }</td>
				<td><button>로그인 제한</button> &nbsp;
					<button>로그인 허용</button></td>
				<td><button>강제 탈퇴 처리</button></td>
			</tr>
		<%-- <% } %> --%>
		</c:forEach>
	</table>
</body>
</html>