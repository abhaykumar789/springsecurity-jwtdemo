package com.cgi.main.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
					//auth.inMemoryAuthentication()
		       // Store the  data(login username and password ,roles) RAM
						//Testing
				//auth.jdbcAuthentication();
					//Store data in db and validate the  data/login when
				//ever request will come (Based on queries)
				//auth.userDetailsService(null)
				//Store  data in db and validate data/login when req come
		 			//It will work based on ORM(Spring Data JPA)
	
				auth.inMemoryAuthentication()
				.withUser("RAJU")
				.password("{noop}123")
				.authorities("ADMIN");
				
				auth.inMemoryAuthentication()
				.withUser("SUNIL")
				.password("{noop}123")
				.authorities("MANAGER");
				
				
	}
		
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		 
	}
}
