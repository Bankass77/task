<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Task</title>
</head>
<body>
	<h1>Add Task</h1>
	<form id="addTaskForm" method="POST" action="/add-task">
		<label>Title</label> <input type="text" name="title" required><br>
		<label>Description</label>
		<textarea name="description"></textarea>
		<br> <label>Due Date</label> <input type="date" name="dueDate"
			required><br>
		<button type="submit">Add Task</button>
	</form>
	<script>
  const form = document.getElementById('addTaskForm');

  form.addEventListener('submit', event => {
    const title = form.elements.title.value.trim();
    const description = form.elements.description.value.trim();
    const dueDate = form.elements.dueDate.value;

    if (!title || !dueDate) {
      event.preventDefault();
      alert('Please enter a title and a due date.');
    }
  });
</script>
	<br>
	<a href="task-list.do">Task List</a>
</body>
</html>
