package com.example.web.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class SignDTO {
	
	/**
	 * naver 
	 */

	@Getter
	@Setter
	@ToString
	public static class NaverInfoRequest {
		private String accessToken;
	}
	
	
	@Getter
	@Setter
	@ToString
	public static class NaverUserInfoResponse {
		private NaverUserInfoDetail response;
		private String resultcode;
		private String message;
	}
	
	@Getter
	@Setter
	@ToString
	public static class NaverUserInfoDetail {
		private String email;
		private String nickname;
		private String gender; 		// F, M
		private String birthday;	// 10-01
		private String birthyear;	// 1900
		private String mobile;
		private String id;
	}
	
	
}
