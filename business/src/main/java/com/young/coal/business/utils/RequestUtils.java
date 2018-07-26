package com.young.coal.business.utils;

import com.alibaba.fastjson.JSON;
import com.young.coal.business.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class RequestUtils {

    public static User getUserInfo(){

        HttpServletRequest request =  ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Cookie[] cookies = request.getCookies();
        if ( null == cookies || cookies.length == 0) {
            return null;
        }

        for(Cookie cookie : cookies){
            if(StringUtils.equals("push_session", cookie.getName())){

                try {
                    String value = URLDecoder.decode(cookie.getValue(), "UTF-8");
                    String userInfoJsonString = Base64Utils.getFromBase64(value);
                    return JSON.parseObject(userInfoJsonString, User.class);
                } catch (UnsupportedEncodingException e) {
                    return null;
                }
            }
        }
        return null;
    }
}
