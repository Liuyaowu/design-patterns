package com.ansun.designpatterns.example2.commandandtemplate.factory;

import cn.hutool.core.collection.CollectionUtil;
import com.ansun.designpatterns.example2.commandandtemplate.GoodsStockUpdateCommand;
import com.ansun.designpatterns.example2.commandandtemplate.PurchaseInputStockUpdateCommand;
import com.ansun.designpatterns.example2.dto.PurchaseInputOrderDTO;
import com.ansun.designpatterns.example2.dto.PurchaseInputOrderItemDTO;
import com.ansun.designpatterns.example2.entity.GoodsStockDO;
import com.ansun.designpatterns.mapper.example2.GoodsStockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 创建采购入库库存更新命令的工厂
 */
@Component
public class PurchaseInputStockUpdateCommandFactory<T> extends AbstractGoodsStockUpdateCommandFactory<T> {

    private T t;

    @Autowired
    public PurchaseInputStockUpdateCommandFactory(GoodsStockMapper goodsStockMapper) {
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
        PurchaseInputOrderDTO purchaseInputOrderDTO = (PurchaseInputOrderDTO) t;
        List<PurchaseInputOrderItemDTO> purchaseInputOrderItemDTOList = purchaseInputOrderDTO.getItems();

        if (CollectionUtil.isEmpty(purchaseInputOrderItemDTOList))     {
            return new ArrayList<>(0);
        }
        List<Long> goodsSkuIdList = new ArrayList<>(purchaseInputOrderItemDTOList.size());
        for (PurchaseInputOrderItemDTO record : purchaseInputOrderItemDTOList) {
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
        // 采购入库单
        PurchaseInputOrderDTO purchaseInputOrderDTO = (PurchaseInputOrderDTO) t;
        List<PurchaseInputOrderItemDTO> purchaseInputOrderItemDTOList = purchaseInputOrderDTO.getItems();

        // key为商品SKU ID,值为采购入库单Item DTO对象
        Map<Long, PurchaseInputOrderItemDTO> purchaseInputOrderItemDTOMap = new HashMap<>(16);

        if (CollectionUtil.isNotEmpty(purchaseInputOrderItemDTOList))     {
            for (PurchaseInputOrderItemDTO record : purchaseInputOrderItemDTOList) {
                purchaseInputOrderItemDTOMap.put(record.getGoodsSkuId(), record);
            }
        }

        // 创建库存更新命令
        return new PurchaseInputStockUpdateCommand(goodsStockDOList, goodsStockMapper,  purchaseInputOrderItemDTOMap);
    }
}
