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

</body>

<script type="text/javascript">

$(document).ready(function() {
	$.ajax({
		url : '/board/mailSend'
		, type : 'post'
		, data : {'abc' : 'abc'}
	})
})

</script>

</html>