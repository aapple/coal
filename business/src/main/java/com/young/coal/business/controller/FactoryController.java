package com.young.coal.business.controller;


import com.young.coal.business.model.CoalPrice;
import com.young.coal.business.model.Factory;
import com.young.coal.business.model.ResponseData;
import com.young.coal.business.model.SuccessTip;
import com.young.coal.business.service.CoalPriceService;
import com.young.coal.business.service.FactoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

}
