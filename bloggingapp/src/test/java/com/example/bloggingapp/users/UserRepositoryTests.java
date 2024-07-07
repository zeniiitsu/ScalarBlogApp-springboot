package com.example.bloggingapp.users;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTests {
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	@Order(1)
	void can_create_users() {
		UserEntity user  = UserEntity.builder().username("Bhavesh").email("bhaveshainkar1234@gmail.com").build();
		
		userRepository.save(user);
		
	}
	
	@Test
	@Order(2)
	void can_find_users() {
		UserEntity user  = UserEntity.builder().username("Bhavesh").email("bhaveshainkar1234@gmail.com").build();
		
		userRepository.save(user);
		
		var users = userRepository.findAll();
		Assertions.assertEquals(1, users.size());
	}

}
