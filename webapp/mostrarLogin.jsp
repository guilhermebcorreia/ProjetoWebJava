<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String login = request.getParameter("User");
		String password = request.getParameter("senha");
		
		out.print("Login: " + login + " - Senha: " + password);
	%>
</body>
</html>