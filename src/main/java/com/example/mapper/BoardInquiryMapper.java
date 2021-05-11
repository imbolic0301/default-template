package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.example.domain.BoardInquiryEntity;

@Repository
public interface BoardInquiryMapper {

	@Select("SELECT * FROM board_inquiry WHERE id = #{id}")
	BoardInquiryEntity getBoardInquiryListById(@Param("id") Long id);
	
	@Select(""
			+ "	SELECT * FROM board_inquiry "
			+ "	WHERE type = #{type} AND parent_id IS NULL "
			+ "	ORDER BY id desc "
			+ "	LIMIT #{skip}, #{size}")
	List<BoardInquiryEntity> getBoardInquiryListByType(@Param("type") Integer type,
			@Param("skip") Integer skip, @Param("size") Integer size);
	
	@Select("SELECT * FROM board_inquiry WHERE origin_id = #{originId}  ORDER BY id asc")
	List<BoardInquiryEntity> getBoardInquiryListByOriginIdList(@Param("originId") Long parentIdList);
	
	@Insert("INSERT board_inquiry ("
			+ "	user_id, "
			+ "	titel, "
			+ "	content, "
			+ "	answer, "
			+ "	parentId, "
			+ "	originId "
			+ ") VALUES ("
			+ "	#{userId}, "
			+ "	#{titel}, "
			+ "	#{content}, "
			+ "	#{answer}, "
			+ "	#{parentId}, "
			+ "	#{originId} "
			+ ")")
	void createBoardInquiry(BoardInquiryEntity entity);
	
	@Update(""
			+ "UPDATE board_inquiry"
			+ "	SET"
			+ "		content = #{content}, content), "
			+ "		title = IFNULL(#{title}, title), "
			+ "		parent_id = #{parentId}, parent_id), "
			+ "		origin_id = #{originId}, origin_id), "
			+ "		answer = #{answer}, answer) "
			+ "	WEHRE id = #{id}"
			)
	void updateBoardInquiry(BoardInquiryEntity entity);
	
	@Update(""
			+ "UPDATE board_inquiry"
			+ "	SET"
			+ "		title = IFNULL(#{title}, title), "
			+ "		content = #{content}, content), "
			+ "		answer = #{answer}, content) "
			+ "	WEHRE id = #{id}"
			)
	void updateAnswer(BoardInquiryEntity entity);
	
	@Update("UPDATE board_inquiry SET is_del = 1 AND mod_dt = now()")
	void removeBoardInquiry(Long id);
	
}
