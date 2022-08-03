package com.ansun.designpatterns.example4.state;

import com.ansun.designpatterns.example4.state.dto.GoodsDTO;
import org.springframework.stereotype.Component;

/**
 * 默认状态
 *
 * @author liuyaowu
 * @date 2022-08-03 17:31
 */
@Component
public class DefaultGoodsState implements GoodsState {

    /**
     * 执行商品流转到当前状态来的业务逻辑
     */
    @Override
    public void doTransition(GoodsDTO goods) throws Exception {
    }

    /**
     * 判断当前商品能否执行编辑操作
     */
    @Override
    public Boolean canEdit(GoodsDTO goods) throws Exception {
        return false;
    }

    /**
     * 判断能否执行审核操作
     */
    @Override
    public Boolean canApprove(GoodsDTO goods) throws Exception {
        return false;
    }

    /**
     * 判断能否执行上架操作
     */
    @Override
    public Boolean canPutOnShelves(GoodsDTO goods) throws Exception {
        return false;
    }

    /**
     * 判断能否执行下架操作
     */
    @Override
    public Boolean canPullOffShelves(GoodsDTO goods) throws Exception {
        return false;
    }

    /**
     * 能否执行删除操作
     */
    @Override
    public Boolean canRemove(GoodsDTO goods) throws Exception {
        return false;
    }
}