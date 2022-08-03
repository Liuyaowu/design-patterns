package com.ansun.designpatterns.example4.state.factory;

import com.ansun.designpatterns.example4.state.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 获取状态的工厂 根据传入的商品，获取商品状态，返回具体的状态处理对象
 *
 * @author liuyaowu
 * @date 2022-08-03 17:42
 */
@Component
public class GoodsStateFactory {

    /**
     * 待审核状态
     */
    @Autowired
    private WaitForApproveGoodsState waitForApproveGoodsState;
    /**
     * 待上架状态
     */
    @Autowired
    private WaitForPutOnShelvesGoodsState waitForPutOnShelvesGoodsState;
    /**
     * 审核未通过状态
     */
    @Autowired
    private ApproveRejectGoodsState approveRejectGoodsState;
    /**
     * 已上架状态
     */
    @Autowired
    private PuttedOnShelvesGoodsState puttedOnShelvesGoodsState;
    /**
     * 默认商品状态
     */
    @Autowired
    private DefaultGoodsState defaultGoodsState;

    /**
     * 获取商品对应的状态组件
     */
    public GoodsState get(GoodsDTO goods) throws Exception {
        if (GoodsStatus.WAIT_FOR_APPROVE.equals(goods.getStatus())) {
            return waitForApproveGoodsState;
        } else if (GoodsStatus.WAIT_FOR_PUT_ON_SHELVES.equals(goods.getStatus())) {
            return waitForPutOnShelvesGoodsState;
        } else if (GoodsStatus.APPROVE_REJECT.equals(goods.getStatus())) {
            return approveRejectGoodsState;
        } else if (GoodsStatus.PUTTED_ON_SHELVES.equals(goods.getStatus())) {
            return puttedOnShelvesGoodsState;
        } else {
            return defaultGoodsState;
        }
    }
}