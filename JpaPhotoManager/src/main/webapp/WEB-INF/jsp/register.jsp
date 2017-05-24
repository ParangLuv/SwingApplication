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

	<form action="register" method="post" id="form1">
		id : <input type="text" name="id" id="id"><a href="#"
			onclick="duplicated()">중복체크</a> <br> password : <input
			type="password" name="password" id="pw1"><br> password
		확인: <input type="password" id="pw2"><br> email: <input
			type="text" name="email"><br> name : <input type="text"
			name="name"><br> tel : <input type="text" name="tel"><br>

	</form>
	<button onclick="submit1()">등록</button>
</body>
<script type="text/javascript">
	function submit1() {
		if ($("#pw1").val() != $("#pw2").val()) {
			alert("비밀번호가 일치하지 않습니다");
		} else {
			$("#form1").submit();
		}
	}
	function duplicated() {
		event.preventDefault();
		$.ajax({
			url : "${pageContext.request.contextPath }/idcheck",
			type : "post",
			data : {
				id : $("#id").val(),
			},
			success : function(data) {
				if (data == "duplicated") {
					alert("중복된 아이디입니다");
				} else {
					alert("사용할수 있는 아이디 입니다");
				}

			}
		});
	}
</script>
</html>
