package com.workintech.twitter.service;


import com.workintech.twitter.entity.Like;
import com.workintech.twitter.entity.Retweet;
import com.workintech.twitter.repository.RetweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RetweetServiceImpl implements RetweetService {

    private RetweetRepository retweetRepository;
    @Autowired
    public RetweetServiceImpl(RetweetRepository retweetRepository) {
        this.retweetRepository = retweetRepository;
    }

    @Override
    public Retweet findById(int id) {
        Optional<Retweet> retweet = retweetRepository.findById(id);
        if(retweet.isPresent()) {
            return retweet.get();
        }
        return null;
    }

    @Override
    public Retweet save(Retweet retweet) {
        return retweetRepository.save(retweet);
    }

    @Override
    public Retweet delete(int id) {
        Optional<Retweet> retweet = retweetRepository.findById(id);
        if(retweet.isPresent()) {
            retweetRepository.delete(retweet.get());
        }
        return null;
    }
}
