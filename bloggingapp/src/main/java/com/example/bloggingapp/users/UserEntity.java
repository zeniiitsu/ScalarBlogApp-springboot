package com.example.bloggingapp.users;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name  = "my_user")
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(nullable = false)
	private Long id;
	
	@Column(nullable = false)
	@NonNull
	private String username;
	
	@Column(nullable = false)
	@NonNull
	private String email;
	
	@Nullable
	private String bio;
	
	@Nullable
	private String image;
	
	
	
}
