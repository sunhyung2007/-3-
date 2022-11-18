<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html><head>
<meta charset="utf-8">
<title>writeForm.jsp  글쓰기 페이지</title>
</head>
<body>
	<form action="../board?cmd=write"   method="post">
		제목 : <input type="text"  name="title"  required> <br> 
		작성자 : <input type="text"  name="writer" required="required"> <br> 
		내용 : <textarea rows="5" cols="50" required="required"  name="content">test!!!</textarea> <br> 
		<!--작성날짜	 : <input type="date" name="regdate"> <br>  -->
		<input type="submit"  value="글 등록" >
	</form>
</body>
</html>