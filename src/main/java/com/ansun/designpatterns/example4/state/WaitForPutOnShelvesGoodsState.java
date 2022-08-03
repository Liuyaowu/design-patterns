package com.ansun.designpatterns.example4.state;

import com.ansun.designpatterns.convertor.GoodsConvertor;
import com.ansun.designpatterns.mapper.example4.GoodsMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 审核通过等待上架状态
 *
 * @author liuyaowu
 * @date 2022-08-03 17:40
 */
@Component
public class WaitForPutOnShelvesGoodsState implements GoodsState {

    @Resource
    private GoodsMapper goodsMapper;
    @Resource
    private GoodsConvertor goodsConvertor;

    /**
     * 执行商品流转到当前状态来的业务逻辑
     */
    @Override
    public void doTransition(GoodsDTO goods) throws Exception {
        goods.setStatus(GoodsStatus.WAIT_FOR_PUT_ON_SHELVES);
        goods.setGmtModified(new Date());
        goodsMapper.updateStatus(goodsConvertor.dtoToDo(goods));
    }

    /**
     * 判断当前商品能否执行编辑操作
     */
    @Override
    public Boolean canEdit(GoodsDTO goods) throws Exception {
        return true;
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
        return true;
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
        return true;
    }
}
