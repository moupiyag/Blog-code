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
	<div>
		<form:form method="POST" action="newblog.do" commandName="blog">
			<table>
				<tr>
					<td><form:label path="blogTitle">Title: </form:label></td>
					<td><form:input path="blogTitle" /></td>
				</tr>
				<tr>
					<td><form:label path="blogBody">Blog :</form:label></td>
					<td><form:input path="blogBody" /></td>
				</tr>
				<!-- 				<tr> -->
				<%-- 					<td><form:label path="tagList">Tags :</form:label></td> --%>
				<%-- 					<td><form:input path="tagList" /></td> --%>
				<!-- 				</tr> -->
				<tr>
					<td></td>
					<td><input type="submit" value="Add" /></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>