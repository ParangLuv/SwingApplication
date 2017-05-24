<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/jquery/1.12.4/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<body>
	id :
	<input type="text" name="id" id="id">
	<br> pw :
	<input type="password" name="password" id="pw">
	<br>


	<button onclick="login()">로그인</button>

</body>
<script type="text/javascript">
	function login() {
		$.ajax({
			url : "${pageContext.request.contextPath }/loginCheck",
			type : "post",
			data : {
				id : $("#id").val(),
				password : $("#pw").val()
			},
			success : function(data) {
				if (data == "success") {
					alert($("#id").val() + "님 환영");
					
					location.href = "${pageContext.request.contextPath }/pic"
					
				} else if (data == "noId") {
					alert("아이디 없음");
				} else if(data== "fail") {
					alert("비번이 틀림");
					$("#pw").select();
				}

			},

		});
	}
</script>
</html>
