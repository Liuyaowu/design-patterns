package com.ansun.designpatterns.mapper.example3;

import com.ansun.designpatterns.example3.dto.OrderItemDiscountEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 订单条目优惠
 *
 * @author liuyaowu
 * @date 2022-01-13 15:26
 */
public interface OrderItemDiscountMapper {

    /**
     * 批量添加订单条目优惠
     *
     * @param list 订单条目优惠信息
     * @return
     */
    int batchInsert(@Param("list") List<OrderItemDiscountEntity> list);

    /**
     * 根据业务id查询订单条目优惠
     *
     * @param bizId 业务id
     * @return
     */
    OrderItemDiscountEntity getByBizId(@Param("bizId") Long bizId);

    /**
     * 根据订单明细id查询
     *
     * @param orderItemId 订单明细id
     * @return
     */
    OrderItemDiscountEntity getByOrderItemId(Long orderItemId);

    /**
     * 根据订单明细id批量查询订单优惠信息
     *
     * @param orderItemIds
     * @return
     */
    List<OrderItemDiscountEntity> listByOrderItemIds(@Param("orderItemIds") Set<Long> orderItemIds);

    /**
     * 查询套餐使用完的记录
     *
     * @param memberCardId 套餐id
     * @return
     */
    List<OrderItemDiscountEntity> listPackageUsed(@Param("memberCardId") Long memberCardId);

    /**
     * 批量修改状态为退回
     *
     * @param ids 订单id
     * @return
     */
    int batchUpdateStatusAsReturnByIds(@Param("ids") Set<Long> ids);

    /**
     * 批量修改状态为退回
     *
     * @param itemIds 订单明细id
     * @return
     */
    int batchUpdateStatusAsReturnByItemIds(@Param("itemIds") Set<Long> itemIds);

}
