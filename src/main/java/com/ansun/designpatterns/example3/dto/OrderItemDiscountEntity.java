package com.ansun.designpatterns.example3.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单明细优惠对象 ord_order_item_discount
 *
 * @date 2021-12-20
 */
@Data
@TableName("ord_order_item_discount")
public class OrderItemDiscountEntity implements Serializable {
    /**
     * 主键id
     */
    private Long id;
    /**
     * 订单明细id
     */
    private Long orderItemId;
    /**
     * 类型（10=卡项抵扣、20=优惠券减免、30=积分抵扣）
     */
    private String type;
    /**
     * 状态,10=使用,20=退回
     */
    private Integer status;
    /**
     * 名称，如：次卡抵扣1次
     */
    private String name;
    /**
     * 金额
     */
    private BigDecimal amount;
    /**
     * 业务id
     */
    private String bizId;
    /**
     * 会员卡项id
     */
    private Long memberCardId;
    /**
     * 排序
     */
    private Integer orders;
}
