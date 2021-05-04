package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.example.domain.BoardInfoEntity;

@Repository
public interface BoardInfoMapper {
	
	//로컬 캐시 갱신용도
	@Select("SELECT * FROM board_info WHERE is_use = 1")
	List<BoardInfoEntity> getBoardInfoList();
	
}
