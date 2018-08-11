package com.young.coal.business.service;

import com.young.coal.business.model.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    public ResponseData query(User user) {

        Query query = new Query();

        if(StringUtils.isNotEmpty(user.getUserName())){
            query.addCriteria(Criteria.where("userName").regex(user.getUserName()));
        }

        Pageable pageable = new PageRequest(user.getPage(), user.getPageSize());
        query.with(pageable);
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC,"createDate")));

        List<User> users = mongoTemplate.find(query, User.class);

        ResponseData responseData = new ResponseData();
        responseData.setTotal(mongoTemplate.count(query, User.class));
        responseData.setPage(user.getPage());

        responseData.setData(users);

        return responseData;
    }

    public void update(User user) {

        Update update = new Update();
        update.set("userName", user.getUserName());
        update.set("password", user.getPassword());
        update.set("role", user.getRole());
        update.set("updateDate", new Date());
        update.setOnInsert("createDate", new Date());

        Query query = new Query(Criteria.where("userName").is(user.getUserName()));

        mongoTemplate.upsert(query, update, User.class);
    }
}