package com.ansun.designpatterns.example2.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * 货位库存明细
 */
@Data
@ToString
@EqualsAndHashCode
public class GoodsAllocationStockDetailDTO {

	/**
	 * id
	 */
	private Long id;

	/**
	 * 商品sku id
	 */
	private Long goodsSkuId;

	/**
	 * 货位id
	 */
	private Long goodsAllocationId;

	/**
	 * 这一批商品的上架时间
	 */
	private Date putOnTime;

	/**
	 * 这一批商品本次上架的数量
	 */
	private Long putOnQuantity;

	/**
	 * 这一批上架的商品当前还剩余的库存数量
	 */
	private Long currentStockQuantity;

	/**
	 * 锁定库存
	 */
	private Long lockedStockQuantity;

	/**
	 * 创建时间
	 */
	private Date gmtCreate;

	/**
	 * 修改时间
	 */
	private Date gmtModified;

}
