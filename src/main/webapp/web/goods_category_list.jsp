<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
     <c:set var="base" scope="request" value="${pageContext.request.contextPath}"></c:set> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="0" cellspacing="0" cellpadding="5">
	<tr>
		<th>id</th>
		<th>parent_id</th>
		<th>name</th>
		<th>status</th>
		<th>sort_order</th>
		<th>is_parent</th>
	</tr>
	<c:forEach var="i" items="${data}">
		<tr>
			<td>${i.id}</td>
			<td>${i.parentId}</td>
			<td>${i.name}</td>
			<td>${i.status}</td>
			<td>${i.sortOrder}</td>
			<td>${i.isParent}</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>