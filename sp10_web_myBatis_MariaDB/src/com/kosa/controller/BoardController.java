package com.kosa.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.executor.ReuseExecutor;

import com.kosa.dao.BoardDAO;
import com.kosa.model.BoardDTO;


@WebServlet("/board")
public class BoardController extends HttpServlet {
	
	public BoardController() { }
	
	public void service(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		
		String cmd = request.getParameter("cmd");  //  ~~?cmd=list
		System.out.println("cmd : " + cmd);
		
		if( cmd.equals("list") ) {			list(request, response);
		} else if( cmd.equals("write") ) {			write(request, response);
		} else if( cmd.equals("update") ) {		update(request, response);
		} else if( cmd.equals("delete") ) {		delete(request, response);
		} else if( cmd.equals("detail") ) {		detail(request, response);
		} else if( cmd.equals("search") ) {		search(request, response);
		} 
	} // service end

	public void list(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		BoardDAO dao = new BoardDAO();
		List<BoardDTO>  list = dao.selectAll();
		
		if( list != null ) {
			request.setAttribute("list", list);  // data save
			request.getRequestDispatcher("/views/list.jsp").forward(request, response);
		} else {
			response.sendRedirect("views/error.jsp");
		}
	} // list end
	
	
	
	public void write(HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		
		System.out.println("write page 여기까지 왔는지?");
		
		request.setCharacterEncoding("utf-8");
		
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		System.out.println(title + ",\t" + writer +",\t" + content);
		
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = new BoardDTO(title, writer, content);
		
		int rowcount = dao.insert(dto);   // db insert 처리
		
		if( rowcount > 0 ) {
			response.sendRedirect("board?cmd=list");
		} else {
			response.sendRedirect("views/error.jsp");
		}
	} // write end

	public void delete(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		String seq = request.getParameter("seq");
		BoardDAO dao = new BoardDAO();
		int rowcount = dao.delete(Integer.parseInt(seq));
		if( rowcount > 0 ) {
			list(request, response);   // response.sendRedirect("board?cmd=list");
		} 
	} // delete end
	
	// 상세보기 - 내용 클릭시 
	public void detail(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		String seq = request.getParameter("seq");
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = dao.detailList(Integer.parseInt(seq));
		
		if( dto != null ) {
			request.setAttribute("dto", dto);
			request.getRequestDispatcher("/views/detail.jsp").forward(request, response);
		} else {
			response.sendRedirect("views/error.jsp");
		}
	} // detail end
	
	// update
	public void update(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		String seq = request.getParameter("seq");
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BoardDTO dto =  new BoardDTO( title,  writer, content );  
		BoardDAO dao = new BoardDAO();
		
		int rowcount = dao.update(dto);
		if (rowcount > 0) {
			response.sendRedirect("board?cmd=list");  //list(request, response);
		}
	} // end update

	// search
	public void search(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		// 컬럼명
		String column = request.getParameter("column");
		String keyvalue = request.getParameter("keyvalue");
		System.out.println(column + " / " + keyvalue);

		Map<String, String> map = new HashMap<>(); // collection
		map.put("column", column); // column : title or writer or contnet
		map.put("keyvalue", keyvalue); // keyvalue : 김연아

		BoardDAO dao = new BoardDAO();
		List<BoardDTO> list = dao.getSearchList(map);
		if (list != null) {
			request.setAttribute("list", list);
			request.getRequestDispatcher("/views/searchList.jsp").forward(request, response);
		} else {
			response.sendRedirect("views/error.jsp");
		}

	}

	public void search2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String keyvalue = request.getParameter("keyvalue");

		Map<String, String> map = new HashMap<>(); // collection
		map.put("title", title);
		map.put("writer", writer);
		map.put("content", content);
		map.put("keyvalue", keyvalue);

		BoardDAO dao = new BoardDAO();
		List<BoardDTO> list = dao.getSearchList2(map);
		
		if (list != null) {
			request.setAttribute("list", list);
			request.getRequestDispatcher("/views/searchList.jsp").forward(request, response);
		} else {
			response.sendRedirect("views/error.jsp");
		}
	}

}







