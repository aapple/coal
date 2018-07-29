package com.young.coal.business.model;

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

    private String productType;

    private String productTypeDetail;

    private BigDecimal price;

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
    private String coke_hantan; // 含碳
    private String coke_hanliu; // 含硫
    private String coke_shuifen; // 水份
    private String coke_huifafen; // 挥发份
    private String coke_huifen; // 灰分

    //1.在产 0.停产
    private Integer state;

    private Date updateDate;

    private Date createDate;
}
