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

<!-- 	Username : -->
<%-- 	<sec:authentication property="principal.username" /> --%>
<!-- 	Role : -->
<%-- 	<sec:authentication property="principal.authorities" /> --%>


	<%-- 	<h2>${msg1}</h2> --%>
	<%-- 	<h2>${msg2}</h2> --%>

	<div id="container">
		<div id="body">
			<sec:authorize access="isAuthenticated()">
				<h2>
					Welcome
					<sec:authentication property="principal.username" />
					!
				</h2>
			Please go to the menu.
			</sec:authorize>
		</div>
</body>
</html>