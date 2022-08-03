package com.ansun.designpatterns.example4.state.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * 商品sku
 */
@Data
@EqualsAndHashCode
@ToString
public class GoodsSkuDTO {

    /**
     * id
     */
    private Long id;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 商品sku编号
     */
    private String skuCode;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品毛重
     */
    private Double grossWeight;

    /**
     * 商品长度
     */
    private Double goodsLength;

    /**
     * 商品宽度
     */
    private Double goodsWidth;

    /**
     * 商品高度
     */
    private Double goodsHeight;

    /**
     * 商品sku编号
     */
    private String goodsSkuCode;

    /**
     * 销售属性
     */
    private String saleProperties;

    /**
     * 采购价格
     */
    private Double purchasePrice;

    /**
     * 销售价格
     */
    private Double salePrice;

    /**
     * 折扣价格
     */
    private Double discountPrice;

    /**
     * 销售库存
     */
    private Long saleStockQuantity;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改文件
     */
    private Date gmtModified;

    /**
     * 销售属性值
     */
    List<GoodsSkuSalePropertyValueDTO> propertyValues;

}
