package com.example.service;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.BoardInquiryEntity;
import com.example.mapper.BoardInquiryMapper;

@Service
public class BoardInquiryService {
	
	@Autowired
	private BoardInquiryMapper boardInquiryMapper;
	
	
	@Transactional(readOnly = true)
	public BoardInquiryEntity getBoardInquiryListById(Integer id) {
		return boardInquiryMapper.getBoardInquiryListById(id);
	}
	
	@Transactional(readOnly = true)
	public List<BoardInquiryEntity> getBoardInquiryListByType(Integer type) {
		List<BoardInquiryEntity> boardList = boardInquiryMapper.getBoardInquiryListByType(type);
		
		Map<Long, List<BoardInquiryEntity>> subInquiryMap = new LinkedHashMap<>();
		List<Long> idList = new LinkedList<>();
		for(BoardInquiryEntity entity : boardList) {
			idList.add(entity.getId());
		}
		
		Boolean flag = (idList.size() > 0) ? true : false ;
		while(flag) {
			List<BoardInquiryEntity> subInquiryList = boardInquiryMapper.getBoardInquiryListByParentIdList(idList);
			if(subInquiryList == null || subInquiryList.size() < 1) {
				flag = false;
			} else {
				idList.clear();
				for(BoardInquiryEntity entity : subInquiryList) {
					idList.add(entity.getId());
					List<BoardInquiryEntity> subList = subInquiryMap.get(entity.getParentId());
					if(subList == null || subList.size() < 1) {
						subList = new LinkedList<>();
					}
					subList.add(entity);
					subInquiryMap.put(entity.getParentId(), subList);
				}
				
				for(BoardInquiryEntity entity : boardList) {
					entity.setSubInquiryList(subInquiryMap.get(entity.getId()));
				}
				
			}
		}
		
		return boardList;
	}
	
	@Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void createBoard(BoardInquiryEntity entity) throws Exception {
		boardInquiryMapper.createBoardInquiry(entity);
	}
	
	@Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void answerBoardInquiry(BoardInquiryEntity entity) throws Exception {
		boardInquiryMapper.updateAnswer(entity);
	}
}
