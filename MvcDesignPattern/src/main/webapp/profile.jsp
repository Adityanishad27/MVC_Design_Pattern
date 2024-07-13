<%@page import="com.model.users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%  users user =(users) session.getAttribute("session_user"); %>

<h3> Welcome To Profile </h3>
<h2>Name : <%= user.getFullName() %> </h2>
<h2>Email : <%= user.getEmail() %>  </h2>
<h2>Address : <%= user.getAddress() %> </h2>


<a href="Logout">LOGOUT</a>

</body>
</html>