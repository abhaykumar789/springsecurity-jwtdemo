package com.cgi.main.config;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cgi.main.service.UserServiceImpl;
import com.cgi.main.util.JwtUtil;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwt;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	private UserServiceImpl userService;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
			System.out.println("Hello World");
			System.out.println(request.getMethod()+"<--");
			System.out.println(request.getServletPath()+"<---");
			System.out.println("username:"+request.getParameter("username"));
			System.out.println("password:"+request.getParameter("password"));
			String token = request.getHeader("Authorization");
			System.out.println(token+"<----");
			if(token!=null) {
				// I want to do some validation
				String username=this.jwt.getUserName(token.substring(7));
				System.out.println("<"+username+">");
				/*UserDetails ud=this.userDetailsService.loadUserByUsername(username);
				System.out.println("line42:"+ud);
				boolean b=this.jwt.validateToken(token, ud.getUsername());
				System.out.println(b);
				*/
				UserDetails ud=this.userDetailsService.loadUserByUsername(username);
				String username1=ud.getUsername();
				String password=ud.getPassword();
					
				Collection<? extends GrantedAuthority> s=ud.getAuthorities();
				UsernamePasswordAuthenticationToken authToken  = 
						new UsernamePasswordAuthenticationToken(
								 username1, 
								password,s);
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
				
				/*Optional<User>  op=this.userService.findByUsername(username);
				if(op.isPresent()) {
						User us=op.get();
						Collection<String> set=us.getRoles().stream().collect(Collectors.toSet());
						Collection<GrantedAuthority> c=new ArrayList();
						Iterator<String> it=set.iterator();
						while(it.hasNext()) {
							c.add(new SimpleGrantedAuthority(it.next()));
						}
					//boolean b=this.jwt.validateToken(token, us.getUsername());
					//System.out.println(b);
						UsernamePasswordAuthenticationToken authToken  = 
								new UsernamePasswordAuthenticationToken(
										 us.getUsername(), 
										us.getPassword(),c);
						authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						SecurityContextHolder.getContext().setAuthentication(authToken);
				}*/
				
				 
			}
			filterChain.doFilter(request, response);
			
	}

}
