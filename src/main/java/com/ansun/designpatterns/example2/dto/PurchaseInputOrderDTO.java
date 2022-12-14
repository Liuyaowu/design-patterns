package com.ansun.designpatterns.example2.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * 采购入库单DTO
 */
@Data
@ToString
@EqualsAndHashCode
public class PurchaseInputOrderDTO {

	/**
	 * id
	 */
	private Long id;

	/**
	 * 采购单id
	 */
	private Long purchaseOrderId;

	/**
	 * 供应商id
	 */
	private Long supplierId;

	/**
	 * 预期到达时间
	 */
	private Date expectArrivalTime;

	/**
	 * 实际到达时间
	 */
	private Date actualArrivalTime;

	/**
	 * 采购联系人
	 */
	private String purchaseContactor;

	/**
	 * 采购联系人电话号码
	 */
	private String purchaseContactorPhoneNumber;

	/**
	 * 采购联系人邮箱地址
	 */
	private String purchaseContactorEmail;

	/**
	 * 采购单备注
	 */
	private String purchaseOrderRemark;

	/**
	 * 采购员
	 */
	private String purchaser;

	/**
	 * 采购入库单的状态
	 */
	private Integer status;

	/**
	 * 采购入库单的创建时间
	 */
	private Date gmtCreate;

	/**
	 * 采购入库单的修改时间
	 */
	private Date gmtModified;

	/**
	 * 采购入库单条目集合
	 */
	private List<PurchaseInputOrderItemDTO> items;

}
