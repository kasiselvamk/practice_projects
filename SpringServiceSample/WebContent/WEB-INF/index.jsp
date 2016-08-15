
<html>
<head>
<title>Upload Multiple File Request Page</title>
</head>
<body>
	<form method="POST" action="http://localhost:7001/SpringServiceSample/uploadMultipleFile" enctype="multipart/form-data">
		File1 to upload: <input type="file" name="file"> <input type="text" name="name" value=" ">
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
		<input type="submit" value="Upload"> Press here to upload the file!
	</form>
	
	 <br/>
	 <br/>
	
	<form method="POST" action="http://localhost:7001/SpringServiceSample/DeleteMultipleFile"  >
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