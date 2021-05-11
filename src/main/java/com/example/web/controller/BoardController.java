package com.example.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.constant.EnvConstants;
import com.example.service.BoardReplyService;
import com.example.service.BoardService;
import com.example.web.dto.BoardDTO;

@RestController
@RequestMapping("/api/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private BoardReplyService boardReplyService;
	
	
	@GetMapping("/list")
	public ResponseEntity<?> getBoardListByInfoId(
			@RequestParam("infoId") Integer infoId,
			@RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "size", defaultValue = "10") Integer size
			) throws Exception{
		return ResponseEntity.ok(boardService.getBoardListByInfoId(infoId, page, size));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getBoardById(@PathVariable("id") Long id) throws Exception {
		return ResponseEntity.ok(new BoardDTO.BoardResponse(boardService.getBoardById(id)));
	}
	
	@PostMapping
	public ResponseEntity<?> createBoard(@RequestBody BoardDTO.BoardRequest request) throws Exception {
		boardService.createBoard(request.of());
		return ResponseEntity.ok(EnvConstants.OK);
	}
	

	@PatchMapping
	public ResponseEntity<?> updateBoard(@RequestBody BoardDTO.BoardRequest request) throws Exception {
		boardService.updateBoard(request.of());
		return ResponseEntity.ok(EnvConstants.OK);
	}

	@GetMapping("/{id}/reply")
	public ResponseEntity<?> getBoardReplyByBoardId(
			@PathVariable("id") Long id,
			@RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "size", defaultValue = "10") Integer size
			) throws Exception {
		return ResponseEntity.ok(new BoardDTO.BoardResponse(boardService.getBoardById(id)));
	}
	
	@PostMapping("/reply")
	public ResponseEntity<?> createBoardReply(@RequestBody BoardDTO.BoardReplyRequest request) throws Exception {
		boardReplyService.createReply(request.of());
		return ResponseEntity.ok(EnvConstants.OK);
	}
	
	@PatchMapping("/reply")
	public ResponseEntity<?> updateBoardReply(@RequestBody BoardDTO.BoardReplyRequest request) throws Exception {
		boardReplyService.updateReply(request.of());
		return ResponseEntity.ok(EnvConstants.OK);
	}
	
}
