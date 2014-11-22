<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div id="container">
		<div id="body">
			<div id="blogtitle">
				<%-- 				<c:out value="${blogObject.userName}" /> --%>
				<%-- 				<c:out value="${blogObject.blogTitle}" /> --%>
				${blogObject.blogTitle}
			</div>
			<div id="blogdate">
				<%-- 				<c:out value="Updated at ${blogObject.blogDate}" /> --%>
				Updated at ${blogObject.blogDate}
			</div>
			<div id="blogbodysample">
				<%-- 				<c:out value="${blogObject.blogBody}" /> --%>
				${blogObject.blogBody} <br> Authored by ${blogObject.userName}
			</div>
			<div id="comment">
				<c:forEach var="comment" items="${blogObject.commentList}">
					<c:out value="${comment.userName}" />
					<div id="blogdate">
						<c:out value="Commented at ${comment.commentDate}" />
					</div>
					<div id="blogbodysample">
						<c:out value="${comment.commentBody}" />
					</div>
					<br>
				</c:forEach>

			</div>
			<br>

			<form:form method="POST" action="newcomment.do" commandName="comment">

				<%-- 				<form:label path="userName">user :</form:label> --%>
				<%-- 				<form:input path="userName" size="100" ></form:input> --%>
				<br>
				<form:label path="commentBody">Add Comment :</form:label>
				<form:textarea path="commentBody" rows="5" cols="90" />

				<form:hidden path="blogId" value="${comment.blogId}" />
				<input type="submit" value="Post" />

			</form:form>
		</div>
	</div>

</body>
</html>