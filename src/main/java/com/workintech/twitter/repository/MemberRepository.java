package com.workintech.twitter.repository;

import com.workintech.twitter.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    @Query("SELECT m FROM Member m WHERE m.email = :email")
    Optional<Member> findMemberByEmail(String email);
    @Query("SELECT m FROM Member m WHERE m.id = :id")
    Optional<Member> findById(int id);
}
