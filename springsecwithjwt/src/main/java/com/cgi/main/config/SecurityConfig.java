package com.cgi.main.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cgi.main.aspect.InvalidUserAuthEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailService;
	@Autowired
	private BCryptPasswordEncoder encoder;
	@Autowired
	private InvalidUserAuthEntryPoint authenticationEntryPoint;
	
	
	@SuppressWarnings("deprecation")
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	   @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		   		 auth.userDetailsService(userDetailService)
		   		 .passwordEncoder(encoder);
		   		 
	}
	   
	@Override
	protected void configure(HttpSecurity http) throws Exception {
				http
				.csrf().disable()
				.authorizeRequests()
				.antMatchers("/user/save","/user/login").permitAll()
				.anyRequest()
				.authenticated()
				.and()
				.exceptionHandling()
				.authenticationEntryPoint(authenticationEntryPoint)
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				;
	}
	
	
}
