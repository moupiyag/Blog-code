<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
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
			<c:out value="${msg1}" />

			<c:if test="${not empty param.forUser}">
				<h2>Blogs of ${user}</h2>
			</c:if>

			<c:forEach var="blog" items="${blogList}" varStatus="counter">
				<div id="blogtitle">
<%-- 				<c:out value="${counter.count}" />.  --%>
				<a href="<c:url value="/showblog.do?blog=${blog.id}" />"><c:out value="${blog.blogTitle}" /></a>
				</div>
			<div id="blogdate">
			<c:out value="Updated at ${blog.blogDate}" />
			</div>
			<div id="blogbodysample"><c:out value="${blog.blogBody}" /></div>
				<br>
			</c:forEach>



		</div>
</body>
</html>