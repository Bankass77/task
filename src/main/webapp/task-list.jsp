<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
				<th>Due Date</th>
				<th>Completed</th>
				<!-- <th>Priority</th> -->
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${tasks}" var="task">
				<tr>

					<td>${task.title}</td>
					<td>${task.description}</td>
					<td>${task.duedate}</td>
					<td>${task.completed}</td>
					<%-- <td>${task.completed}</td> --%>
				</tr>
			</c:forEach>
		</tbody>

	</table>
	<br>
	<a href="add-task.jsp"> Add Task</a>

</body>
</html>