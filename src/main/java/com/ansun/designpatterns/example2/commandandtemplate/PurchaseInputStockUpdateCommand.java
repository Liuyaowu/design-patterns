package com.ansun.designpatterns.example2.commandandtemplate;

import com.ansun.designpatterns.example2.entity.GoodsStockDO;
import com.ansun.designpatterns.mapper.example2.GoodsStockMapper;

import java.util.List;

/**
 * 采购入库库存更新命令
 */
public class PurchaseInputStockUpdateCommand extends AbstractGoodsStockUpdateCommand {

    /**
     * 采购入库单item集合
     */
    private Map<Long, PurchaseInputOrderItemDTO> purchaseInputOrderItemDTOMap;

    /**
     * 构造函数
     *
     * @param goodsStockDOList             商品库存DO集合
     * @param goodsStockMapper                商品库存mapper
     * @param purchaseInputOrderItemDTOMap 采购入库单商品sku id与采购入库单item map集合
     */
    public PurchaseInputStockUpdateCommand(List<GoodsStockDO> goodsStockDOList,
                                           GoodsStockMapper goodsStockMapper,
                                           Map<Long, PurchaseInputOrderItemDTO> purchaseInputOrderItemDTOMap) {
        super(goodsStockDOList, goodsStockMapper);
        this.purchaseInputOrderItemDTOMap = purchaseInputOrderItemDTOMap;
    }

    /**
     * 更新销售库存
     */
    @Override
    protected void updateSaleStockQuantity() {
        for (GoodsStockDO goodsStockDO : goodsStockDOList) {
            PurchaseInputOrderItemDTO purchaseInputOrderItemDTO = purchaseInputOrderItemDTOMap.get(goodsStockDO.getGoodsSkuId());
            goodsStockDO.setSaleStockQuantity(goodsStockDO.getSaleStockQuantity() + purchaseInputOrderItemDTO.getArrivalCount());
        }
    }

    /**
     * 更新锁定库存
     */
    @Override
    protected void updateLockedStockQuantity() {

    }

    /**
     * 更新已销售库存
     */
    @Override
    protected void updateSoldStockQuantity() {

    }

}
