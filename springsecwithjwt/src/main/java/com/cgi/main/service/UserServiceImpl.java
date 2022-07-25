package com.cgi.main.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cgi.main.model.User;
import com.cgi.main.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService,UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	public Integer saveUser(User user) {
	
		//Conversion  is done
		user.setPassword(this.encoder.encode(user.getPassword()));
		//Store in database and return the primary key
		return this.userRepository.save(user).getId();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
				Optional<User> op=this.userRepository.findByUsername(username);
				System.out.println("op:"+op+"<-----");
				if(op.isEmpty()) {
					System.out.println("Throw the  exception:<----UsernameNotFoundException");
					throw new UsernameNotFoundException("USER-NOT-EXITS");
				}
				User us=op.get();
				System.out.println(us+"<--------------");
				
				return new org.springframework.security.core.userdetails.User
						(username,
						 us.getPassword(),
						us.getRoles()
						.stream()
						.map(role->new SimpleGrantedAuthority(role)).collect(Collectors.toList()));
				
	}

	@Override
	public Optional<User> findByUsername(String userName) {
			return this.userRepository.findByUsername(userName);
	}

	
}
