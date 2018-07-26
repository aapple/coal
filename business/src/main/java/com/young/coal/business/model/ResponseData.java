package com.young.coal.business.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by yaobin on 2017/11/9.
 */
@Getter
@Setter
@ToString
public class ResponseData {

    private Long total;
    private Integer page;
    private Object data;
}
