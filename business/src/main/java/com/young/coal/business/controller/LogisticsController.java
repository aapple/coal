package com.young.coal.business.controller;


import com.young.coal.business.model.Logistics;
import com.young.coal.business.model.ResponseData;
import com.young.coal.business.model.SuccessTip;
import com.young.coal.business.service.LogisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yaobin on 2017/11/7.
 */
@Controller
@RequestMapping("/logistics")
public class LogisticsController {

    @Autowired
    private LogisticsService logisticsService;

    @RequestMapping("/update")
    @ResponseBody
    public Object update(@RequestBody Logistics logistics){

        logisticsService.update(logistics);

        return new SuccessTip();
    }

    @RequestMapping("/query")
    @ResponseBody
    public Object query(@RequestBody Logistics logistics){

        ResponseData responseData = logisticsService.query(logistics);
        SuccessTip successTip = new SuccessTip();
        successTip.setData(responseData);

        return successTip;
    }

}
