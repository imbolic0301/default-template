package com.example.web.dto;

import java.time.ZonedDateTime;
import java.util.List;

import com.example.domain.BoardInquiryEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class BoardInquiryDTO {
	
	@Getter
	@Setter
	@ToString
	public static class InquiryRequest {
		private Long userId;
		private String title;
		private String content;
		private String answer;
		private Long parentId;
		private Long originId;
		
		public BoardInquiryEntity of() {
			return BoardInquiryEntity
					.builder()
					.userId(this.userId)
					.title(this.title)
					.content(this.content)
					.answer(this.answer)
					.parentId(this.parentId)
					.originId(this.originId)
					.build();
		}
	}
	
	@Getter
	@ToString
	public static class InquiryResponse {
		private Long id;
		private Long userId;
		private String title;
		private String content;
		private String answer;
		private Long parentId;
		private Long originId;
		private Boolean isDel;
		private ZonedDateTime regDt;
		private ZonedDateTime modDt; 
		
		private List<InquiryResponse> subInquiryList;
		
		public InquiryResponse(BoardInquiryEntity entity) {
			this.id = entity.getId();
			this.userId = entity.getUserId();
			this.title = entity.getTitle();
			this.content = entity.getContent();
			this.answer = entity.getAnswer();
			this.parentId = entity.getParentId();
			this.originId = entity.getOriginId();
			this.regDt = entity.getRegDt();
			this.modDt = entity.getModDt();
		}
		
		public void setSubInquiryList(List<InquiryResponse> list) {
			this.subInquiryList = list;
		}
	}
	
	
}
