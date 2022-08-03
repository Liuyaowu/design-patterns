package com.ansun.designpatterns.mapper.example2;

import com.ansun.designpatterns.example2.entity.GoodsStockDO;

/**
 * TODO
 *
 * @author liuyaowu
 * @date 2022-08-03 15:57
 */
public interface GoodsStockMapper {

    void updateGoodsStock(GoodsStockDO goodsStockDO);

    GoodsStockDO getGoodsStockBySkuId(Long goodsSkuId);

}
