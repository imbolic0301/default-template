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
	
	@Select("SELECT * FROM board WHERE info_id = #{infoId} ORDER BY reg_dt DESC LIMIT #{skip}, #{size}")
	List<BoardEntity> getBoardListByInfoId(
			@Param("infoId") Integer infoId, 
			@Param("skip") Integer skip, @Param("size") Integer size);
	
	@Select("SELECT * FROM board WHERE id = #{id}")
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
	
	@Update(""
			+ "	UPDATE board"
			+ "	SET"
			+ "		title = IFNULL(#{title}, title), "
			+ "		content = IFNULL(#{content}, content) "
			+ "	WHERE id = #{id}")
	void updateBoard(BoardEntity entity);
	
	@Update("UPDATE board SET is_del = 1 AND mod_dt = now()")
	void remove(Long id);
	
}
