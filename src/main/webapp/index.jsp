<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="UTF-8">
<title>Basic Task Application - Welcome</title>
</head>
<body>
	<h1>Welcome To Task Application!</h1>

	<s:a href="%{localeEN}">English</s:a>
	<s:a href="%{localeDE}">French</s:a>

	<div>
		<s:a action="add-task">Add Task</s:a>
		<s:a action="edit-task">Update Task</s:a>
		<s:a action="delete-task">Delete Task</s:a>
		<s:a action="task-list">Task Lits</s:a>


	</div>
</body>
</html>