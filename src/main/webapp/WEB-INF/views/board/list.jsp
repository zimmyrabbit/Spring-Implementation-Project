<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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

<a href="/board/write"><button type="button">글쓰기</button></a>

</body>
</html>