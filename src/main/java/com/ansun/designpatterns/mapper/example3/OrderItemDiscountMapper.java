package com.ansun.designpatterns.mapper.example3;

import com.ansun.designpatterns.example3.dto.OrderItemDiscountEntity;

/**
 * 订单条目优惠
 *
 * @author liuyaowu
 * @date 2022-01-13 15:26
 */
public interface OrderItemDiscountMapper {

    /**
     * 根据订单明细id查询
     *
     * @param orderItemId 订单明细id
     * @return
     */
    OrderItemDiscountEntity getByOrderItemId(Long orderItemId);

}
