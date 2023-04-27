<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Task</title>
</head>
<body>
    <h1>Add Task</h1>

    <%-- Afficher le message d'erreur, s'il y en a un --%>
    <c:if test="${not empty errorMessage}">
        <p class="error">${errorMessage}</p>
    </c:if>
    <s:form id="addTaskForm" action="addtask">
        <s:textfield label="Titre" name="title" />
        <s:textarea label="Description" name="description" />
        <s:datetimepicker label="Date d'échéance" name="dueDate" />
        <s:checkbox label="Terminée" name="completed" />
        <s:submit value="Enregistrer" />
        <s:reset value="Annuler" />
    </s:form>
    <script>
        const form = document.getElementById('addTaskForm');

        form.addEventListener('submit', event => {
            const title = form.elements.title.value.trim();
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
