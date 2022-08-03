package com.ansun.designpatterns.example4.state.manage;

import com.ansun.designpatterns.example4.state.*;
import com.ansun.designpatterns.example4.state.dto.ApproveResult;
import com.ansun.designpatterns.example4.state.dto.GoodsDTO;
import com.ansun.designpatterns.example4.state.factory.GoodsStateFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 商品状态管理实现类 
 * 
 * 通过传入的商品,根据商品状态通过状态工厂获取对应的对象
 *
 * @author liuyaowu
 * @date 2022-08-03 17:43
 */
@Component
public class GoodsStateManagerImpl implements GoodsStateManager {

    /**
     * 待审核状态
     */
    @Resource
    private WaitForApproveGoodsState waitForApproveGoodsState;
    /**
     * 待上架状态
     */
    @Resource
    private WaitForPutOnShelvesGoodsState waitForPutOnShelvesGoodsState;
    /**
     * 审核未通过状态
     */
    @Resource
    private ApproveRejectGoodsState approveRejectGoodsState;
    /**
     * 已上架状态
     */
    @Resource
    private PuttedOnShelvesGoodsState puttedOnShelvesGoodsState;
    /**
     * 商品状态工厂
     */
    @Resource
    private GoodsStateFactory goodsStateFactory;

    /**
     * 创建一个商品
     */
    @Override
    public void create(GoodsDTO goods) throws Exception {
        waitForApproveGoodsState.doTransition(goods);
    }

    /**
     * 当前这个商品能否执行编辑操作
     */
    @Override
    public Boolean canEdit(GoodsDTO goods) throws Exception {
        GoodsState state = goodsStateFactory.get(goods);
        return state.canEdit(goods);
    }

    /**
     * 执行编辑商品的操作
     */
    @Override
    public void edit(GoodsDTO goods) throws Exception {
        waitForApproveGoodsState.doTransition(goods);
    }

    /**
     * 判断能否执行审核操作
     */
    @Override
    public Boolean canApprove(GoodsDTO goods) throws Exception {
        GoodsState state = goodsStateFactory.get(goods);
        return state.canApprove(goods);
    }

    /**
     * 执行审核操作
     */
    @Override
    public void approve(GoodsDTO goods, Integer approveResult) throws Exception {
        if (ApproveResult.APPROVE_PASS.equals(approveResult)) {
            waitForPutOnShelvesGoodsState.doTransition(goods);
        } else if (ApproveResult.APPROVE_REJECT.equals(approveResult)) {
            approveRejectGoodsState.doTransition(goods);
        }
    }

    /**
     * 判断能否执行上架操作
     */
    @Override
    public Boolean canPutOnShelves(GoodsDTO goods) throws Exception {
        GoodsState state = goodsStateFactory.get(goods);
        return state.canPutOnShelves(goods);
    }

    /**
     * 执行上架操作
     */
    @Override
    public void putOnShelves(GoodsDTO goods) throws Exception {
        puttedOnShelvesGoodsState.doTransition(goods);
    }

    /**
     * 判断能否执行下架操作
     */
    @Override
    public Boolean canPullOffShelves(GoodsDTO goods) throws Exception {
        GoodsState state = goodsStateFactory.get(goods);
        return state.canPullOffShelves(goods);
    }

    /**
     * 执行下架操作
     */
    @Override
    public void pullOffShelves(GoodsDTO goods) throws Exception {
        waitForPutOnShelvesGoodsState.doTransition(goods);
    }

    /**
     * 能否执行删除操作
     */
    @Override
    public Boolean canRemove(GoodsDTO goods) throws Exception {
        GoodsState state = goodsStateFactory.get(goods);
        return state.canRemove(goods);
    }

}
