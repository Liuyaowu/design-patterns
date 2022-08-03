package com.ansun.designpatterns.example4.state.dto;

/**
 * 商品状态
 */
public interface GoodsStatus {

    /**
     * 未知类型
     */
    Integer UNKNOWN = 0;

    /**
     * 待审核
     */
    Integer WAIT_FOR_APPROVE = 1;

    /**
     * 待上架
     */
    Integer WAIT_FOR_PUT_ON_SHELVES = 2;

    /**
     * 审核不通过
     */
    Integer APPROVE_REJECT = 3;

    /**
     * 已上架
     */
    Integer PUTTED_ON_SHELVES = 4;

}
