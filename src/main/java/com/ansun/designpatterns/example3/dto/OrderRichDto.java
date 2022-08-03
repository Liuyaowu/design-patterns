package com.ansun.designpatterns.example3.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单详细信息
 *
 * @author liuyaowu
 * @date 2022-01-13 19:08
 */
@Data
public class OrderRichDto implements Serializable {

    /**
     * 订单id
     */
    private Long id;

    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 会员姓名
     */
    private String memberName;

    /**
     * 会员手机号
     */
    private String mobile;

    /**
     * 门店编码
     */
    private String storeCode;

    /**
     * 门店名称
     */
    private String storeName;

    /**
     * 收银台id
     */
    private Long counterId;

    /**
     * 收银员id
     */
    private Long cashierId;

    /**
     * 收银员姓名
     */
    private String cashierName;

    /**
     * 订单类型
     */
    private String orderType;

    /**
     * 订单号
     */
    private Long orderNum;

    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;

    /**
     * 改价金额
     */
    private BigDecimal operateMoney;

    /**
     * 优惠金额
     */
    private BigDecimal discountMoney;

    /**
     * 应收金额
     */
    private BigDecimal payMoney;

    /**
     * 已付金额
     */
    private BigDecimal paidMoney;

    /**
     * 订单状态（00=挂起、10=待付款、20=已完成、30=已取消）
     */
    private String orderStatus;

    /**
     * 支付状态（10=未支付、20=部分支付、30=已支付、40=部分退款、50=全额退款、99=无需支付）
     */
    private String payStatus;

    /**
     * 订单备注
     */
    private String remarks;

    /**
     * 订单创建时间
     */
    private Date createDate;

    /**
     * 订单利润
     */
    private OrderTurnover orderTurnover;

    /**
     * 订单已完成出库标记:0=未完成出库,1=已完成出库,其它表示不需要出库
     */
    private String exWarehouseFlag;

    /**
     * 订单是否可退款
     */
    private Boolean refundable;

    /**
     * 订单是否可撤销
     */
    private Boolean revocable;

    /**
     * 取消原因id
     */
    private String cancelReasonId;

    /**
     * 取消原因
     */
    private String cancelReason;

    /**
     * 是否存在退款项,true表示有过退款商品,false或其它表示订单没有退款过
     */
    private Boolean existRefundItem;

    /**
     * 订单条目
     */
    private List<OrderItemDto> orderItemList;

    /**
     * 支付退款记录
     */
    private List<PayRefundsDto> payRefundsList;

    /**
     * 订单条目
     */
    @Data
    public static class OrderItemDto implements Serializable {

        /**
         * 订单条目id
         */
        private Long id;

        /**
         * 商品id
         */
        private Long itemId;

        /**
         * 商品SKU ID
         */
        private Long itemSkuId;

        /**
         * 商品类型（10=产品、20=服务、30=卡项）
         */
        private String itemType;

        /**
         * 商品单位
         */
        private String unit;

        /**
         * 主品标记:0=非主品,1=主品
         */
        private Integer mainFlag;

        /**
         * 是否朔源 1=已溯源、0=未溯源
         */
        private String isSourceCode;

        /**
         * 商品名称
         */
        private String itemName;

        /**
         * 数量
         */
        private Integer quantity;

        /**
         * 售价
         */
        private BigDecimal realPrice;

        /**
         * 改价金额
         */
        private BigDecimal operateMoney;

        /**
         * 优惠金额
         */
        private BigDecimal discountMoney;

        /**
         * 小计金额
         */
        private BigDecimal amount;

        /**
         * 成本金额
         */
        private BigDecimal costAmount;

        /**
         * 实际金额
         */
        private BigDecimal realMoney;

        /**
         * 会员护理记录id
         */
        private Long memberServiceLogId;

    }

    /**
     * 订单营业额
     */
    @Data
    public static class OrderTurnover implements Serializable {

        /**
         * 产品收入
         */
        private BigDecimal productIncome;

        /**
         * 护理收入
         */
        private BigDecimal serviceIncome;

        /**
         * 产品成本
         */
        private BigDecimal productCost;

        /**
         * 护理成本
         */
        private BigDecimal serviceCost;

        /**
         * 订单利润
         */
        private BigDecimal orderProfit;

    }

    /**
     * 付款退款记录
     */
    @Data
    public static class PayRefundsDto implements Serializable {

        /**
         * 记录id
         */
        private Long id;

        /**
         * 订单id
         */
        private Long orderId;

        /**
         * 支付金额
         */
        private BigDecimal amount;

        /**
         * 支付方式id
         */
        private Long paymentMethodId;

        /**
         * 支付方式名称
         */
        private String paymentMethodName;

        /**
         * 创建时间
         */
        private Date createDate;

    }

}
