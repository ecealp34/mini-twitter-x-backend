package com.workintech.twitter.service;

import com.workintech.twitter.entity.Reply;
import com.workintech.twitter.entity.Retweet;

public interface ReplyService {

    Reply findById(int id);
    Reply save(Reply reply);
    Reply delete(int id);
}
