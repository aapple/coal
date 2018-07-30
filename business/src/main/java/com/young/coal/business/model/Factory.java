package com.young.coal.business.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "factory")
public class Factory {

    private String factoryName;
    private String userId; // 管理用户的Id
    private String sallerName; // 业务员名字
    private String sallerPhone; // 业务员联系电话
    private Date createDate;

}