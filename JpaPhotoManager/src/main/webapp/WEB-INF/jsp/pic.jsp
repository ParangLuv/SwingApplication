<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html >
<html>
<head>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/jquery/1.12.4/jquery.min.js"></script>
	
	<style>
	div {border: 1px dashed gray; margin: 10px; width:140px; float:left; text-align:center; padding:10px;}
	span{overflow: auto; word-break: break-all;}
	</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<body>
	${sessionScope.id }님 환영합니다.
	<Br>

	<form action="multiUploadForm" method="post"
		enctype="multipart/form-data">

		<input type="hidden" name="id" value="${sessionScope.id }"> <input
			type="file" name="pic" multiple="multiple">
		<button>확인</button>
	</form>


	<c:forEach var="list" items="${list }">
	
	<div>
	<p>
		<a href="displayFile?filename=${list.pic.replace('s_','') }"><img
			src="displayFile?filename=${list.pic }"></a>
		<a
			href="${pageContext.request.contextPath}/deleteFile?pic=${list.pic}">x</a><br>
			<span>${list.pic }</span>
			<br>
			<fmt:formatDate value="${list.regDate }" pattern="yyyy-MM-dd"/>			</p>
			</div>
	</c:forEach>
	<c:if test="${list.size() == 0 }">
		<h1>등록된 사진이 없습니다.</h1>
	</c:if>


</body>
<script type="text/javascript">
	
</script>
</html>
