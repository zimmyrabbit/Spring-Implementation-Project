<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
  src="https://code.jquery.com/jquery-3.6.0.js"
  integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
  crossorigin="anonymous"></script>
</head>
<body>

<h1>BoardTest</h1>

<hr>

<table>
	<tr>
		<th>글번호</th>
		<th>제목</th>
		<th>작성일</th>
	</tr>
	<c:forEach items="${list }" var="item">
		<tr>
			<td>${item.no }</td>
			<td><a href="/board/detail?no=${item.no}">${item.title }</a></td>
			<td><fmt:formatDate value="${item.date }" type="date" /></td>
		</tr>
	</c:forEach>
</table>
<form id="excelDownForm"></form>

<a href="/board/write"><button type="button">글쓰기</button></a>
<a href="/board/videoWrite"><button type="button">동영상 글쓰기</button></a>
<button type="button" id="excelDown">리스트 엑셀다운</button>

</body>
<script type="text/javascript">
$('#excelDown').click(function(){
	execExcelDown();
});


function execExcelDown(){
	alert('게시판 리스트를 엑셀 다운로드 합니다.');
	var formObj = $('#excelDownForm');
	formObj.attr('action','/board/excelDown');
	formObj.attr("method", "post");
	formObj.submit();
}
</script>
</html>