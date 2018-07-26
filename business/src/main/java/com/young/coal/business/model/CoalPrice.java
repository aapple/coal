package com.young.coal.business.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by yaobin on 2017/10/30.
 */
@Getter
@Setter
@ToString
@Document(collection = "resource")
public class CoalPrice extends Page{

    private String resourceId; // ID

    private String type; // contactsId,image,video, text

    private String imageType; // iamge,logo

    private String url;

    private String description;

    private Date createDate; // 创建时间
}
