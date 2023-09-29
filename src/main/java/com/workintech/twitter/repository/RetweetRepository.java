package com.workintech.twitter.repository;


import com.workintech.twitter.entity.Retweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RetweetRepository extends JpaRepository<Retweet, Integer> {
    @Query("SELECT r FROM Retweet r WHERE r.id = :id")
    Optional<Retweet> findById(int id);
}
