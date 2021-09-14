<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="/board/write" method="post" enctype="multipart/form-data">

	<table>
		<tr>
			<td> <label for="title"> 제목 : <input type="text" id="title" name="title" /> </label> </td>
		</tr>
		
		<tr>
			<td> <textarea rows="10" cols="40" id="content" name="content"></textarea> </td>
		</tr>
		
		<tr>
			<td> <label> 파일 : <input type="file" id="file" name="file" /> </label> </td>
		</tr>
	</table>
	
	<input type="submit" value="쓰기" />

</form>


</body>
</html>