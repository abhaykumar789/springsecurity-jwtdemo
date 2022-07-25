package com.cgi.main;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class EmsappApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(EmsappApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//Convertion of Password in Encoder format
		BCryptPasswordEncoder b=new BCryptPasswordEncoder();
		String ep=b.encode("RAJU");
		System.out.println(ep);//$2a$10$/LiJoYryvEyS3qtJZY76aet7ejVQbVqv/3Zg5KBXakGYRDzL5XILu
		/*
		   DataBase:
		      username  password 														role
		      raju      $2a$10$/LiJoYryvEyS3qtJZY76aet7ejVQbVqv/3Zg5KBXakGYRDzL5XILu    ADMIN
		 */
	}

}
