package com.ansun.designpatterns.example4.state;

/**
 * 商品状态接口
 * <p>
 * 封装了商品状态的标准行为
 * - 流转到当前状态时商品需要做什么操作,比如更新商品status
 * - 当前状态下是否可以执行某些操作,比如编辑、审核、上/下架、删除
 *
 * @author liuyaowu
 * @date 2022-08-03 17:30
 */
public interface GoodsState {

    /**
     * 执行商品流转到当前状态来的业务逻辑
     */
    void doTransition(GoodsDTO goods) throws Exception;

    /**
     * 判断当前商品能否执行编辑操作
     */
    Boolean canEdit(GoodsDTO goods) throws Exception;

    /**
     * 判断能否执行审核操作
     */
    Boolean canApprove(GoodsDTO goods) throws Exception;

    /**
     * 判断能否执行上架操作
     */
    Boolean canPutOnShelves(GoodsDTO goods) throws Exception;

    /**
     * 判断能否执行下架操作
     */
    Boolean canPullOffShelves(GoodsDTO goods) throws Exception;

    /**
     * 能否执行删除操作
     */
    Boolean canRemove(GoodsDTO goods) throws Exception;

}
