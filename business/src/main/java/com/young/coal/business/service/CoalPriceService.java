package com.young.coal.business.service;

import com.young.coal.business.common.utils.RequestUtils;
import com.young.coal.business.model.CoalPrice;
import com.young.coal.business.model.ResponseData;
import com.young.coal.business.model.User;
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

    public ResponseData query(CoalPrice coalPrice) {

        Query query = new Query();

        if (StringUtils.isNotEmpty(coalPrice.getFactoryName())){
            query.addCriteria(Criteria.where("factoryName").regex(coalPrice.getFactoryName()));
        }

        if (StringUtils.isNotEmpty(coalPrice.getFactoryType())){
            query.addCriteria(Criteria.where("factoryType").regex(coalPrice.getFactoryType()));
        }

        if (StringUtils.isNotEmpty(coalPrice.getProductType())){
            query.addCriteria(Criteria.where("productType").regex(coalPrice.getProductType()));
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

        Update update = new Update();
        update.set("factoryName", coalPrice.getFactoryName());
        update.set("factoryType", coalPrice.getFactoryType());
        update.set("productType", coalPrice.getProductType());
        update.set("productTypeDetail", coalPrice.getProductTypeDetail());
        update.set("price", coalPrice.getPrice());
        update.set("coalWashing", coalPrice.getCoalWashing());
        update.set("graded", coalPrice.getGraded());
        update.set("state", coalPrice.getState());
        update.set("coal_fareliang", coalPrice.getCoal_fareliang());
        update.set("coal_quanshuifen", coalPrice.getCoal_quanshuifen());
        update.set("coal_liufen", coalPrice.getCoal_liufen());
        update.set("coal_huifafen", coalPrice.getCoal_huifafen());
        update.set("coal_huifen", coalPrice.getCoal_huifen());

        update.set("coke_gudingtan", coalPrice.getCoke_gudingtan());
        update.set("coke_hanliu", coalPrice.getCoke_hanliu());
        update.set("coke_quanshui", coalPrice.getCoke_quanshui());
        update.set("coke_huifafen", coalPrice.getCoke_huifafen());
        update.set("coke_huifen", coalPrice.getCoke_huifen());

        update.set("updateDate", new Date());
        update.setOnInsert("createDate", new Date());

        Query query = new Query(Criteria.where("factoryName").is(coalPrice.getFactoryName()));
        query.addCriteria(Criteria.where("productType").is(coalPrice.getProductType()));
        query.addCriteria(Criteria.where("productTypeDetail").is(coalPrice.getProductTypeDetail()));

        mongoTemplate.upsert(query, update, CoalPrice.class);
    }
}
