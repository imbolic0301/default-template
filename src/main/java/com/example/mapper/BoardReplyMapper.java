package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.example.domain.BoardReplyEntity;

@Repository
public interface BoardReplyMapper {

	@Select(""
			+ "	SELECT * "
			+ "	FROM board_reply "
			+ "	WHERE board_id = #{boardId} AND parent_id IS NULL AND is_del = 0"
			+ "	ORDER BY id DESC"
			+ "	LIMIT #{skip}, #{size}")
	List<BoardReplyEntity> getOriginReplyListByBoardId(
			@Param("boardId") Long boardId,
			@Param("skip") Integer skip, @Param("size") Integer size);
	

	//차후 조회에서 특정 질문에 대해 n개만큼 계층을 가져갈 경우 사용하는 쿼리
	@Select("SELECT * FROM board_reply WHERE board_id = #{boardId} AND parent_id IS NULL ORDER BY origin_id ASC, id ASC, parent_id ASC")
	List<BoardReplyEntity> getSubReplyList(@Param("boardId") Long boardId);
	
	@Insert(""
			+ "INSERT INTO board_reply ("
			+ "	user_id,"
			+ "	board_id,"
			+ "	content,"
			+ "	parent_id,"
			+ "	origin_id"
			+ ") VALUES ("
			+ "	#{userId},"
			+ "	#{boardId},"
			+ "	#{content},"
			+ "	#{parentId},"
			+ "	#{originId}"
			+ ")"
			)
	void createBoardReply(BoardReplyEntity entity);
	
	@Update(""
			+ "	UPDATE board_reply "
			+ "	SET"
			+ "		content = IFNULL(#{content}, content),"
			+ "		mod_dt = NOW() "
			+ "	WEHRE id = #{id}"
			)
	void updateBoardReply(BoardReplyEntity entity);
	
	@Update("UPDATE board_reply SET is_del = 1 AND mod_dt = now() WHERE id = #{id}")
	void removeBoardReply(BoardReplyEntity entity);
}
