package com.ansun.designpatterns.example2.commandandtemplate.factory;

/**
 * TODO
 *
 * @author liuyaowu
 * @date 2022-08-03 16:09
 */

import com.ansun.designpatterns.example2.commandandtemplate.GoodsStockUpdateCommand;
import com.ansun.designpatterns.example2.entity.GoodsStockDO;
import com.ansun.designpatterns.mapper.example2.GoodsStockMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 库存更新命令工厂抽象类
 */
@Slf4j
public abstract class AbstractGoodsStockUpdateCommandFactory<T> implements GoodsStockUpdateCommandFactory<T> {

    /**
     * 商品库存
     */
    protected GoodsStockMapper goodsStockMapper;

    /**
     * 构造函数
     *
     * @param goodsStockMapper 商品库存Mapper
     */
    public AbstractGoodsStockUpdateCommandFactory(GoodsStockMapper goodsStockMapper) {
        this.goodsStockMapper = goodsStockMapper;
    }

    /**
     * 创建商品库存更新命令:模板方法
     *
     * @param t 参数对象泛型
     * @return 库存更新命令, 异常返回null
     */
    @Override
    public GoodsStockUpdateCommand createCommand(T t) {
        try {
            List<Long> goodsSkuIdList = getGoodsSkuId(t);
            List<GoodsStockDO> goodsStockDOList = createGoodsStockDOs(goodsSkuIdList);
            return createCommand(goodsStockDOList, t);
        } catch (Exception e) {
            log.error("error", e);
        }
        return null;
    }

    /**
     * 获取商品SKU ID集合
     *
     * @return 商品SKU ID集合
     * @throws Exception
     */
    protected abstract List<Long> getGoodsSkuId(T t) throws Exception;

    /**
     * 创建库存更新命令
     *
     * @param goodsStockDOList 商品库存DO对象集合
     * @return 库存更新命令
     * @throws Exception
     */
    protected abstract GoodsStockUpdateCommand createCommand(List<GoodsStockDO> goodsStockDOList, T t) throws Exception;

    /**
     * 创建商品库存DO对象集合:根据传入的商品SKU ID集合遍历是否存在库存记录,
     * 不存在则先创建,然后再将所有新建和已经存在的库存记录返回
     *
     * @param goodsSkuIds 商品SKU ID集合
     * @return 商品库存DO对象集合
     */
    protected List<GoodsStockDO> createGoodsStockDOs(List<Long> goodsSkuIds) throws Exception {
        List<GoodsStockDO> goodsStockDOList = new ArrayList<>(goodsSkuIds.size());

        for (Long goodsSkuId : goodsSkuIds) {
            GoodsStockDO goodsStockDO = goodsStockMapper.getGoodsStockBySkuId(goodsSkuId);
            if (goodsStockDO == null) {
                // 如果商品库存记录不存在则创建
                goodsStockDO = new GoodsStockDO();
                goodsStockDO.setGoodsSkuId(goodsSkuId);
                goodsStockDO.setSaleStockQuantity(0L);
                goodsStockDO.setLockedStockQuantity(0L);
                goodsStockDO.setSaledStockQuantity(0L);
                goodsStockDO.setStockStatus(StockStatus.NOT_IN_STOCK);
                Date currentTime = new Date();
                goodsStockDO.setGmtCreate(currentTime);
                goodsStockDO.setGmtModified(currentTime);

                // 保存商品库存记录
                goodsStockMapper.saveGoodsStock(goodsStockDO);
            }
            goodsStockDOList.add(goodsStockDO);
        }
        return goodsStockDOList;
    }

}
