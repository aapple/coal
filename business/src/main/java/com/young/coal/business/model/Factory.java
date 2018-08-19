package com.young.coal.business.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "factory")
public class Factory extends Page{

    private String factoryName;
    private String factoryType; // 煤炭，兰炭，物流
    private String userName; // 管理用户的Id
    private String sallerName; // 业务员名字
    private String sallerPhone; // 业务员联系电话

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:SS")
    private Date updateDate;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:SS")
    private Date createDate;

}
