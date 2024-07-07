package com.example.bloggingapp.users;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.bloggingapp.users.dtos.CreateUserRequest;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTests {
	@Autowired
	UserServices userService;
	
	@Test
	void can_create_users() {
		var user = this.userService.createUser(new CreateUserRequest("Bhavesh", "bha1234", "bhavesh@gmail.com"));
	
		Assertions.assertNotNull(user);
		Assertions.assertEquals("Bhavesh", user.getUsername());
	}
	
	
	
	
	
}
