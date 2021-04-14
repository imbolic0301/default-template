package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.web.interceptor.TestInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	// 호출 경로 기반으로 인터셉터 적용 경로 지정
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		TestInterceptor testInterceptor = new TestInterceptor();
		
		registry
			.addInterceptor(testInterceptor)
			.addPathPatterns("/api/**");
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**").allowedMethods("*");
	}

}