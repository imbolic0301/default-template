package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

//    @Bean
//    protected RestTemplate restTemplate(HttpComponentsClientHttpRequestFactory clientHttpRequestFactory) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//        converter.setObjectMapper(objectMapper);
//
//        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
//        restTemplate.getMessageConverters().add(converter);
//        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
//        
//        return restTemplate;
//    }
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
