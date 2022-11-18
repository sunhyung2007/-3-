package com.kosa.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;

import com.kosa.config.SqlSessionFactoryService;
import com.kosa.model.BoardDTO;

@Component
public class BoardDAO {

	private SqlSessionFactory sqlSessionFactory;
	private SqlSession sqlSession = null; // DML 처리 함수를 갖고 있다.

	public BoardDAO() {
		sqlSessionFactory = SqlSessionFactoryService.getSqlSessionFactory();
	}

	// select all
	public List<BoardDTO> selectAll() {
		try {
			sqlSession = sqlSessionFactory.openSession(); // app과 db를 연결(통로)
			return sqlSession.selectList("dao.selectAll");

		} catch (Exception e) {
			return null;
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		} // try end
	}

	// insert
	public int insert(BoardDTO dto) {

		try {
			sqlSession = sqlSessionFactory.openSession();
			int result = sqlSession.insert("dao.insert", dto);
			sqlSession.commit(); //
			return result;

		} catch (Exception e) {
//			sqlSession.rollback();  // 단일 쿼리 실행시 의미가 없다.
			return 0;
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		} // try end
	} // write end

	// delete
	public int delete(int seq) {
		try {
			sqlSession = sqlSessionFactory.openSession();
			int rowcount = sqlSession.delete("dao.delete", seq);
			sqlSession.commit();
			return rowcount;

		} catch (Exception e) {
			return 0;
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		} // try end
	} // delete end

	// detailList
	public BoardDTO detailList(int seq) {
		try {
			sqlSession = sqlSessionFactory.openSession();
			BoardDTO dto = sqlSession.selectOne("dao.detailList", seq);
			return dto;
		} catch (Exception e) {
			return null;
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	} // end detail
	
	// update
	public int update(BoardDTO dto) {  
		try{
			sqlSession = sqlSessionFactory.openSession();
			int rowcount= sqlSession.update("dao.update", dto);
			sqlSession.commit();
			
			return rowcount;
			
		}catch(Exception e){			return 0;		}
		  finally {		if(sqlSession != null){ sqlSession.close(); }		
		}
	} // end update
	
	//전체 검색 조회_1
	public List<BoardDTO> getSearchList(Map<String, String> map){
		try{
			 /*for(Map.Entry<String, String> m : map.entrySet()){
				 System.out.println(m.getKey() + "/" + m.getValue() +"-");
			 }*/
			 sqlSession = sqlSessionFactory.openSession();
			 return sqlSession.selectList("dao.selectSearch" ,map);
		}catch(Exception e){
			 return null;
		}finally{
			if(sqlSession != null){ sqlSession.close(); }
		}
	}

	// 전체 검색 조회_2
	public List<BoardDTO> getSearchList2(Map<String, String> map) {

		try {
			/*
			 * for(Map.Entry<String, String> m : map.entrySet()){
			 * System.out.println(m.getKey() + "/" + m.getValue() +"-"); }
			 */
			sqlSession = sqlSessionFactory.openSession();
			return sqlSession.selectList("dao.selectSearch2", map);
			
		} catch (Exception e) {
			return null;
		} finally {
			if (sqlSession != null) {		sqlSession.close();		}
		}
	}
}
