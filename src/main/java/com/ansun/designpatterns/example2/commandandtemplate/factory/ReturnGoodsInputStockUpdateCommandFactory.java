package com.ansun.designpatterns.example2.commandandtemplate.factory;

import cn.hutool.core.collection.CollectionUtil;
import com.ansun.designpatterns.example2.commandandtemplate.GoodsStockUpdateCommand;
import com.ansun.designpatterns.example2.commandandtemplate.ReturnGoodsInputStockUpdateCommand;
import com.ansun.designpatterns.example2.dto.ReturnGoodsInputOrderDTO;
import com.ansun.designpatterns.example2.dto.ReturnGoodsInputOrderItemDTO;
import com.ansun.designpatterns.example2.entity.GoodsStockDO;
import com.ansun.designpatterns.mapper.example2.GoodsStockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 创建退货入库库存更新命令的工厂
 */
@Component
public class ReturnGoodsInputStockUpdateCommandFactory<T> extends AbstractGoodsStockUpdateCommandFactory<T> {

    private T t;

    @Autowired
    public ReturnGoodsInputStockUpdateCommandFactory(GoodsStockMapper goodsStockMapper) {
        super(goodsStockMapper);
    }

    /**
     * 获取商品SKU ID集合
     *
     * @return 商品SKU ID集合
     * @throws Exception
     */
    @Override
    protected List<Long> getGoodsSkuId(T t) {
        ReturnGoodsInputOrderDTO returnGoodsInputOrderDTO = (ReturnGoodsInputOrderDTO) t;
        List<ReturnGoodsInputOrderItemDTO> returnGoodsInputOrderItemDTOs = returnGoodsInputOrderDTO.getItems();

        if (CollectionUtil.isEmpty(returnGoodsInputOrderItemDTOs))     {
            return new ArrayList<>(0);
        }
        List<Long> goodsSkuIdList = new ArrayList<>(returnGoodsInputOrderItemDTOs.size());
        for (ReturnGoodsInputOrderItemDTO record : returnGoodsInputOrderItemDTOs) {
            goodsSkuIdList.add(record.getGoodsSkuId());
        }
        return goodsSkuIdList;
    }

    /**
     * 创建库存更新命令
     *
     * @param goodsStockDOList 商品库存DO对象集合
     * @return 库存更新命令
     * @throws Exception
     */
    @Override
    protected GoodsStockUpdateCommand createCommand(List<GoodsStockDO> goodsStockDOList, T t) {
        // 退货入库单
        ReturnGoodsInputOrderDTO returnGoodsInputOrderDTO = (ReturnGoodsInputOrderDTO) t;
        List<ReturnGoodsInputOrderItemDTO> returnGoodsInputOrderItemDTOs = returnGoodsInputOrderDTO.getItems();

        // key为商品SKU ID,值为退货入库单Item DTO对象
        Map<Long, ReturnGoodsInputOrderItemDTO> returnGoodsInputOrderItemDTOMap =
                new HashMap<>(16);

        if (CollectionUtil.isNotEmpty(returnGoodsInputOrderItemDTOs))     {
            for (ReturnGoodsInputOrderItemDTO record : returnGoodsInputOrderItemDTOs) {
                returnGoodsInputOrderItemDTOMap.put(record.getGoodsSkuId(), record);
            }
        }

        // 创建库存更新命令
        return new ReturnGoodsInputStockUpdateCommand(
                goodsStockDOList, goodsStockMapper, returnGoodsInputOrderItemDTOMap);
    }
}
