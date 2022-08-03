package com.ansun.designpatterns.example2.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * 退货入库单DTO
 */
@Data
@ToString
@EqualsAndHashCode
public class ReturnGoodsInputOrderDTO {

	/**
	 * id
	 */
	private Long id;

	/**
	 * 退货工单id
	 */
	private Long returnGoodsWorksheetId;

	/**
	 * 用户账号ID
	 */
	private Long userAccountId;

	/**
	 * 订单id
	 */
	private Long orderId;

	/**
	 * 订单编号
	 */
	private String orderNo;

	/**
	 * 退货入库单的状态
	 */
	private Integer status;

	/**
	 * 收货人
	 */
	private String consignee;

	/**
	 * 收货地址
	 */
	private String deliveryAddress;

	/**
	 * 收货人手机号码
	 */
	private String consigneeCellPhoneNumber;

	/**
	 * 运费
	 */
	private Double freight;

	/**
	 * 支付方式
	 */
	private Integer payType;

	/**
	 * 订单总金额
	 */
	private Double totalAmount;

	/**
	 * 折扣金额
	 */
	private Double discountAmount;

	/**
	 * 优惠券抵扣金额
	 */
	private Double couponAmount;

	/**
	 * 应付金额
	 */
	private Double payableAmount;

	/**
	 * 发票抬头
	 */
	private String invoiceTitle;

	/**
	 * 纳税人识别号
	 */
	private String taxpayerId;

	/**
	 * 订单备注
	 */
	private String orderComment;

	/**
	 * 退货原因
	 */
	private Integer returnGoodsReason;

	/**
	 * 退货备注
	 */
	private String returnGoodsRemark;

	/**
	 * 退货的实际到货时间
	 */
	private Date arrivalTime;

	/**
	 * 销售出库单的创建时间
	 */
	private Date gmtCreate;

	/**
	 * 销售出库单的修改时间
	 */
	private Date gmtModified;

	/**
	 * 退货入库单条目DTO集合
	 */
	private List<ReturnGoodsInputOrderItemDTO> items;

}
