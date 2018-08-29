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
@Document(collection = "coal_price")
public class CoalPrice extends Page{

    private String factoryName;

    private String factoryType; // 煤炭，兰炭

    private String productType;

    private String productTypeDetail;

    private BigDecimal price; // 含税价

    private BigDecimal onePrice; // 一票价

    private BigDecimal priceDiff;

    private String productImage;

    private String reportImage;

    //是否水洗 0:不是 1 是
    private Integer coalWashing;

    //是否过筛  0:不是 1 是
    private Integer graded;

    private String coal_fareliang; //发热量
    private String coal_quanshuifen; // 全水分
    private String coal_liufen; // 硫份
    private String coal_huifafen; // 挥发份
    private String coal_huifen; // 灰分
    private String coal_gudingtan; // 固定碳
    private String coke_gudingtan; // 固定碳
    private String coke_hanliu; // 含硫
    private String coke_quanshui; // 全水
    private String coke_huifafen; // 挥发份
    private String coke_huifen; // 灰分

    //1.在产 0.停产
    private Integer state;


    @JsonFormat(timezone = "GMT+8", pattern = "MM-dd")
    private Date updateDate;

    @JsonFormat(timezone = "GMT+8", pattern = "MM-dd")
    private Date createDate;
}
