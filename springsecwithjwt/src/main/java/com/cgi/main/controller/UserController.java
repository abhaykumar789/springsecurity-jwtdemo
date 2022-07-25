package com.cgi.main.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.main.aspect.UserNotFoundException;
import com.cgi.main.model.User;
import com.cgi.main.model.UserRequest;
import com.cgi.main.model.UserResponse;
import com.cgi.main.service.UserServiceImpl;
import com.cgi.main.util.JwtUtil;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwt;
	
	@PostMapping("/save")
	public ResponseEntity<String> saveUser(@RequestBody User user){
		  int id=this.userService.saveUser(user);
		  return ResponseEntity.ok("User ID is:"+id);
	}
	
	//2. Validate the  Uesr and Generate the 
	@PostMapping("/login")
	public ResponseEntity<UserResponse> loginUser(@RequestBody UserRequest request)
	throws UserNotFoundException{
		System.out.println("loginUser:");
	  /* Authentication auth=this.authenticationManager
		.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		System.out.println(auth.getDetails()+"<=====");
		//If it is Authentication then only it will generate the token  either  wise it will throw the  exception
		*/
		Optional<User>  op=this.userService.findByUsername(request.getUsername());
		if(op.isEmpty()) {
			throw  new UserNotFoundException("USER_NOT_EXITS");
		}else {
		String token =this.jwt.generateToken(request.getUsername());
		String message ="SUCCESSFULLY-GENERATE-TOKEN";
		return ResponseEntity.ok(new UserResponse(token,message));
		}
	}
}
