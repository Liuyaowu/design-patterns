package com.ansun.designpatterns.example4.state;

import com.ansun.designpatterns.convertor.GoodsDTOConvertor;
import com.ansun.designpatterns.example4.state.dto.GoodsDTO;
import com.ansun.designpatterns.example4.state.dto.GoodsStatus;
import com.ansun.designpatterns.mapper.example4.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 已上架状态
 */
@Component
public class PuttedOnShelvesGoodsState implements GoodsState {

    /**
     * 商品管理DAO组件
     */
    @Autowired
    private GoodsMapper goodsMapper;
    @Resource
    private GoodsDTOConvertor goodsDTOConvertor;

    /**
     * 执行商品流转到当前状态来的业务逻辑
     *
     * @param goods 商品
     * @throws Exception
     */
    @Override
    public void doTransition(GoodsDTO goods) throws Exception {
        goods.setStatus(GoodsStatus.PUTTED_ON_SHELVES);
        goods.setGmtModified(new Date());
        goodsMapper.updateStatus(goodsDTOConvertor.dtoToDo(goods));
    }

    /**
     * 判断当前商品能否执行编辑操作
     *
     * @param goods 商品
     * @return 能否执行编辑操作
     */
    @Override
    public Boolean canEdit(GoodsDTO goods) throws Exception {
        return false;
    }

    /**
     * 判断能否执行审核操作
     *
     * @param goods 商品
     * @return
     * @throws Exception
     */
    @Override
    public Boolean canApprove(GoodsDTO goods) throws Exception {
        return false;
    }

    /**
     * 判断能否执行上架操作
     *
     * @param goods 商品
     * @return 能否执行上架操作
     * @throws Exception
     */
    @Override
    public Boolean canPutOnShelves(GoodsDTO goods) throws Exception {
        return false;
    }

    /**
     * 判断能否执行下架操作
     *
     * @param goods 商品
     * @return
     * @throws Exception
     */
    @Override
    public Boolean canPullOffShelves(GoodsDTO goods) throws Exception {
        return true;
    }

    /**
     * 能否执行删除操作
     *
     * @param goods 商品
     * @return
     * @throws Exception
     */
    @Override
    public Boolean canRemove(GoodsDTO goods) throws Exception {
        return false;
    }

}
