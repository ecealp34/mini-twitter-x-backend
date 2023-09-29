package com.workintech.twitter.service;

import com.workintech.twitter.entity.Account;
import com.workintech.twitter.entity.Member;

import java.util.List;

public interface MemberService {
    List<Member> findAll();
    Member findById(int id);

    Member save(Member member);
    Member delete(int id);
}
