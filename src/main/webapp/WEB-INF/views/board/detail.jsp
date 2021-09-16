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

<h3>${map.title }</h3>
<h3><fmt:formatDate value="${map.date }" type="date" /></h3>

<div>${map.content }</div>
	
	<c:if test="${not empty map.storedName}">
		<img src="/file/board/${map.storedName}" style="width:300px; height:300px;" />
		<br>
		<br>
		
		<button type="button" id="file_down">파일 다운로드</button>
	</c:if>
</body>

<<<<<<< HEAD
<img src="/resources/${map.storedName}" style="width:300px; height:300px;" />
<a href="/board/download?fileno=${map.fileno }">${map.originName }</a>
=======
<script>
>>>>>>> c3da0955382f8473c09706e63572742220d16bf2

// 파일 다운로드
$('#file_down').unbind("click").click(function(e) {	
	document.location.href = "<c:url value='/board/fileDown'/>?fileName=" + '${map.originName}' + '&saveName=' + '${map.storedName}' ;
	e.preventDefault();
});

</script>
</html>