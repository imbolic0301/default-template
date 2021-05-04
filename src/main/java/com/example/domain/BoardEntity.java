package com.example.domain;

import java.time.ZonedDateTime;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class BoardEntity {
	private Long id;
	private Integer infoId;
	private Long userId;
	private Integer category;
	private String title;
	private String content;
	private Boolean isDel;
	private ZonedDateTime regDt;
	private ZonedDateTime modDt; 
	
	private List<BoardReplyEntity> replyList;
}
