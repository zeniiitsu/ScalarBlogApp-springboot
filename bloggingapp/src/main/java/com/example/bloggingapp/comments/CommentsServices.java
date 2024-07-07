package com.example.bloggingapp.comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentsServices {
	
	@Autowired
	private CommentsRepository commentsRepository;

}
