
package com.tomojo.config.web;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.LinkedList;
import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.tomojo.config.web",
		"com.tomojo.controllers" })
public class WebBeansConfig extends WebMvcConfigurerAdapter {

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		super.configureMessageConverters(converters);
		converters.add(mappingJackson2HttpMessageConverter());
	}

	@Bean
	public static ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return objectMapper;
	}

	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		List<MediaType> mediaTypes = new LinkedList<MediaType>();
		mediaTypes.add(MediaType.APPLICATION_JSON);
		jsonConverter.setSupportedMediaTypes(mediaTypes);
		jsonConverter.setObjectMapper(objectMapper());
		return jsonConverter;
	}

}
