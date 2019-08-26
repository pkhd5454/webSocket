package com.socialChat;

import javax.servlet.http.HttpSessionListener;

import org.springframework.context.annotation.Bean;

public class Application {
	@Bean
	public HttpSessionListener httpSessionListener() {
		return new SessionListener();
	}
}
