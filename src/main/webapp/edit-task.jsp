<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Edit Task</title>
  <script type="text/javascript">
    function validateForm() {
      var taskName = document.forms["editTaskForm"]["title"].value;
      if (taskName == "") {
        alert("Task name must be filled out");
        return false;
      }
    }
  </script>
</head>

<body>
  <h1>Edit Task</h1>
  <logic:present name="errorMessage">
    <p style="color: red"><bean:write name="errorMessage"/></p>
  </logic:present>

  <html:form action="/edittask" method="post" onsubmit="return validateForm()">
    <html:hidden property="taskId" value="${task.id}"/>
    <table>
      <tr>
        <td>Task Title:</td>
        <td><html:textfield property="title" value="${task.title}"/></td>
      </tr>
      <tr>
        <td>Task Description:</td>
        <td><html:textarea property="description" value="${task.description}"/></td>
      </tr>
      <tr>
        <td>Task due date:</td>
        <td><html:textfield property="dueDate" value="${task.dueDate}"/></td>
      </tr>
      <tr>
        <td>Task Completed:</td>
        <td><html:checkbox property="completed" value="${task.completed}"/></td>
      </tr>
      <%--<tr>
        <td>Task Priority:</td>
        <td><html:select property="taskPriority" value="${task.taskPriority}">
              <html:option value="LOW">Low</html:option>
              <html:option value="MEDIUM">Medium</html:option>
              <html:option value="HIGH">High</html:option>
            </html:select></td>
      </tr>--%>
      <tr>
        <td colspan="2"><html:submit value="Save"/></td>
      </tr>
    </table>
  </html:form>
</body>
</html>
