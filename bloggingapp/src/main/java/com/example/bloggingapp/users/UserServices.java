package com.example.bloggingapp.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bloggingapp.users.dtos.CreateUserRequest;

@Service
public class UserServices {
	
	@Autowired
	private UserRepository userRepository;
	
	public UserEntity createUser(CreateUserRequest req) {
		var newUser = UserEntity.builder().username(req.getUsername()).email(req.getEmail()).build();
		
		return userRepository.save(newUser);
	}
	
	
	
	public UserEntity getUser(String username) {
		return userRepository.findByUsername(username).orElseThrow(()-> new UserNotFoundException(username));
	}
	
	
	
	public UserEntity getUser(Long userid) {
		return userRepository.findById(userid).orElseThrow(()-> new UserNotFoundException(userid));
	}
	
	
	
	public UserEntity loginUser(String username, String password) {
		var user = userRepository.findByUsername(username).orElseThrow(()-> new UserNotFoundException(username));
		if(user==null) {
			throw new UserNotFoundException(username);
		}
		
		// TODO match password
		return user;
	}
	
	public static class UserNotFoundException extends IllegalArgumentException{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public UserNotFoundException(String username) {
			super("user with username : " + username + " not found");
		}
		
		public UserNotFoundException(Long userid) {
			super("user with userid : " + userid + " not found");
		}
	}
	
	

}
