<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>searchList.jsp</title>
</head>
<body>
	<h3>단일검색하기</h3>
	<form action="board?cmd=search" method="post">
		<select name="column">
			<option value="title">제목</option>
			<option value="writer">작성자</option>
			<option value="contnet">내용</option>
		</select> 
		<input type="text" name="keyvalue"> 
		<input type="submit"	value="검색">
	</form>

	<h3>다중검색하기</h3>
	<!-- 검색: where writer='yuna' or title='title' or content='test' -->
	<form action="board?cmd=search2" method="post">
		<input type="checkbox" name="writer" value="writer" checked>
		   작성자 <input	type="checkbox" name="title" value="title">
			제목 <input type="checkbox" name="content" value="content">내용 <br> 
			검색어:<input	type="text" name="keyvalue"> <input type="submit" value="검색">
	</form>

	<hr>
	<table border="1" width="750">
		<tr>
			<th>번호</th>			
			<th>제목</th>			
			<th>작성자</th>			
			<th>내용</th>			
			<th>작성날짜</th>			
			<th>조회수</th>
		</tr>
		<c:forEach var="list" items="${ list }">
			<tr>
				<td>${ list.seq }</td>
				<td>${ list.title }</td>
				<td>${ list.writer }</td>
				<td><a
					href="javascript:location.href='board?cmd=detail&seq=${ list.seq }' ">${ list.content }</a></td>
				<td>${ list.regdate }</td>
				<td>${ list.readcount }</td>
				<td><a
					href="javascript:location.href='board?cmd=delete&seq=${ list.seq }' ">삭제하기</a></td>
			</tr>
		</c:forEach>
	</table>
	<a href="writeForm.jsp">글쓰기</a>

</body>
</html>