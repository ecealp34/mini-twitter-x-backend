package com.workintech.twitter.service;

import com.workintech.twitter.entity.Member;
import com.workintech.twitter.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MemberServiceImpl implements MemberService{
    private MemberRepository memberRepository;
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }


    @Override
    public Member findById(int id) {
        Optional<Member> member = memberRepository.findById(id);
        if(member.isPresent()) {
            return member.get();
        }
        return null;
    }

    @Override
    public Member save(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member delete(int id) {
        Optional<Member> member = memberRepository.findById(id);
        if(member.isPresent()) {
            memberRepository.delete(member.get());
        }
        return null;
    }


}
