package com.example.web.dto;

import java.time.ZonedDateTime;
import java.util.List;

import com.example.domain.BoardEntity;
import com.example.domain.BoardReplyEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class BoardDTO {
	
	@Getter
	@Setter
	@ToString
	public static class BoardRequest {
		private Long id;
		private Integer infoId;
		private Long userId;
		private Integer category;
		private String title;
		private String content;
		
		public BoardEntity of() {
			return BoardEntity
						.builder()
						.id(this.id)
						.infoId(this.infoId)
//						.userId(this.userId)
						.userId(1L)
						.category(this.category)
						.title(this.title)
						.content(this.content)
						.build();
		}
	}
	
	@Getter
	@Setter
	@ToString
	public static class BoardReplyRequest {
		private Long id;
		private Long userId;
		private Long boardId;
		private Long parentId;
		private Long originId;
		private String content;
		
		public BoardReplyEntity of() {
			return BoardReplyEntity
						.builder()
						.id(this.id)
						.userId(this.userId)
						.boardId(this.boardId)
						.parentId(this.parentId)
						.originId(this.originId)
						.content(this.content)
						.build();
		}
	}
	
	@Getter
	@ToString
	public static class BoardResponse {
		private Long id;
		private Integer infoId;
		private Long userId;
		private Integer category;
		private String title;
		private String content;
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "Asia/Seoul")
		private ZonedDateTime regDt;
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "Asia/Seoul")
		private ZonedDateTime modDt;
		
		public BoardResponse(BoardEntity entity) {
			if(entity == null) return;
			this.id = entity.getId();
			this.infoId = entity.getInfoId();
			this.userId = entity.getUserId();
			this.category = entity.getCategory();
			this.title = entity.getTitle();
			this.content = entity.getContent();
			this.regDt = entity.getRegDt();
			this.modDt = entity.getModDt();
		}
	}
	
	@Getter
	@ToString
	public static class BoardReplyResponse {
		private Long id;
		private Long userId;
		private Long boardId;
		private Long parentId;
		private Long originId;
		private String content;
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "Asia/Seoul")
		private ZonedDateTime regDt;
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "Asia/Seoul")
		private ZonedDateTime modDt;
		
		private List<BoardReplyResponse> subReplyList;
		
		public BoardReplyResponse(BoardReplyEntity entity) {
			if(entity == null) return;
			this.id = entity.getId();
			this.userId = entity.getUserId();
			this.boardId = entity.getBoardId();
			this.parentId = (entity.getParentId() == null) ? 0 : entity.getParentId();
			this.originId = entity.getOriginId();
			this.content = (entity.getIsDel()) ? "삭제된 댓글입니다." : entity.getContent();
			this.regDt = entity.getRegDt();
			this.modDt = entity.getModDt();
		}
		
		public void setSubReplyList(List<BoardReplyResponse> replyList) {
			this.subReplyList = replyList;
		}
	}
}
