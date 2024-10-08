package com.example.project01.Requests;

import lombok.Data;

@Data
public class CommentCreateRequest {
    Long id;
    String title;
    String text;
    Long userId;
    Long postId;
}
