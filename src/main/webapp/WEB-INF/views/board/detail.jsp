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

<h3>${map.title }</h3>
<h3><fmt:formatDate value="${map.date }" type="date" /></h3>

<div>${map.content }</div>

<img src="/resources/${map.storedName}" style="width:300px; height:300px;" />
<a href="/board/download?fileno=${map.fileno }">${map.originName }</a>

</body>
</html>