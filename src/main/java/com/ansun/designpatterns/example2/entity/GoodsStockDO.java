package com.ansun.designpatterns.example2.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * 商品库存
 *
 * @author liuyaowu
 * @date 2022-08-03 15:57
 */
@Data
@ToString
@EqualsAndHashCode
public class GoodsStockDO {

    /**
     * id
     */
    private Long id;

    /**
     * 商品sku id
     */
    private Long goodsSkuId;

    /**
     * 商品sku的销售库存
     */
    private Long saleStockQuantity;

    /**
     * 商品sku的锁定库存
     */
    private Long lockedStockQuantity;

    /**
     * 商品sku的已销售库存
     */
    private Long saledStockQuantity;

    /**
     * 商品sku的库存状态
     */
    private Integer stockStatus;

    /**
     * 商品sku库存的创建时间
     */
    private Date gmtCreate;

    /**
     * 商品sku库存的修改时间
     */
    private Date gmtModified;

}
