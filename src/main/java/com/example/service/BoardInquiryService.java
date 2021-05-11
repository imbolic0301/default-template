package com.example.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.BoardInquiryEntity;
import com.example.mapper.BoardInquiryMapper;
import com.example.web.dto.BoardInquiryDTO.InquiryResponse;

@Service
public class BoardInquiryService {
	
	@Autowired
	private BoardInquiryMapper boardInquiryMapper;
	
	
	@Transactional(readOnly = true)
	public InquiryResponse getBoardInquiryById(Long id) {
		InquiryResponse result = new InquiryResponse(boardInquiryMapper.getBoardInquiryListById(id));
		if(result != null) {
			result.setSubInquiryList(
				Optional.ofNullable(boardInquiryMapper.getBoardInquiryListByOriginIdList(result.getOriginId())).orElseGet(Collections::emptyList)
					.stream()
					.map(e -> new InquiryResponse(e))
					.collect(Collectors.toList())
			);
			
		}
		
		return result;
	}
	
	@Transactional(readOnly = true)
	public List<InquiryResponse> getBoardInquiryListByType(Integer type, Integer page, Integer size) {
		return Optional.ofNullable(boardInquiryMapper.getBoardInquiryListByType(type, (page-1) * size, size)).orElseGet(Collections::emptyList)
					.stream()
					.map(e -> new InquiryResponse(e))
					.collect(Collectors.toList());
	}
	
	@Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void createBoardInquiry(BoardInquiryEntity entity) throws Exception {
		boardInquiryMapper.createBoardInquiry(entity);
	}
	
	@Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateBoardInquiry(BoardInquiryEntity entity) throws Exception {
		boardInquiryMapper.updateBoardInquiry(entity);
	}
	
	@Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateAnswerBoardInquiry(BoardInquiryEntity entity) throws Exception {
		boardInquiryMapper.updateAnswer(entity);
	}
	
}
