package com.ansun.designpatterns.example2.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * 采购入库单条目
 */
@Data
@ToString
@EqualsAndHashCode
public class PurchaseInputOrderItemDTO {

    /**
     * id
     */
    private Long id;

    /**
     * 采购入库单id
     */
    private Long purchaseInputOrderId;

    /**
     * 商品sku id
     */
    private Long goodsSkuId;

    /**
     * 商品sku的采购数量
     */
    private Long purchaseCount;

    /**
     * 商品sku的采购价格
     */
    private Double purchasePrice;

    /**
     * 商品sku到货后质检出来的合格商品数量
     */
    private Long qualifiedCount;

    /**
     * 商品sku实际到货的数量
     */
    private Long arrivalCount;

    /**
     * 采购入库单条目的创建时间
     */
    private Date gmtCreate;

    /**
     * 采购入库单条目的修改时间
     */
    private Date gmtModified;

    /**
     * 采购入库单商品上架条目集合
     */
    private List<PurchaseInputOrderPutOnItemDTO> putOnItemDTOs;

    /**
     * 货位库存明细
     */
    private List<GoodsAllocationStockDetailDTO> stockDetails;

}
