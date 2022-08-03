package com.ansun.designpatterns.example4.state.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * 商品
 */
@Data
@ToString
@EqualsAndHashCode
public class GoodsDO {

	/**
	 * id
	 */
	private Long id;

	/**
	 * 类目id
	 */
	private Long categoryId;

	/**
	 * 品牌id
	 */
	private Long brandId;

	/**
	 * 商品编号
	 */
	private String code;

	/**
	 * 商品名称
	 */
	private String name;

	/**
	 * 商品副名称
	 */
	private String subName;

	/**
	 * 商品毛重
	 */
	private Double grossWeight;

	/**
	 * 商品长度
	 */
	private Double length;

	/**
	 * 商品宽度
	 */
	private Double width;

	/**
	 * 商品高度
	 */
	private Double height;

	/**
	 * 商品状态
	 */
	private Integer status;

	/**
	 * 服务保证
	 */
	private String serviceGuarantees;

	/**
	 * 包装清单
	 */
	private String packageList;

	/**
	 * 运费模板id
	 */
	private Long freightTemplateId;

	/**
	 * 创建时间
	 */
	private Date gmtCreate;

	/**
	 * 修改时间
	 */
	private Date gmtModified;

}
