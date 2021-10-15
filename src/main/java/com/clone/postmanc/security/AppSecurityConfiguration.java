package com.clone.postmanc.security;

import com.clone.postmanc.users.UserService;
import com.clone.postmanc.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppSecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserService userService;

  @Bean
  public PasswordEncoder defaultPasswordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  @Override
  public void configure(AuthenticationManagerBuilder authenticationManagerBuilder)
      throws Exception {
    authenticationManagerBuilder.userDetailsService(userService);
  }

  @Override
  public void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.authorizeRequests().antMatchers(AppConstants.PUBLIC_URLS).permitAll();
    httpSecurity.authorizeRequests().anyRequest().hasAnyRole(AppConstants.ALL_ROLES);
    httpSecurity.formLogin().disable();
    httpSecurity.httpBasic();
    httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    httpSecurity.csrf().disable();
  }
}
