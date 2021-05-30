package com.example.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.web.dto.SignDTO;
import com.example.web.dto.SignDTO.NaverInfoRequest;


@RestController
@RequestMapping("/sign")
public class SignController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@PostMapping("/naver")
	public ResponseEntity<?> naverLogin(@RequestBody NaverInfoRequest request) throws IOException {
		System.out.println("accessToken : ");
		System.out.println(request.getAccessToken());

        String header = "Bearer " + request.getAccessToken(); // Bearer 다음에 공백 추가
        String apiURL = "https://openapi.naver.com/v1/nid/me";
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", header);
        
        HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", header);
		

		HttpEntity<MultiValueMap<String, String>> apiRequest = new HttpEntity<>(headers);
		SignDTO.NaverUserInfoResponse result = restTemplate.exchange(apiURL, HttpMethod.GET, apiRequest,
				new ParameterizedTypeReference<SignDTO.NaverUserInfoResponse>() {
				}).getBody();
		
		String naverUserId = result.getResponse().getId();
		System.out.println("naverUserId : " + naverUserId);
		// UserEntity user = UserEntity.builder().naverId(naverUserId).build();
		System.out.println("result : " + result);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/kakao")
	public ResponseEntity<?> kakaoLoginTest(@RequestBody SignDTO.KakaoUserInfoRequest request) throws IOException {
		// JS SDK로 유저 정보를 확인해서 필요한 정보만 받아오면 끝! 필요한 파라미터는 https://developers.kakao.com/tool/rest-api/open/get/v2-user-me 참조!
		//
		System.out.println("request : " + request);
		return ResponseEntity.ok().build();
	}
	
}
