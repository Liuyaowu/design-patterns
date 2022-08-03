package com.ansun.designpatterns.example4.state.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * 商品sku的销售属性值
 */
@Data
@ToString
@EqualsAndHashCode
public class GoodsSkuSalePropertyValueDTO {

    /**
     * id
     */
    private Long id;

    /**
     * 商品sku id
     */
    private Long goodsSkuId;

    /**
     * 类目与属性关联关系id
     */
    private Long relationId;

    /**
     * 属性值
     */
    private String propertyValue;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

}
