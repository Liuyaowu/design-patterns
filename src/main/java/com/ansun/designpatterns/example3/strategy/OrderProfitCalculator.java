package com.ansun.designpatterns.example3.strategy;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.NumberUtil;
import com.ansun.designpatterns.example3.dto.OrderItemDiscountEntity;
import com.ansun.designpatterns.example3.dto.OrderRichDto;
import com.ansun.designpatterns.mapper.example3.OrderItemDiscountMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * 订单利润计算器
 *
 * @author liuyaowu
 * @date 2022-03-04 10:04
 */
@Slf4j
@Component
public class OrderProfitCalculator {

    private Map<String, Function<List<OrderRichDto.OrderItemDto>, OrderRichDto.OrderTurnover>> orderTypeMap = new HashMap(8);

    @Resource
    private OrderItemDiscountMapper orderItemDiscountMapper;

    /**
     * 初始化类型
     */
    @PostConstruct
    private void postConstruct() {
        orderTypeMap.put(OrderTypeEnum.ORDER_RETAIL.getCode(), key -> calculateRetailOrder(key));
        orderTypeMap.put(OrderTypeEnum.ORDER_SALE_CARD.getCode(), key -> calculateSaleCardOrder(key));
        orderTypeMap.put(OrderTypeEnum.ORDER_CONSUME_CARD.getCode(), key -> calculateConsumeCardOrder(key));
        orderTypeMap.put(OrderTypeEnum.ORDER_GENERAL.getCode(), key -> calculateGeneralOrder(key));
    }

    /**
     * 计算订单利润
     *
     * @param orderType  订单类型
     * @param orderItems 订单包含的订单明细
     * @return
     */
    public OrderRichDto.OrderTurnover calculateOrderProfit(String orderType, List<OrderRichDto.OrderItemDto> orderItems) {
        if (CollUtil.isEmpty(orderItems)) {
            log.error("1");
            // 抛出异常
        }

        Function<List<OrderRichDto.OrderItemDto>, OrderRichDto.OrderTurnover> function = orderTypeMap.get(orderType);
        if (function == null) {
            log.error("查询订单明细计算订单利润失败,订单类型没有对应的利润计算方式");
            // 抛出异常
        }
        return function.apply(orderItems);
    }

    /**
     * 计算零售订单利润
     *
     * @param orderItems 订单包含的订单明细
     * @return 订单利润对象
     * @deprecated 零售订单合并到了普通订单(开单)
     */
    @Deprecated
    private OrderRichDto.OrderTurnover calculateRetailOrder(List<OrderRichDto.OrderItemDto> orderItems) {
        // 产品收入、成本
        BigDecimal productIncome = BigDecimal.ZERO, productCost = BigDecimal.ZERO, productDiscount = BigDecimal.ZERO, productOperate = BigDecimal.ZERO;
        // 护理收入、成本
        BigDecimal serviceIncome = BigDecimal.ZERO, serviceCost = BigDecimal.ZERO, serviceDiscount = BigDecimal.ZERO, serviceOperate = BigDecimal.ZERO;

        for (OrderRichDto.OrderItemDto item : orderItems) {
            if (OrderItemGoodsTypeEnum.TYPE_PRODUCT.getCode().equals(item.getItemType())) {
                productCost = NumberUtil.add(productCost, item.getCostAmount());
                productDiscount = NumberUtil.add(productDiscount, item.getDiscountMoney());
                productOperate = NumberUtil.add(productOperate, item.getOperateMoney());
                productIncome = NumberUtil.sub(NumberUtil.add(productIncome, item.getAmount()), NumberUtil.add(productOperate, productDiscount));
            } else if (OrderItemGoodsTypeEnum.TYPE_SERVICE.getCode().equals(item.getItemType())) {
                serviceCost = NumberUtil.add(serviceCost, item.getCostAmount());
                serviceDiscount = NumberUtil.add(serviceDiscount, item.getDiscountMoney());
                serviceOperate = NumberUtil.add(serviceOperate, item.getOperateMoney());
                serviceIncome = NumberUtil.sub(NumberUtil.add(serviceIncome, item.getAmount()), NumberUtil.add(serviceOperate, serviceDiscount));
            }
        }

        // 订单利润 = (产品收入 - 成本) + (护理收入 - 成本)
        BigDecimal productProfit = NumberUtil.sub(productIncome, productCost);
        BigDecimal serviceProfit = NumberUtil.sub(serviceIncome, serviceCost);
        BigDecimal orderProfit = NumberUtil.add(productProfit, serviceProfit);

        return buildOrderTurnover(productIncome, productCost, serviceIncome, serviceCost, orderProfit);
    }

    /**
     * 计算开卡订单利润
     *
     * @param orderItems 订单包含的订单明细
     * @return 订单利润对象
     * @deprecated 开卡订单合并到了普通订单(开单)
     */
    @Deprecated
    private OrderRichDto.OrderTurnover calculateSaleCardOrder(List<OrderRichDto.OrderItemDto> orderItems) {
        // 开卡订单不计算利润
        return null;
    }

    /**
     * 计算普通订单(开单)利润
     *
     * @param orderItems
     * @return
     */
    private OrderRichDto.OrderTurnover calculateGeneralOrder(List<OrderRichDto.OrderItemDto> orderItems) {
        // 总的应收金额
        BigDecimal totalRealPayAmount = BigDecimal.ZERO;
        // 总的
        BigDecimal totalCostAmount = BigDecimal.ZERO;
        for (OrderRichDto.OrderItemDto item : orderItems) {
            // 成交金额
            BigDecimal realMoney = item.getRealMoney() == null ? BigDecimal.ZERO : item.getRealMoney();
            // 累计成本金额
            BigDecimal costAmount = item.getCostAmount() == null ? BigDecimal.ZERO : item.getCostAmount();

            totalRealPayAmount = NumberUtil.add(totalRealPayAmount, realMoney);
            totalCostAmount = NumberUtil.add(totalCostAmount, costAmount);
        }

        OrderRichDto.OrderTurnover orderTurnover = new OrderRichDto.OrderTurnover();
        // 利润 = 成交金额 - 成本
        orderTurnover.setOrderProfit(NumberUtil.sub(totalRealPayAmount, totalCostAmount));

        return orderTurnover;
    }

    /**
     * 计算耗卡订单利润
     *
     * @param orderItems 订单包含的订单明细
     * @return 订单利润对象
     */
    private OrderRichDto.OrderTurnover calculateConsumeCardOrder(List<OrderRichDto.OrderItemDto> orderItems) {
        // 产品收入、成本
        BigDecimal productIncome = BigDecimal.ZERO, productCost = BigDecimal.ZERO;
        // 护理收入、成本
        BigDecimal serviceIncome = BigDecimal.ZERO, serviceCost = BigDecimal.ZERO;

        for (OrderRichDto.OrderItemDto item : orderItems) {
            // 查询订单明细折扣信息:消耗订单的利润需要根据这个获取
            OrderItemDiscountEntity orderItemDiscountEntity = orderItemDiscountMapper.getByOrderItemId(item.getId());
            if (orderItemDiscountEntity == null) {
                // 抛出异常
            }

            if (OrderItemGoodsTypeEnum.TYPE_PRODUCT.getCode().equals(item.getItemType())) {
                productIncome = NumberUtil.add(productIncome, orderItemDiscountEntity.getAmount());
                productCost = NumberUtil.add(productCost, item.getCostAmount());
            } else if (OrderItemGoodsTypeEnum.TYPE_SERVICE.getCode().equals(item.getItemType())) {
                serviceIncome = NumberUtil.add(serviceIncome, orderItemDiscountEntity.getAmount());
                serviceCost = NumberUtil.add(serviceCost, item.getCostAmount());
            }
        }

        // 订单利润 = (产品收入 - 成本) + (护理收入 - 成本)
        BigDecimal orderProfit = NumberUtil.add(NumberUtil.sub(productIncome, productCost), NumberUtil.sub(serviceIncome, serviceCost));

        return buildOrderTurnover(productIncome, productCost, serviceIncome, serviceCost, orderProfit);
    }

    /**
     * 构建利润对象
     *
     * @param productIncome 产品收入
     * @param productCost   产品成本
     * @param serviceIncome 护理收入
     * @param serviceCost   护理成本
     * @param orderProfit   订单利润
     * @return
     */
    private OrderRichDto.OrderTurnover buildOrderTurnover(BigDecimal productIncome, BigDecimal productCost, BigDecimal serviceIncome, BigDecimal serviceCost, BigDecimal orderProfit) {
        OrderRichDto.OrderTurnover orderTurnover = new OrderRichDto.OrderTurnover();
        orderTurnover.setProductCost(productCost);
        orderTurnover.setProductIncome(productIncome);
        orderTurnover.setServiceCost(serviceCost);
        orderTurnover.setServiceIncome(serviceIncome);
        orderTurnover.setOrderProfit(orderProfit);

        return orderTurnover;
    }

}
