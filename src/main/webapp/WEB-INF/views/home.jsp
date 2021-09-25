<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
<script
  src="https://code.jquery.com/jquery-3.6.0.js"
  integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
  crossorigin="anonymous"></script>	
</head>
<body>
<h1>
	<p>
	<spring:message code="page.title"/>
	</p>
</h1>

<a href="<c:url value='/board/list'/>">Board List</a>
<br>
<a href="<c:url value='/board/map'/>">Naver MAP</a>
<br>
<a href="<c:url value='/board/datatableList'/>">datatable Board List</a>
<br>

<p><spring:message code="title.main"/></p>
<p><spring:message code="title.content"/></p>
<p><spring:message code="title.end"/></p>

<button type="button" class="lang" data-lang="kor">한국어</button>
<button type="button" class="lang" data-lang="eng">영어</button>
<button type="button" class="lang" data-lang="jpn">일본어</button>

<P> <p><spring:message code="page.time"/>&nbsp;${serverTime}. </P>
</body>
<script type="text/javascript">
$('.lang').click(function(){
	location.href = "/lang?lang=" + $(this).data('lang');
});
</script>
</html>
