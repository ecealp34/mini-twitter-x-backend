package com.workintech.twitter.service;

import com.workintech.twitter.entity.Like;
import com.workintech.twitter.entity.Tweet;

import java.util.List;

public interface LikeService {
    List<Like> findAll();
    Like findById(int id);
    Like save(Like like);
    Like delete(int id);
}
