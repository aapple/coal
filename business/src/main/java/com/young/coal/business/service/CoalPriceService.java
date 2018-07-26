package com.young.coal.business.service;

import com.young.coal.business.model.CoalPrice;
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
public class CoalPriceService {

    @Autowired
    MongoTemplate mongoTemplate;

    public void add(CoalPrice coalPrice) {

        coalPrice.setResourceId(UUID.randomUUID().toString().replace("-", ""));
        coalPrice.setCreateDate(new Date());

        mongoTemplate.save(coalPrice);
    }


    public void delete(CoalPrice coalPrice) {

        Query query = Query.query(Criteria.where("resourceId").is(coalPrice.getResourceId()));
        mongoTemplate.remove(query, CoalPrice.class);
    }

    public ResponseData query(CoalPrice coalPrice) {

        Query query = new Query();

        if(StringUtils.isNotEmpty(coalPrice.getType())){
            query.addCriteria(Criteria.where("type").regex(coalPrice.getType()));
        }

        if(StringUtils.isNotEmpty(coalPrice.getImageType())){
            query.addCriteria(Criteria.where("imageType").regex(coalPrice.getImageType()));
        }

        if(StringUtils.isNotEmpty(coalPrice.getDescription())){
            query.addCriteria(Criteria.where("description").regex(coalPrice.getDescription(), "i"));
        }

        Pageable pageable = new PageRequest(coalPrice.getPage(), coalPrice.getPageSize());
        query.with(pageable);
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC,"createDate")));

        List<CoalPrice> coalPrices = mongoTemplate.find(query, CoalPrice.class);

        ResponseData responseData = new ResponseData();
        responseData.setTotal(mongoTemplate.count(query, CoalPrice.class));
        responseData.setPage(coalPrice.getPage());

        responseData.setData(coalPrices);

        return responseData;
    }

    public void update(CoalPrice coalPrice) {

        Update update = new Update().set("description", coalPrice.getDescription());
        Query queryTask = new Query(Criteria.where("resourceId").is(coalPrice.getResourceId()));
        mongoTemplate.updateFirst(queryTask, update, CoalPrice.class);
    }
}
