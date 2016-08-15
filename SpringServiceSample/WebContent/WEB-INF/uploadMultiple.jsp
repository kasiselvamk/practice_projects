<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<title>Upload Multiple File Request Page</title>
<script src="./js/jquery-3.1.0.js"></script>
<script>

$( document ).ready(function() {

$('#upload').click( function() {
	  
    var formData = new FormData( $('#file')[0] );

    $.ajax({
        url:'http://localhost:7001/SpringServiceSample/uploadMultipleFile',
        type: 'POST',
        data: formData,
        async: false,
        success: function (data) {
            alert(data)
        },
        cache: false,
        contentType: false,
        processData: false
    });

    return false;
    
    });
});

</script>

</head>
<body>
	<form method="POST" id="myForm" action="uploadMultipleFile" enctype="multipart/form-data">
		File1 to upload: <input type="file" id ='file' name="file"> <input type="text" name="name" value=" ">
 <br/>
		File2 to upload: <input type="file" name="file"> <input type="text" name="name" value=" ">
 <br/>
		File3 to upload: <input type="file" name="file"> <input type="text" name="name" value=" "> 
 <br/> 
		File4 to upload: <input type="file" name="file"> <input type="text" name="name" value=" ">
 <br/>
		File5 to upload: <input type="file" name="file"> <input type="text" name="name" value=" ">
<br/>
<br/>
        <input type="hidden" name="isAutoAuth" value="false">
 <br/>
		<input type="button"  id = "upload" value="Upload"> Press here to upload the file!
	</form>
	
	 <br/>
	 <br/>
	
	<form method="POST" action="DeleteMultipleFile"  >
       <input type="text" name="name"  >
       </br>
        <input type="text" name="name"  >
       </br>
        <input type="text" name="name"  >
       </br>
        <input type="text" name="name"  >
       </br>
        <input type="text" name="name"  >
       </br>
        <input type="text" name="name"  >
       </br>
		<input type="submit" value="Delete"> Press here to Delete the file!
	</form>
	
</body>
</html>