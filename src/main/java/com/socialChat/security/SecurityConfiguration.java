package com.socialChat.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
  private final CustomUsersService service;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().mvcMatchers("/h2-console/*").permitAll().anyRequest().permitAll();

    http.csrf().disable();

    http.headers().frameOptions().disable();

    http.httpBasic();

    http.formLogin().loginPage("/socialChat/login").defaultSuccessUrl("/socialChat/home");

    http.exceptionHandling().accessDeniedPage("/socialChat/accessDenied");

    http.logout()
        .logoutUrl("/socialChat/logout")
        .invalidateHttpSession(true)
        .logoutSuccessUrl("/socialChat/home");

    http.rememberMe().key("remember").userDetailsService(service);

    http.sessionManagement().maximumSessions(1).sessionRegistry(sessionRegistry());
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication().withUser("user1").password("{noop}123").roles("USER");
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public SessionRegistry sessionRegistry() {
    return new SessionRegistryImpl();
  }
}
