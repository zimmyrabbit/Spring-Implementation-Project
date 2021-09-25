<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.js"> </script>

<link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>

</head>
<body>

<table id="userTable" class="display" >
    <thead>
        <tr>
            <th>no</th>
            <th>title</th>
            <th>date</th>
        </tr>
    </thead>
</table>

</body>

<script type="text/javascript">

$(document).ready(function(){
    $('#userTable').DataTable({
        pageLength: 10,
        bPaginate: true,
        bLengthChange: true,
        bAutoWidth: false,
        processing: false,
        ordering: true,
        serverSide: false,
        searching: true,
        ajax : {
            type : 'POST',
            url : '/board/datatableListView',
            dataType : 'json',
			dataSrc : function(data) {
				return data;
			}
        },
        columns : [
            {data: "no"},
            {data: "title"},
            {data: "excelDate"},
        ]
    });
});

</script>
</html>