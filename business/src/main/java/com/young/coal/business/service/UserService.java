package com.young.coal.business.service;

import com.young.coal.business.model.RequestParams;
import com.young.coal.business.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public User login(User user) {

        Query query = new Query(Criteria.where("userName").is(user.getUserName()));
        query.addCriteria(Criteria.where("password").is(user.getPassword()));

        User result = mongoTemplate.findOne(query, User.class);

        return result;
    }

    public Boolean changePassword(RequestParams requestParams, User user) {

        Query query = new Query(Criteria.where("userName").is(user.getUserName()));
        query.addCriteria(Criteria.where("password").is(requestParams.getOldPassword()));

        User result = mongoTemplate.findOne(query, User.class);

        if(result != null){
            Update update = new Update().set("password", requestParams.getNewPassword());
            mongoTemplate.updateFirst(query, update, User.class);
            return true;
        } else {
            return false;
        }
    }

    public void createUser(User user) {
        mongoTemplate.save(user);
    }
}