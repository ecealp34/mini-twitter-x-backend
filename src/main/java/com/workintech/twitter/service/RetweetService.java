package com.workintech.twitter.service;

import com.workintech.twitter.entity.Like;
import com.workintech.twitter.entity.Retweet;

public interface RetweetService {
    Retweet findById(int id);
    Retweet save(Retweet retweet);
    Retweet delete(int id);
}
