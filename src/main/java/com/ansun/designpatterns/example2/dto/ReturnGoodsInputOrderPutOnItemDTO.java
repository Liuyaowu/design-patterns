package com.ansun.designpatterns.example2.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * 退货入库单商品上架条目DTO类
 */
@Data
@ToString
@EqualsAndHashCode
public class ReturnGoodsInputOrderPutOnItemDTO {

    /**
     * id
     */
    private Long id;

    /**
     * 退货入库单条目id
     */
    private Long returnGoodsInputOrderItemId;

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
     * 退货入库单商品上架条目的创建时间
     */
    private Date gmtCreate;

    /**
     * 退货入库单商品上架条目的修改时间
     */
    private Date gmtModified;

}
