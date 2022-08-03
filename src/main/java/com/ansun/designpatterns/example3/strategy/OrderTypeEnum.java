package com.ansun.designpatterns.example3.strategy;

import lombok.Getter;

/**
 * 订单类型枚举类
 *
 * @author liuyaowu
 * @date 2021-12-28 11:28
 */
@Getter
public enum OrderTypeEnum {

    /**
     * 零售订单
     */
    @Deprecated
    ORDER_RETAIL("10", "零售订单"),

    /**
     * 开卡订单
     */
    @Deprecated
    ORDER_SALE_CARD("20", "开卡订单"),

    /**
     * 耗卡订单
     */
    ORDER_CONSUME_CARD("30", "耗卡订单"),

    /**
     * 普通订单
     */
    ORDER_GENERAL("40", "普通订单"),

    ;

    /**
     * code
     */
    private String code;

    /**
     * code名称
     */
    private String name;

    OrderTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 根据枚举类型的code取得枚举类型
     *
     * @param code 枚举类型的值
     * @return 枚举类型
     */
    public static OrderTypeEnum findByCode(String code) {
        for (OrderTypeEnum enumType : values()) {
            if (enumType.getCode().equals(code)) {
                return enumType;
            }
        }
        return null;
    }

    /**
     * 根据枚举类型的code取得枚举类型的名称
     *
     * @param code 枚举类型的值
     * @return 枚举类型的名称
     */
    public static String getNameByCode(String code) {
        for (OrderTypeEnum enumType : values()) {
            if (enumType.getCode().equals(code)) {
                return enumType.getName();
            }
        }
        return null;
    }
    
}
