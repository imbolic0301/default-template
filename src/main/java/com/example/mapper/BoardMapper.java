package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.example.domain.BoardEntity;

@Repository
public interface BoardMapper {
	
	@Select("")
	List<BoardEntity> getBoardListByInfoId(@Param("infoId") Integer infoId);
	
	@Select("")
	BoardEntity getBoardById(@Param("id") Long id);
	
	@Insert(""
			+ "INSERT INTO board ("
			+ "	info_id,"
			+ "	user_id,"
			+ "	category,"
			+ "	title,"
			+ "	content"
			+ ") VALUES ("
			+ "	#{infoId},"
			+ "	#{userId},"
			+ "	#{category},"
			+ "	#{title},"
			+ "	#{content}"
			+ ")")
	void createBoard(BoardEntity entity);
	
	@Update("")
	void updateBoard(BoardEntity entity);
	
	@Update("")
	void remove(Long id);
	
}
