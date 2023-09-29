package com.workintech.twitter.repository;

import com.workintech.twitter.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {
    @Query("SELECT r FROM Reply r WHERE r.id = :id")
    Optional<Reply> findById(int id);
}
