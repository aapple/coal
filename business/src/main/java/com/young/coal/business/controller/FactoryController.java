package com.young.coal.business.controller;


import com.young.coal.business.common.utils.RequestUtils;
import com.young.coal.business.model.CoalPrice;
import com.young.coal.business.model.Factory;
import com.young.coal.business.model.ResponseData;
import com.young.coal.business.model.SuccessTip;
import com.young.coal.business.service.CoalPriceService;
import com.young.coal.business.service.FactoryService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by yaobin on 2017/11/7.
 */
@Controller
@RequestMapping("/factory")
public class FactoryController {

    @Autowired
    private FactoryService factoryService;

    @RequestMapping("/update")
    @ResponseBody
    public Object update(@RequestBody Factory factory){

        factoryService.update(factory);

        return new SuccessTip();
    }

    @RequestMapping("/query")
    @ResponseBody
    public Object query(@RequestBody Factory factory){

        ResponseData responseData = factoryService.query(factory);
        SuccessTip successTip = new SuccessTip();
        successTip.setData(responseData);

        return successTip;
    }

    @RequestMapping("/queryFactoryByUserName")
    @ResponseBody
    public Object queryFactoryByUserName(@RequestBody Factory factory){

        List<String> factoryNames = factoryService.queryFactorys(factory.getFactoryType());
        SuccessTip successTip = new SuccessTip();
        successTip.setData(factoryNames);

        return successTip;
    }

}
