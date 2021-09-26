<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.js"> </script>

<title>Insert title here</title>
</head>
<body>

보낼주소 : <input type="email" id="email" name="email"> <br><br>
제목 : <input type="text" id="title" name="title"> <br>

<textarea rows="10" cols="40" id="content" name="content"></textarea>

<button onclick="mailSend()">Send</button>

</body>

<script type="text/javascript">

function mailSend() {
	
	var email = $("#email").val();
	var title = $("#title").val();
	var content = $("#content").val();

	$.ajax({
		url : '/board/mailSend'
		, type : 'post'
		, data : {'email' : email
				, 'title' : title
				, 'content' : content}
		, success : function() {
			alert("Mail Send Success");
			
			$("#email").val("");
			$("#title").val("");
			$("#content").val("");
		}
	})
}

</script>

</html>