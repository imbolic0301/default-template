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

	@Select("")
	List<BoardReplyEntity> getBoardReplyListByBoardId(@Param("boardId") Long boardId);
	
	@Insert("")
	void createBoardReply(BoardReplyEntity entity);
	
	@Update("")
	void updateBoardReply(BoardReplyEntity entity);
}
