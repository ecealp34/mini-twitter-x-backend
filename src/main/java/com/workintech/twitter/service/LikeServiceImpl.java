package com.workintech.twitter.service;

import com.workintech.twitter.entity.Like;
import com.workintech.twitter.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService{

    private LikeRepository likeRepository;
    @Autowired
    public LikeServiceImpl(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @Override
    public List<Like> findAll() {
        return likeRepository.findAll();
    }

    @Override
    public Like findById(int id) {
        Optional<Like> like = likeRepository.findById(id);
        if(like.isPresent()) {
            return like.get();
        }
        return null;
    }

    @Override
    public Like save(Like like) {
        return likeRepository.save(like);
    }

    @Override
    public Like delete(int id) {
        Optional<Like> like = likeRepository.findById(id);
        if(like.isPresent()) {
            likeRepository.delete(like.get());
        }
        return null;
    }
}
