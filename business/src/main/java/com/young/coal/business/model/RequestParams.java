package com.young.coal.business.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by yaobin on 2017/11/8.
 */
@Getter
@Setter
@ToString
public class RequestParams extends Page{

    private String oldPassword;

    private String newPassword;

    private String userName;
}
