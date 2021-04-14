package com.example.web.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExceptionDTO {
	private int status;
	private String message;
	
	@Builder
	public ExceptionDTO(int status, String message) {
		this.message = message;
		this.status = status;
	}
}
