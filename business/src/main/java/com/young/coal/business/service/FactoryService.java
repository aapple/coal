package com.young.coal.business.service;

import com.young.coal.business.model.CoalPrice;
import com.young.coal.business.model.Factory;
import com.young.coal.business.model.ResponseData;
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
import java.util.UUID;

/**
 * Created by yaobin on 2017/12/4.
 */
@Service
public class FactoryService {

    @Autowired
    MongoTemplate mongoTemplate;

    public ResponseData query(Factory factory) {

        Query query = new Query();

        if(StringUtils.isNotEmpty(factory.getFactoryName())){
            query.addCriteria(Criteria.where("factoryName").regex(factory.getFactoryName()));
        }

        Pageable pageable = new PageRequest(factory.getPage(), factory.getPageSize());
        query.with(pageable);
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC,"createDate")));

        List<Factory> factories = mongoTemplate.find(query, Factory.class);

        ResponseData responseData = new ResponseData();
        responseData.setTotal(mongoTemplate.count(query, CoalPrice.class));
        responseData.setPage(factory.getPage());

        responseData.setData(factories);

        return responseData;
    }
}
