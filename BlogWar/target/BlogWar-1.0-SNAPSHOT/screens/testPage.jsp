<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Test page</title>
</head>
<body>
	<sec:authorize access="isAuthenticated()">
		Username : <sec:authentication property="principal.username" />
		Role : <sec:authentication property="principal.authorities" />
	</sec:authorize>
	
	<a href="<c:url value="/j_spring_security_logout"/>">Logout</a>
	
	<h2>${msg1}</h2>
	<h2>${msg2}</h2>
	<%-- 	<c:forEach items=${userList} var="element"> --%>
	<%-- 		<div>First name : <c:out value="${element.firstName }" /></div> --%>
	<%-- 		<div>Last name : <c:out value="${element.lastName }" /></div> --%>
	<%-- 	</c:forEach> --%>

	<c:out value="${msg1}" />
	<c:out value="${msg2}" />

</body>
</html>