<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div id="container">
		<div id="body">
			<div id="bloglist">
				<c:forEach var="element" items="${blogUserList}">
					<br>
					<br>
					<a href="<c:url value="/showblogs?forUser=${element}" />"> <c:out
							value="Blogs of ${element}" /></a>
				</c:forEach>
			</div>

		</div>
	</div>
</body>
</html>