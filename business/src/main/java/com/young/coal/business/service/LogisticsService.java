package com.young.coal.business.service;

import com.young.coal.business.model.CoalPrice;
import com.young.coal.business.model.Logistics;
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
public class LogisticsService {

    @Autowired
    MongoTemplate mongoTemplate;

    public ResponseData query(Logistics logistics) {

        Query query = new Query();

        if (StringUtils.isNotEmpty(logistics.getFactoryName())){
            query.addCriteria(Criteria.where("factoryName").regex(logistics.getFactoryName()));
        }

        if (StringUtils.isNotEmpty(logistics.getStart())){
            query.addCriteria(Criteria.where("start").regex(logistics.getStart()).orOperator(Criteria.where("end").regex(logistics.getEnd())));
        }

        Pageable pageable = new PageRequest(logistics.getPage(), logistics.getPageSize());
        query.with(pageable);
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC,"createDate")));

        List<Logistics> logisticsList = mongoTemplate.find(query, Logistics.class);

        ResponseData responseData = new ResponseData();
        responseData.setTotal(mongoTemplate.count(query, Logistics.class));
        responseData.setPage(logistics.getPage());

        responseData.setData(logisticsList);

        return responseData;
    }

    public void update(Logistics logistics) {

        if (StringUtils.isEmpty(logistics.getLogisticsId())){
            logistics.setLogisticsId(UUID.randomUUID().toString().replaceAll("-", ""));
        }

        Update update = new Update();
        update.set("factoryName", logistics.getFactoryName());
        update.set("start", logistics.getStart());
        update.set("end", logistics.getEnd());
        update.set("count", logistics.getCount());
        update.set("price", logistics.getPrice());
        update.set("state", logistics.getState());
        update.set("updateDate", new Date());
        update.setOnInsert("createDate", new Date());
        update.setOnInsert("logisticsId", logistics.getLogisticsId());

        Query query = new Query(Criteria.where("logisticsId").is(logistics.getLogisticsId()));
        mongoTemplate.upsert(query, update, Logistics.class);
    }
}
