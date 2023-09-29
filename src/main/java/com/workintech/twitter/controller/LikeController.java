package com.workintech.twitter.controller;

import com.workintech.twitter.entity.Like;
import com.workintech.twitter.entity.Member;
import com.workintech.twitter.entity.Tweet;
import com.workintech.twitter.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tweet/like")
public class LikeController {
    private LikeService likeService;
    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/{id}")
    public Like save(@RequestBody Tweet tweet, @PathVariable int id) {
        Like like = likeService.findById(id);
        like.setTweet(tweet);
        return likeService.save(like);
    }

    @DeleteMapping("/{id}")
    public Like deletedLike(@PathVariable int id) {
        Like like = likeService.findById(id);
        return likeService.delete(like.getId());
    }

}
