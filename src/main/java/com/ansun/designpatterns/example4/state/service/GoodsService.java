package com.ansun.designpatterns.example4.state.service;

import com.ansun.designpatterns.convertor.GoodsConvertor;
import com.ansun.designpatterns.example4.state.manage.GoodsStateManager;
import com.ansun.designpatterns.mapper.example4.GoodsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * TODO
 *
 * @author liuyaowu
 * @date 2022-08-03 17:45
 */
@Service
public class GoodsService {
    
    @Resource
    private GoodsStateManager goodsStateManager;
    @Resource
    private GoodsMapper goodsMapper;
    @Resource
    private GoodsConvertor goodsConvertor;

    /**
     * 新增商品
     */
    public Long save(GoodsDTO goods) throws Exception {
        goods.setStatus(GoodsStatus.UNKNOWN);
        goods.setGmtCreate(new Date());
        goods.setGmtModified(new Date());
        Long goodsId = goodsMapper.save(goodsConvertor.dtoToDo(goods));
        goods.setId(goodsId);

        goodsStateManager.create(goods);
        return goodsId;
    }

    /**
     * 更新商品
     */
    public Boolean update(GoodsDTO goods) throws Exception {
        if (!goodsStateManager.canEdit(goods)) {
            return false;
        }

        goodsMapper.update(goodsConvertor.dtoToDo(goods));
        goodsStateManager.edit(goods);

        return true;
    }

    /**
     * 审核商品
     */
    public Boolean approve(Long goodsId, Integer approveResult) throws Exception {
        GoodsDTO goods = goodsConvertor.doToDto(goodsMapper.getById(goodsId));
        if (!goodsStateManager.canApprove(goods)) {
            return false;
        }
        goodsStateManager.approve(goods, approveResult);
        return true;
    }

    /**
     * 执行上架操作
     */
    public Boolean putOnShelves(Long goodsId) throws Exception {
        GoodsDTO goods = goodsConvertor.doToDto(goodsMapper.getById(goodsId));
        if (!goodsStateManager.canPutOnShelves(goods)) {
            return false;
        }
        goodsStateManager.putOnShelves(goods);
        return true;
    }

    /**
     * 执行下架操作
     */
    public Boolean pullOffShelves(Long goodsId) throws Exception {
        GoodsDTO goods = goodsConvertor.doToDto(goodsMapper.getById(goodsId));
        if (!goodsStateManager.canPullOffShelves(goods)) {
            return false;
        }
        goodsStateManager.pullOffShelves(goods);
        return true;
    }
    
}
