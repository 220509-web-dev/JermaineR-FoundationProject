<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<script type="text/javascript"
    src="http://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js">
    </script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title> Login Page </title>

</head>
<body>
<%= request.getSession().getAttribute("auth-user", User); %>


</body>
</html>