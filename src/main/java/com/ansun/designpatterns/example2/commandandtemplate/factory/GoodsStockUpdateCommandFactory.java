package com.ansun.designpatterns.example2.commandandtemplate.factory;

import com.ansun.designpatterns.example2.commandandtemplate.GoodsStockUpdateCommand;

/**
 * 库存更新命令工厂接口
 */
public interface GoodsStockUpdateCommandFactory<T> {

    /**
     * 创建商品库存更新命令
     *
     * @param t 参数对象泛型
     * @return 库存更新命令
     */
    GoodsStockUpdateCommand createCommand(T t);

}