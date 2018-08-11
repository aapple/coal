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
public class Page {

    private Integer page;

    private Integer pageSize;

    private String sortField;

    private String sortDir;

    private Integer total;

    private String userName;

    private String queryType;

    public Integer getPage(){
        if(this.page == null){
            this.page = 0;
        }

        return this.page;
    }

    public Integer getPageSize(){
        if(this.pageSize == null){
            this.pageSize = 50;
        }

        return this.pageSize;
    }
}
