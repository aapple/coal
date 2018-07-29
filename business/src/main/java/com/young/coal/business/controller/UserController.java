package com.young.coal.business.controller;

import com.alibaba.fastjson.JSON;
import com.young.coal.business.common.exception.CoalException;
import com.young.coal.business.model.RequestParams;
import com.young.coal.business.model.SuccessTip;
import com.young.coal.business.model.User;
import com.young.coal.business.service.UserService;
import com.young.coal.business.common.utils.Base64Utils;
import com.young.coal.business.common.utils.RequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@RequestMapping("/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public Object login(@RequestBody User user, HttpServletResponse response){

        User result = userService.login(user);

        SuccessTip successTip = new SuccessTip();
        if(result == null){
            throw new CoalException("用户名或者密码错误！");
        }

        String userJsonString = JSON.toJSONString(result);
        String base64String = null;
        try {
            base64String = URLEncoder.encode(Base64Utils.getBase64(userJsonString), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("base64编码错误");
        }
        Cookie cookie = new Cookie("push_session", base64String);
        cookie.setMaxAge( 365 * 24 * 60 * 60);
        cookie.setPath("/");
        response.addCookie(cookie);

        return successTip;
    }

    @RequestMapping("/changePassword")
    @ResponseBody
    public Object changePassword(@RequestBody RequestParams requestParams) {

        User user = RequestUtils.getUserInfo();
        Boolean result = userService.changePassword(requestParams, user);

        SuccessTip successTip = new SuccessTip();
        if(result){
            successTip.setMessage("密码修改成功！");
        } else {
            successTip.setCode(0);
            successTip.setMessage("旧密码不匹配！");
        }

        return successTip;
    }

    @RequestMapping("/createUser")
    @ResponseBody
    public Object createUser(@RequestBody User user) {

        userService.createUser(user);

        SuccessTip successTip = new SuccessTip();
        return successTip;
    }
}