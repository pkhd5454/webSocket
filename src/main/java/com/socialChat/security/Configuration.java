package com.socialChat.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.session.InvalidSessionStrategy;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class Configuration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	CustomUsersService service;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.formLogin().loginPage("/socialChat/login").defaultSuccessUrl("/socialChat/home");
		
		http.exceptionHandling().accessDeniedPage("/socialChat/accessDenied");
		
		http.logout().logoutUrl("/socialChat/logout").invalidateHttpSession(true).logoutSuccessUrl("/socialChat/home");
		
		http.rememberMe().key("asd").userDetailsService(service);
		
		http.sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true).sessionRegistry(sessionRegistry());
	}
	
	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}
}
