package com.workintech.twitter.repository;

import com.workintech.twitter.entity.Member;
import com.workintech.twitter.entity.Tweet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TweetRepositoryTest {
    private TweetRepository tweetRepository;
    @Autowired
    public TweetRepositoryTest(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    @BeforeEach
    void setUp() {
        Tweet tweet = new Tweet();
        Timestamp time = Timestamp.valueOf(LocalDateTime.now());
        tweet.setPost("Hello");
        tweet.setCreatedAt(time);
        Member member = new Member();
        member.setName("Mert");
        member.setEmail("mert@gmail.com");
        member.setBirthDate("1995-06-24");
        member.setPassword("helloo");
        tweet.setMember(member);

        List<Tweet> tweets = new ArrayList<>();
        tweets.add(tweet);
        tweetRepository.save(tweet);

    }

    @AfterEach
    void tearDown() {
        tweetRepository.deleteAll();
    }

    @Test
    void findById() {
        int notFoundedId = -2;
        Optional<Tweet> tweet = tweetRepository.findById(notFoundedId);
        assertEquals(Optional.empty(), tweet);

    }
}