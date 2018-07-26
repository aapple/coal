package com.young.coal.business.controller;


import com.young.coal.business.model.CoalPrice;
import com.young.coal.business.model.ResponseData;
import com.young.coal.business.model.SuccessTip;
import com.young.coal.business.service.CoalPriceService;
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
@RequestMapping("/coalPrice")
public class CoalPriceController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CoalPriceService coalPriceService;

    @RequestMapping("/add")
    @ResponseBody
    public Object add(@RequestBody CoalPrice coalPrice){

        coalPriceService.add(coalPrice);

        return new SuccessTip();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(@RequestBody CoalPrice coalPrice){

        coalPriceService.delete(coalPrice);

        return new SuccessTip();
    }

    @RequestMapping("/update")
    @ResponseBody
    public Object update(@RequestBody CoalPrice coalPrice){

        coalPriceService.update(coalPrice);

        return new SuccessTip();
    }

    @RequestMapping("/query")
    @ResponseBody
    public Object query(@RequestBody CoalPrice coalPrice){

        ResponseData responseData = coalPriceService.query(coalPrice);
        SuccessTip successTip = new SuccessTip();
        successTip.setData(responseData);

        return successTip;
    }

}
