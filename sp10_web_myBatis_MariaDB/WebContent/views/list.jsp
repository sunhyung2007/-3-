<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list.jsp  List Page</title>
</head>
<body>
<h1> List Page </h1>
<table border="1" width="850">
		<tr>
			<th>번호</th>			
			<th>제목</th>			
			<th>작성자</th>			
			<th>내용</th>			
			<th>작성날짜</th>			
			<th>조회수</th>
		</tr>
		<c:forEach  var="list"  items="${ list }">
			<tr>
				<td>${ list.seq }</td>
				<td>${ list.title }</td>
				<td>${ list.writer }</td>
				<td><a href="javascript:location.href='board?cmd=detail&seq=${ list.seq }' ">${ list.content }</a></td>
				<td>${ list.regdate }</td>
				<td>${ list.readcount }</td>
				<%-- <td><a href="javascript:location.href='cmd=detail&seq=${ list.seq }' ">상세보기</a></td> --%>
				<td><a href="javascript:location.href='board?cmd=delete&seq=${ list.seq }' ">삭제하기</a></td>
			</tr>
		</c:forEach>
</table><br>
<a href="views/writeForm.jsp">글쓰기</a>
</body>
</html>