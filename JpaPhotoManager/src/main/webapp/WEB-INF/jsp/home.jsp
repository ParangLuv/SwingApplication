<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P><a href="${pageContext.request.contextPath }/register">회원가입</a> / <a href="${pageContext.request.contextPath }/login">로그인</a></P>
</body>
</html>
