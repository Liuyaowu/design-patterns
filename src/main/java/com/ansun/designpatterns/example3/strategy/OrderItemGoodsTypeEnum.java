package com.ansun.designpatterns.example3.strategy;

import lombok.Getter;

/**
 * 订单条目商品类型枚举类
 *
 * @author liuyaowu
 * @date 2021-12-30 18:05
 */
@Getter
public enum OrderItemGoodsTypeEnum {

    /**
     * 产品
     */
    TYPE_PRODUCT("10", "产品"),

    /**
     * 服务
     */
    TYPE_SERVICE("20", "服务"),

    /**
     * 卡项
     *
     * @deprecated 更名为套餐
     */
    @Deprecated
    TYPE_CARD("30", "卡项"),

    /**
     * 套餐
     */
    TYPE_PACKAGE("30", "套餐"),

    ;

    /**
     * code
     */
    private String code;

    /**
     * code名称
     */
    private String name;

    OrderItemGoodsTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 根据枚举类型的code取得枚举类型
     *
     * @param code 枚举类型的值
     * @return 枚举类型
     */
    public static OrderItemGoodsTypeEnum findByCode(String code) {
        for (OrderItemGoodsTypeEnum enumType : values()) {
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
        for (OrderItemGoodsTypeEnum enumType : values()) {
            if (enumType.getCode().equals(code)) {
                return enumType.getName();
            }
        }
        return null;
    }

}
