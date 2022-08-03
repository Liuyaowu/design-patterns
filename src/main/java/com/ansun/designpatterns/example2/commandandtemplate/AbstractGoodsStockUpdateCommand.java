package com.ansun.designpatterns.example2.commandandtemplate;

import com.ansun.designpatterns.example2.entity.GoodsStockDO;
import com.ansun.designpatterns.mapper.example2.GoodsStockMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;

/**
 * 商品库存更新命令抽象类
 * <p>
 * 父类中在updateGoodsStock方法中封装好了具体的业务流程,但是销售库存、已售库存、锁定库存需要根据不同的业务去处理,
 * 因为不同的业务需要处理不同的库存,比如采购入库时已售库存不用管,但是退货入库已售库存需要减去退货数量
 */
@Slf4j
public abstract class AbstractGoodsStockUpdateCommand implements GoodsStockUpdateCommand {

    /**
     * 商品库存DO
     */
    protected List<GoodsStockDO> goodsStockDOList;

    /**
     * 商品库存
     */
    protected GoodsStockMapper goodsStockMapper;

    /**
     * 构造函数
     *
     * @param goodsStockDOList 商品库存DO集合
     * @param goodsStockMapper 商品库存mapper
     */
    public AbstractGoodsStockUpdateCommand(List<GoodsStockDO> goodsStockDOList,
                                           GoodsStockMapper goodsStockMapper) {
        this.goodsStockDOList = goodsStockDOList;
        this.goodsStockMapper = goodsStockMapper;
    }

    /**
     * 模板方法:相同的方法放在父类中处理,不同的实现交给具体的子类自己处理实现
     *
     * @return
     */
    @Override
    public Boolean updateGoodsStock() {
        try {
            // 这里只是对实体类中的数据做变更
            updateSaleStockQuantity();
            updateLockedStockQuantity();
            updateSoldStockQuantity();
            updateStockStatus();

            updateGmtModifiedTime();

            // 此处才真正执行sql操作修改语句
            executeUpdateGoodsStock();
        } catch (Exception e) {
            log.error("error", e);
        }
        return true;
    }

    /**
     * 更新销售库存
     */
    protected abstract void updateSaleStockQuantity() throws Exception;

    /**
     * 更新锁定库存
     */
    protected abstract void updateLockedStockQuantity() throws Exception;

    /**
     * 更新已销售库存
     */
    protected abstract void updateSoldStockQuantity() throws Exception;

    /**
     * 更新库存状态:0=无库存,1=有库存
     */
    private void updateStockStatus() {
        for (GoodsStockDO goodsStockDO : goodsStockDOList) {
            goodsStockDO.setStockStatus(goodsStockDO.getSaleStockQuantity() > 0 ? StockStatus.IN_STOCK : StockStatus.NOT_IN_STOCK);
        }
    }

    /**
     * 更新修改时间
     */
    private void updateGmtModifiedTime() throws Exception {
        for (GoodsStockDO goodsStockDO : goodsStockDOList) {
            goodsStockDO.setGmtModified(new Date());
        }
    }

    /**
     * 操作数据库执行更新库存的操作:调用库存mapper操作更新库存方法
     *
     * @throws Exception
     */
    private void executeUpdateGoodsStock() throws Exception {
        for (GoodsStockDO goodsStockDO : goodsStockDOList) {
            goodsStockMapper.updateGoodsStock(goodsStockDO);
        }
    }

}