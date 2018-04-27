<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="member.model.vo.Member"%> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%-- <%
	Member member = (Member)request.getAttribute("member");
	
	//취미 처리
	String[] hobbies = member.getHobby().split(",");
	String[] checked = new String[9];
	for(String s : hobbies) {
		switch(s) {		
		case "game": checked[0] = "checked"; break;
		case "reading": checked[1] = "checked"; break;
		case "music": checked[2] = "checked"; break;
		case "climb": checked[3] = "checked"; break;
		case "sport": checked[4] = "checked"; break;
		case "movie": checked[5] = "checked"; break;
		case "travel": checked[6] = "checked"; break;
		case "cook": checked[7] = "checked"; break;
		case "etc": checked[8] = "checked"; break;
		}
	}
%> --%>
<c:if test="">

</c:if>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MemberDetailView</title>
<script type="text/javascript" src="/first/js/jquery-3.3.1.min.js"></script>

<script type="text/javascript">

$(function(){
	 
	   $('input[type=password]').blur(function(){		   
		   console.log("포커스 벗어남.");
		   var pwd1 = $("#upwd").val();
		   var pwd2 = $("#upwd2").val();
		
		if(pwd1 == pwd2) {			
			$("#confirm").css("display","true");
			$("#confirm").val("일치");		
		} else {
			$("#confirm").css("display","block");
			$("#confirm").val("불일치");			
		}
		   
	   }); 	   
});  

</script>
</head>
<body>

	<h2 align="center">내 정보 보기 페이지</h2>

	<form action="/first/mupdate" method="post">
		<!-- 상대경로 : ../../minsert -->
		<table width="650" align="center">
		
			<tr height="40">
				<th width="150">아이디</th>
				<td><input type="text" name="userid"
					value="${requestScope.member.userId }" readonly></td>
			</tr>
			
			<tr height="40">
				<th width="150">이름</th>
				<td><input type="text" name="username"
					value="${requestScope.member.userName }" readonly></td>
			</tr>
			
			<tr height="40">
				<th width="150">암 호</th>
				<td><input type="password" name="userpwd" id="upwd"
					value="${requestScope.member.userPwd }"></td>
			</tr>
			
			<tr height="40">
				<th width="150">암호확인</th>
				<td><input type="password" id="upwd2"
					value="${requestScope.member.userPwd }"></td>

			</tr>
			
			<tr height="40">
				<th width="150">암호확인 메세지</th>
				<td><input type="text" id="confirm" style="display: none"
					readonly></td>
			</tr>
			
			<tr height="40">
				<th width="150">성 별</th>
				<td>
					<%-- <% if(member.getGender().equals("M")) { %> --%> 
					<c:if test="${member.gender eq 'M'}">
						<input type="radio" name="gender" value="M" checked>남 &nbsp; <input
						type="radio" name="gender" value="F">여 
					</c:if>
					
					<c:if test="${!(member.gender eq 'M') }"><%-- <% } else { %> --%> 
						<input type="radio" name="gender" value="M">남 &nbsp; <input
						type="radio" name="gender" value="F" checked>여 
					</c:if><%-- <% } %> --%>
				</td>
			</tr>
			
			<tr height="40">
				<th width="150">나 이</th>
				<td><input type="number" name="age" min="20" max="100"
					value="${requestScope.member.age }"></td>
			</tr>
			
			<tr height="40">
				<th width="150">이메일</th>
				<td><input type="email" name="email"
					value="${requestScope.member.email }"></td>
			</tr>
			
			<tr height="40">
				<th width="150">전화번호</th>
				<td><input type="tel" name="phone"
					value="${requestScope.member.phone }"></td>
			</tr>
			
			<tr height="40">
				<th width="150">취 미</th>
				<td>
				<c:forTokens var="hobby" items="${member.hobby }" delims=",">
					<c:if test="${hobby eq 'game' }"><c:set var="checked0" value="checked"/></c:if>
					<c:if test="${hobby eq 'reading' }"><c:set var="checked1" value="checked"/></c:if>
					<c:if test="${hobby eq 'music' }"><c:set var="checked2" value="checked"/></c:if>
					<c:if test="${hobby eq 'climb' }"><c:set var="checked3" value="checked"/></c:if>
					<c:if test="${hobby eq 'sport' }"><c:set var="checked4" value="checked"/></c:if>
					<c:if test="${hobby eq 'movie' }"><c:set var="checked5" value="checked"/></c:if>
					<c:if test="${hobby eq 'travel' }"><c:set var="checked6" value="checked"/></c:if>
					<c:if test="${hobby eq 'cook' }"><c:set var="checked7" value="checked"/></c:if>
					<c:if test="${hobby eq 'etc' }"><c:set var="checked8" value="checked"/></c:if>
				</c:forTokens>
					<table>
						<tr>
							<td width="150"><input type="checkbox" name="hobby"
								value="game" ${checked0 }>게임</td>
							<td width="150"><input type="checkbox" name="hobby"
								value="reding" ${checked1 }>독서</td>
							<td width="150"><input type="checkbox" name="hobby"
								value="music" ${checked2 }>음악</td>
						</tr>
						<tr>
							<td width="150"><input type="checkbox" name="hobby"
								value="climb" ${checked3 }>등산</td>
							<td width="150"><input type="checkbox" name="hobby"
								value="sport" ${checked4 }>운동</td>
							<td width="150"><input type="checkbox" name="hobby"
								value="movie" ${checked5 }>영화</td>
						</tr>
						<tr>
							<td width="150"><input type="checkbox" name="hobby"
								value="travel" ${checked6 }>여행</td>
							<td width="150"><input type="checkbox" name="hobby"
								value="cook" ${checked7 }>요리</td>
							<td width="150"><input type="checkbox" name="hobby"
								value="etc" ${checked8 }>기타</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr height="40">
				<th width="150">특이사항</th>
				<td><textarea name="etc" rows="3" cols="50">${requestScope.member.etc }</textarea></td>
			</tr>

			<tr height="40">
				<th width="150" colspan="2"><input type="submit" value="수정하기">
					&nbsp; <a href="/first/mdelete?userid=${requestScope.member.userId }">탈퇴하기</a>
					<a href="/first/index.jsp">시작페이지로</a></th>
			</tr>
		</table>
	</form>
	<%@ include file= "../../footer.html" %>
</body>
</html>