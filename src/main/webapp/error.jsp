<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page isErrorPage="true"%>

<html>
<head>
<title>Error</title>
</head>
<body>
	<h1>An error occurred</h1>
	<p>The following exception was thrown:</p>
	<pre>
      <%= exception.toString() %>
    </pre>
</body>
</html>
