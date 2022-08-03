package com.ansun.designpatterns.example2.commandandtemplate;

/**
 * 商品库存更新命令接口
 *
 * @author liuyaowu
 * @date 2022-08-03 15:54
 */
public interface GoodsStockUpdateCommand {

    /**
     * 更新商品库存命令
     *
     * updateGoodsStock是对各种库存类型更新逻辑的一个统一封装,由于每次更新还需要更新库存状态以及更新时间,
     * 并且这两个处理逻辑都是一样的,因此可以使用模板方法,抽象出一个基类,将共有的方法放在父类中处理
     *
     * @return
     */
    Boolean updateGoodsStock();

}
