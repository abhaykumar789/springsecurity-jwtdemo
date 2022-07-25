package com.cgi.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cgi.main.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
	Optional<User> findByUsername(String userName);
}
