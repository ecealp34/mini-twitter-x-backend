package com.workintech.twitter.repository;

import com.workintech.twitter.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TweetRepository extends JpaRepository<Tweet, Integer> {
    @Query("SELECT t FROM Tweet t WHERE t.id = :id")
    Optional<Tweet> findById(int id);
}
