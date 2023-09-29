package com.workintech.twitter.controller;

import com.workintech.twitter.entity.*;
import com.workintech.twitter.exceptions.TweetException;
import com.workintech.twitter.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tweet")
@CrossOrigin
public class TweetController {
    private TweetService tweetService;
    private MemberService memberService;
    private LikeService likeService;
    private RetweetService retweetService;
    private ReplyService replyService;
    @Autowired
    public TweetController(TweetService tweetService, MemberService memberService, LikeService likeService, RetweetService retweetService, ReplyService replyService) {
        this.tweetService = tweetService;
        this.memberService = memberService;
        this.likeService = likeService;
        this.retweetService = retweetService;
        this.replyService = replyService;
    }
    @GetMapping("/")
    public List<Tweet> findAll() {
        return tweetService.findAll();
    }
    @GetMapping("/{id}")
    public Tweet findById(@PathVariable int id) {
        return tweetService.findById(id);
    }
    @PostMapping("/")
    public Tweet save(@RequestBody Tweet tweet, @PathVariable int id) {
        Member member = memberService.findById(id);
        tweet.setMember(member);
        return tweetService.save(tweet);
    }
    @PostMapping("/{like}/{id}")
    public Tweet savedLike(@RequestBody Tweet tweet, @PathVariable int id) {
        Member member = memberService.findById(id);
        Like like = likeService.findById(id);
        tweet.setMember(member);
        like.setTweet(tweet);
        return tweetService.save(tweet);
    }

    @PostMapping("/{retweet}/{id}")
    public Tweet savedRetweet(@RequestBody Tweet tweet, @PathVariable int id) {
        Retweet retweet = retweetService.findById(id);
        retweet.setTweet(tweet);
        return tweetService.save(tweet);
    }

    @PostMapping("/{reply}/{id}")
    public Tweet savedReply(@RequestBody Tweet tweet, @PathVariable int id) {
        Reply reply = replyService.findById(id);
        reply.setTweet(tweet);
        return tweetService.save(tweet);
    }

    @PutMapping("/{id}")
    public Tweet put(@RequestBody Tweet tweet, @PathVariable int id) {
        Tweet foundTweet = tweetService.findById(id);
        if(foundTweet != null) {
            tweet.setId(id);
            return tweetService.save(tweet);
        }
        throw new TweetException("Tweet with given id is not exist: " + id, HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    public Tweet delete(@PathVariable int id) {
        return tweetService.delete(id);
    }

    @DeleteMapping("/{like}/{id}")
    public Tweet deletedLike(@PathVariable int id) {
        likeService.delete(id);
        return tweetService.delete(id);
    }

    @DeleteMapping("/{reply}/{id}")
    public Tweet deletedReply(@PathVariable int id) {
        replyService.delete(id);
        return tweetService.delete(id);
    }
}
