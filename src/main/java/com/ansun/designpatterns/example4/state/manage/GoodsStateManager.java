package com.ansun.designpatterns.example4.state.manage;

import com.ansun.designpatterns.example4.state.dto.GoodsDTO;

/**
 * 商品状态管理接口
 *
 * @author liuyaowu
 * @date 2022-08-03 17:42
 */
public interface GoodsStateManager {

    /**
     * 创建一个商品
     */
    void create(GoodsDTO goods) throws Exception;

    /**
     * 当前这个商品能否执行编辑操作
     */
    Boolean canEdit(GoodsDTO goods) throws Exception;

    /**
     * 执行编辑商品的操作
     *
     * @param goods 商品
     * @throws Exception
     */
    void edit(GoodsDTO goods) throws Exception;

    /**
     * 判断能否执行审核操作
     */
    Boolean canApprove(GoodsDTO goods) throws Exception;

    /**
     * 执行审核操作
     */
    void approve(GoodsDTO goods, Integer approveResult) throws Exception;

    /**
     * 判断能否执行上架操作
     */
    Boolean canPutOnShelves(GoodsDTO goods) throws Exception;

    /**
     * 执行上架操作
     */
    void putOnShelves(GoodsDTO goods) throws Exception;

    /**
     * 判断能否执行下架操作
     */
    Boolean canPullOffShelves(GoodsDTO goods) throws Exception;

    /**
     * 执行下架操作
     */
    void pullOffShelves(GoodsDTO goods) throws Exception;

    /**
     * 能否执行删除操作
     */
    Boolean canRemove(GoodsDTO goods) throws Exception;

}