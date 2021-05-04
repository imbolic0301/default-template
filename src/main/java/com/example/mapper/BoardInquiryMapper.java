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

	@Select("")
	BoardInquiryEntity getBoardInquiryListById(@Param("id") Integer id);
	
	@Select("")
	List<BoardInquiryEntity> getBoardInquiryListByType(@Param("type") Integer type);
	
	//차후 조회에서 특정 질문에 대해 n개만큼 계층을 가져갈 경우 사용하는 쿼리
	@Select("")
	List<BoardInquiryEntity> getBoardInquiryListByParentIdList(List<Long> parentIdList);
	
	@Insert("")
	void createBoardInquiry(BoardInquiryEntity entity);
	
	@Update("")
	void updateAnswer(BoardInquiryEntity entity);
	
	@Update("")
	void remove(Long id);

	
}
