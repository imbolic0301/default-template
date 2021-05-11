package com.example.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.BoardReplyEntity;
import com.example.mapper.BoardReplyMapper;
import com.example.web.dto.BoardDTO;
import com.example.web.dto.BoardDTO.BoardReplyResponse;

@Service
public class BoardReplyService {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private BoardReplyMapper boardReplyMapper;
	
	
	@Transactional(readOnly = true)
	public List<BoardReplyResponse> getBoardReplyListByBoardId(Long boardId, Integer page, Integer size) {
		List<BoardReplyResponse> originReplyList = 
				Optional.ofNullable(boardReplyMapper.getOriginReplyListByBoardId(boardId, (page-1)*size, size)).orElseGet(Collections::emptyList)
					.stream()
					.map(e -> new BoardDTO.BoardReplyResponse(e))
					.collect(Collectors.toList());

		if(originReplyList.size() > 0) {
			List<BoardReplyEntity> subReplyList = boardReplyMapper.getSubReplyList(boardId);
			Map<Long, List<BoardReplyResponse>> subReplyMap = new HashMap<>();
			if(subReplyList.size() > 0) {
				for(BoardReplyEntity entity : subReplyList) {
					List<BoardReplyResponse> list = subReplyMap.get(entity.getOriginId());
					if(list == null) {
						list = new LinkedList<>();
					}
					list.add(new BoardReplyResponse(entity));
				}
			}
			for(BoardReplyResponse res : originReplyList) {
				res.setSubReplyList(subReplyMap.get(res.getOriginId()));
			}
		}
		return originReplyList;
	}
	
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
