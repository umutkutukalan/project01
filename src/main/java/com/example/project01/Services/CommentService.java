package com.example.project01.Services;

import com.example.project01.Entities.Comment;
import com.example.project01.Repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }
}
