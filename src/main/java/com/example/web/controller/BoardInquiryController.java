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
import com.example.service.BoardInquiryService;
import com.example.web.dto.BoardInquiryDTO;

@RestController
@RequestMapping("/api/board/inquiry")
public class BoardInquiryController {
		
	@Autowired
	private BoardInquiryService boardInquiryService;
	
	
	@GetMapping("/list")
	public ResponseEntity<?> getBoardInquiryListByInfoId(
			@RequestParam(name = "type", defaultValue = "1") Integer type,
			@RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "size", defaultValue = "10") Integer size
			) throws Exception{
		return ResponseEntity.ok(boardInquiryService.getBoardInquiryListByType(type, page, size));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getBoardInquiryById(@PathVariable("id") Long id) throws Exception {
		return ResponseEntity.ok(boardInquiryService.getBoardInquiryById(id));
	}
	
	@PostMapping
	public ResponseEntity<?> createBoardInquiry(@RequestBody BoardInquiryDTO.InquiryRequest request) throws Exception {
		boardInquiryService.createBoardInquiry(request.of());
		return ResponseEntity.ok(EnvConstants.OK);
	}
	
	@PatchMapping()
	public ResponseEntity<?> updateBoard(@RequestBody BoardInquiryDTO.InquiryRequest request) throws Exception {
		boardInquiryService.updateBoardInquiry(request.of());
		return ResponseEntity.ok(EnvConstants.OK);
	}
	
	@PatchMapping("/answer")
	public ResponseEntity<?> updateAnswerBoard(@RequestBody BoardInquiryDTO.InquiryRequest request) throws Exception {
		boardInquiryService.updateAnswerBoardInquiry(request.of());
		return ResponseEntity.ok(EnvConstants.OK);
	}
}
