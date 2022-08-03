package com.ansun.designpatterns.example4.state.dto;

/**
 * 审核结果
 */
public interface ApproveResult {

    /**
     * 审核通过
     */
    Integer APPROVE_PASS = 1;

    /**
     * 审核未通过
     */
    Integer APPROVE_REJECT = 0;

}
