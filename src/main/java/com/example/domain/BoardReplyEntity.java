package com.example.domain;

import java.time.ZonedDateTime;

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
public class BoardReplyEntity {
	private Long id;
	private Long userId;
	private Long boardId;
	private Long parentId;
	private Long originId;
	private String content;
	private Boolean isDel;
	private ZonedDateTime regDt;
	private ZonedDateTime modDt;
}
