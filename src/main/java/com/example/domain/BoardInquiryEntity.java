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
public class BoardInquiryEntity {
	private Long id;
	private Long userId;
	private String title;
	private String content;
	private String answer;
	private Long parentId;
	private Boolean isDel;
	private ZonedDateTime regDt;
	private ZonedDateTime modDt; 
	
	private List<BoardInquiryEntity> subInquiryList;
}
