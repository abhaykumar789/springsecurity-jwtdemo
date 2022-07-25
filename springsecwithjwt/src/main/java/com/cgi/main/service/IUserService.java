package com.cgi.main.service;

import java.util.Optional;

import com.cgi.main.model.User;

public interface IUserService {
	Integer  saveUser(User user);
	Optional<User> findByUsername(String userName);
	
}
