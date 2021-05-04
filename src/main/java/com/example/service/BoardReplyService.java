package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.BoardReplyEntity;
import com.example.mapper.BoardReplyMapper;

@Service
public class BoardReplyService {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private BoardReplyMapper boardReplyMapper;
	
	
	@Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void createReply(BoardReplyEntity entity) throws Exception {
		boardService.checkBoardExceptionById(entity.getBoardId());
		boardReplyMapper.createBoardReply(entity);
	}
	
	@Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateReply(BoardReplyEntity entity) throws Exception {
		boardService.checkBoardExceptionById(entity.getBoardId());
		boardReplyMapper.updateBoardReply(entity);
	}
	

}
