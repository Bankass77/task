<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Task List</title>
</head>
<body>

	<h1>Task List</h1>
	<table>
		<thead>
			<tr>
				<th>Title</th>
				<th>Description</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${tasks}" var="task">
				<tr>

					<td>${task.title}</td>
					<td>${task.description}</td>
				</tr>
			</c:forEach>
		</tbody>

	</table>
	<br>
	<a href="add-task.jsp"> Add Task</a>

</body>
</html>