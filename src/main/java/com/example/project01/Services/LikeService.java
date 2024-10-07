package com.example.project01.Services;

import com.example.project01.Entities.Like;
import com.example.project01.Repositories.LikeRepository;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    private LikeRepository likeRepository;

    public LikeService(LikeRepository likeRepository){
        this.likeRepository = likeRepository;
    }
}
