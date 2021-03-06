package com.young.coal.business.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

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

    private String queryUserName;

    private String queryType;

    private List<String> factoryNames;

    public Integer getPage(){
        if(this.page == null){
            this.page = 0;
        }

        return this.page;
    }

    public Integer getPageSize(){
        if(this.pageSize == null){
            this.pageSize = 500;
        }

        return this.pageSize;
    }
}
