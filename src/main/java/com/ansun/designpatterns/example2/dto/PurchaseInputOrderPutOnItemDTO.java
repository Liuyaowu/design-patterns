package com.ansun.designpatterns.example2.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * 采购入库单的商品上架条目
 */
@Data
@ToString
@EqualsAndHashCode
public class PurchaseInputOrderPutOnItemDTO {

    /**
     * id
     */
    private Long id;

    /**
     * 采购入库单条目id
     */
    private Long purchaseInputOrderItemId;

    /**
     * 货位id
     */
    private Long goodsAllocationId;

    /**
     * 商品sku id
     */
    private Long goodsSkuId;

    /**
     * 商品上架数量
     */
    private Long putOnShelvesCount;

    /**
     * 商品上架条目的创建时间
     */
    private Date gmtCreate;

    /**
     * 商品上架条目的修改时间
     */
    private Date gmtModified;

}
