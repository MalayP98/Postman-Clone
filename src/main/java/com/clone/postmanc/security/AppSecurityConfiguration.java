package com.clone.postmanc.security;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppSecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Bean
  public PasswordEncoder defaultPasswordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  @Override
  public void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.authorizeRequests().anyRequest().authenticated();
    httpSecurity.formLogin().disable();
    httpSecurity.httpBasic();
    httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

}
