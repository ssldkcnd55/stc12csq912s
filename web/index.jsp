<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.Member"%> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%-- <%
    //일반적인 자바 소스 코드가 작성되는 영역임 : 스크립트릿 태그
    Member loginUser = (Member)session.getAttribute("loginUser");
    %> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>first</title>
<link href="resources/css/header.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	
	/* function displayTop3(){ */
	$(function(){
		$.ajax({
			url : "/first/btop3",
			type : "get",
			dataType : "json",
			success : function(data){
				console.log("success : " + data);
				var jsonStr = JSON.stringify(data);  //객체를 문자열로 변환
				console.log(jsonStr);
				var json = JSON.parse(jsonStr); //문자열을 배열 객체로 바꿈
				
				var values = "";
				for(var i in json.list){
					//한글 깨짐을 막기 위해 문자 인코딩 처리한 json 객체의 값은 decodeURIComponent() 로 디코딩 처리함
					values += "<tr><td>" + json.list[i].bnum + "</td><td>" 
							+ "<a href='/first/bdetail?bnum=" + 
							json.list[i].bnum + "&page=1'>" + 
							decodeURIComponent(json.list[i].btitle) + 
							"</a></td><td>" + json.list[i].bwriter + 
							"</td><td>" + json.list[i].bdate + "</td><td>"
							+ json.list[i].rcount + "</td></tr>";
				}
				
				$("#toplist").html(values);
			},
			error: function(jqXHR, textStatus, errorThrown){
				console.log("error : " + jqXHR + ", " 
						+ textStatus + ", " + 
						errorThrown);
			}
		}); //ajax
		
		//board 테이블 옆에 최근 등록한 공지글 3개가 출력되게 함
		$.ajax({
			url : "/first/ntop3",
			type : "get",
			dataType : "json", 
			success : function(data){
				console.log("success : " + data);
								
				var jsonStr = JSON.stringify(data);  //객체를 문자열로 변환
				console.log(jsonStr);
				var json = JSON.parse(jsonStr); //문자열을 배열 객체로 바꿈
						
				var values = "";
				for(var i in json.list){
					//한글 깨짐을 막기 위해 문자 인코딩 처리한 json 객체의 값은 decodeURIComponent() 로 디코딩 처리함
					values += "<tr><td>" + json.list[i].no + "</td><td>" 
							+ "<a href='/first/ndetail?no=" + 
							json.list[i].no + "&action=loginCheck'>" + 
							decodeURIComponent(json.list[i].title) + 
							"</a></td><td>" + json.list[i].writer + 
							"</td><td>" + json.list[i].date + "</td></tr>";
				}
				
				$("#newNotice").html(values);
			},
			error: function(jqXHR, textStatus, errorThrown){
				console.log("error : " + jqXHR + ", " 
						+ textStatus + ", " + 
						errorThrown);
			}
		}); //newNotice		
	}); //ready
/* 	}; */ //body load 
</script>
</head>
<!-- <body onload="displayTop3();"> -->
<body>
	<%-- <% if(loginUser != null && loginUser.getUserId().equals("admin")) {%>
		<%@ include file="adminHeader.jsp" %>
	<% } else { %>
		<%@ include file ="header.jsp" %>
	<% } %>	 --%>
	<c:if test =  "${ !empty sessionScope.loginUser and sessionScope.loginUser.userId eq 'admin' }">
		<c:import url="adminHeader.jsp"></c:import></c:if> 
	<c:if test =  "${ !empty sessionScope.loginUser and !(sessionScope.loginUser.userId  eq 'admin') }">
		<c:import url="header.jsp"></c:import></c:if>
	<c:if test =  "${ empty sessionScope.loginUser  }">
		<c:import url="header.jsp"></c:import></c:if>
	<hr style="clear:both;">
   <div id="banner" style="margin-left:40px; float:left; margin-right:15px;">
      <img src="resources/images/무한극장.gif" width="520px" height="150px">
   </div>
   <%-- JSP 주석 태그 --%>
   <%-- action: 폼의 입력값을 전송받을 서버쪽 컨트롤러의 이름 --%>
   <%-- <% if(loginUser == null) { %> --%>
   <c:if test="${ empty sessionScope.loginUser }">
   <form action="login" method="post">
      <table width="220px" height="85px">   <!-- 서버쪽에선 반드시 name이 필요하다!!! -->
         <tr height="25px"><td width="170px"><input type="text" name="userid" required autofocus></td>
            <td width="50px" rowspan="2">
            <input type="submit" value="로그인" style="width:55px; height:45px; background:pink; color:navy; margin:0px; padding:0px;"></td></tr>
         <tr height="25px"><td><input type="password" name="userpwd" required autofocus></td></tr>
         <tr height="35px">
            <td colspan="2"><a href="views/member/enroll.html"><font size="2">회원가입</font></a> &nbsp; <A><font size="2">아이디/암호 분실시</font></A></td>
         </tr>
      </table>
   </form>
   </c:if>
  <%--  <% } else {%> --%>
  <c:if test="${ !empty sessionScope.loginUser }">
      <form action="">
      <table width="220px" height="85px">   <!-- 서버쪽에선 반드시 name이 필요하다!!! -->
         <tr height="25px">
         	<td width="170px"><%--  <%= loginUser.getUserName() %> --%>${sessionScope.loginUser.userName }님</td><!-- sessionScope는 생략가능 -->
         	<td width="80">         		
         		<a href="/first/logout"><input type="button" value="로그아웃" style="width:55px; height:45px; background:pink; color:navy; margin:0px; padding:0px;"></a>
         	</td>
         	</tr>
         <tr height="25px">
         	<!-- 쿼리 스트링 : a 태그에서 사용함, 컨트롤러에게 전송할 값 지정
         		href="대상이름?이름=전송값/값이 여러개 일 경우 구분자는 &
         		ex) 이름=값&이름=값&이름=값...
         		a 태그는 전송방식이 무조건 get이다. -->
            <td>
            <%-- <a href="/first/mdetail?userid=<%= loginUser.getUserId() %>"> --%>
            <a href="/first/mdetail?userid=${sessionScope.loginUser.userId }">
            내 정보 보기</a></td>
            <td>&nbsp;</td>
         </tr>
      </table>
   </form>
   </c:if>
   <%-- <% } %> --%>
   <hr style="clear:both;"> <br>
   <!-- 문서가 로딩이 완료되면, 자동으로 실행되게 이벤트 처리함
   		게시글 조회수 상위 3개의 목록만 목록보기형식으로
       출력되게 jQuery 의 Ajax 로 구현하시오. 
   		요청 url : btop3 
   		서블릿클래스명 : board.controller.BoardTop3Servlet
   		서비스 메소드 : public ArrayList<Board> selectTop3()
   		Dao 메소드  : 
   		 public ArrayList<Board> selectTop3(Connection con)
   -->
   <br>
<div style="float:left;border:1px solid olive;padding:5px;margin:5px;">
	<h4 style="background:olive;color:white;">인기 게시글</h4>
	<table id="toplist" border="1" cellspacing="0">
	</table>
</div>
<div style="margin:5px;border:1px solid navy;padding:5px;">
	<h4 style="background:navy;color:white;">신규 공지글</h4>
	<table id="newNotice" border="1" cellspacing="0">
	</table>
</div>	
<br style="clear:both;">
   <%@ include file= "footer.html" %>
</body>
</html>






