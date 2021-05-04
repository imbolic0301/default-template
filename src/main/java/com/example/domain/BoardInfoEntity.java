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
public class BoardInfoEntity {
	private Integer id;
	private String name;
	private Boolean isAdminOnly;
	private Boolean isReadOnly;
	private Boolean isUse;
	private ZonedDateTime regDt;
	private ZonedDateTime modDt; 
}
