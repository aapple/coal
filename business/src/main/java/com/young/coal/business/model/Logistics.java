package com.young.coal.business.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by yaobin on 2017/10/30.
 */
@Getter
@Setter
@ToString
@Document(collection = "logistics")
public class Logistics extends Page{

    private String logisticsId;

    private String factoryName;

    private String start;

    private String end;

    private String count;

    private BigDecimal price;

    //1.在产 0.停产
    private Integer state;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:SS")
    private Date updateDate;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:SS")
    private Date createDate;
}
