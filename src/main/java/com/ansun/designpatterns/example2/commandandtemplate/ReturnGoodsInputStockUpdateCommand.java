package com.ansun.designpatterns.example2.commandandtemplate;

import com.ansun.designpatterns.example2.dto.ReturnGoodsInputOrderItemDTO;
import com.ansun.designpatterns.example2.entity.GoodsStockDO;
import com.ansun.designpatterns.mapper.example2.GoodsStockMapper;

import java.util.List;
import java.util.Map;

/**
 * 退货入库库存更新命令
 */
public class ReturnGoodsInputStockUpdateCommand extends AbstractGoodsStockUpdateCommand {

    /**
     * 退货入库单item集合
     */
    private Map<Long, ReturnGoodsInputOrderItemDTO> returnGoodsInputOrderItemDTOMap;

    /**
     * 构造函数
     *
     * @param goodsStockDOList                商品库存DO集合
     * @param goodsStockMapper                   商品库存mapper
     * @param returnGoodsInputOrderItemDTOMap 退货入库单sku id与退货入库单item map集合
     */
    public ReturnGoodsInputStockUpdateCommand(List<GoodsStockDO> goodsStockDOList,
                                              GoodsStockMapper goodsStockMapper,
                                              Map<Long, ReturnGoodsInputOrderItemDTO> returnGoodsInputOrderItemDTOMap) {
        super(goodsStockDOList, goodsStockMapper);
        this.returnGoodsInputOrderItemDTOMap = returnGoodsInputOrderItemDTOMap;
    }

    /**
     * 更新销售库存
     */
    @Override
    protected void updateSaleStockQuantity() {
        for (GoodsStockDO goodsStockDO : goodsStockDOList) {
            ReturnGoodsInputOrderItemDTO returnGoodsInputOrderItemDTO = returnGoodsInputOrderItemDTOMap.get(goodsStockDO.getGoodsSkuId());
            goodsStockDO.setSaleStockQuantity(goodsStockDO.getSaleStockQuantity() + returnGoodsInputOrderItemDTO.getArrivalCount());
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
        for (GoodsStockDO goodsStockDO : goodsStockDOList) {
            ReturnGoodsInputOrderItemDTO returnGoodsInputOrderItemDTO = returnGoodsInputOrderItemDTOMap.get(goodsStockDO.getGoodsSkuId());
            goodsStockDO.setSaledStockQuantity(goodsStockDO.getSaledStockQuantity() - returnGoodsInputOrderItemDTO.getArrivalCount());
        }
    }

}
