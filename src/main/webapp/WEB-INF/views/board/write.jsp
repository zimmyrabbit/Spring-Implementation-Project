<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

<form action="/board/write" method="post" enctype="multipart/form-data" id="form_write">
<input type="hidden" id="file_org" name="file_org" value="" >
<input type="hidden" id="file_size" name="file_size" value="0" >

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
	
	<input type="button" id="test" value="쓰기" />
</form>
</body>

<script>
	$('#test').click(function(){
		if($('#file').val().length > 0) {
			
		       let fileLen = $('#file').val().length;
		       let fileDot = $('#file').val().lastIndexOf(".");
		       let fileType = $('#file').val().substring(fileDot+1, fileLen).toLowerCase();
		       let max_fileSize = 10485760;  // 파일 제한 10MB
		       var check_file_type=new Array();
		       check_file_type=['png','jpg','jpeg','gif','pdf'];  //허용 확장자

		       //파일 확장자 체크
			   if(check_file_type.indexOf(fileType) == -1) {
		             alert('png, jpg, jpeg, gif, pdf 확장자의 파일만 업로드할 수 있습니다.');
			         return false;
			   }
			   //파일 사이즈 체크
			   if( max_fileSize < $("#file")[0].files[0].size ) {
			         alert('파일 용량은 10MB 이하로 제한됩니다.');
			         return false;
			   }

			   $('#file_size').val($('input[type=file]')[0].files[0].size);
		 }else {

		 }

		 if( $('#title').val() == "" ) {
				alert('제목이 빈칸이에요!');
				return false;
		 }		 

		 if( $('#content').val() == "" ) {
			alert('대화가 빈칸이에요!');
			return false;
		 }

		 document.getElementById('form_write').submit();
		 		
	});

</script>
</html>