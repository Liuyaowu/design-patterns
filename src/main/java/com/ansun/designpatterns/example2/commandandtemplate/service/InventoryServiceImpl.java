package com.ansun.designpatterns.example2.commandandtemplate.service;

import com.ansun.designpatterns.example2.commandandtemplate.GoodsStockUpdateCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 库存中心对外接口service组件
 */
@Service
@Slf4j
public class InventoryServiceImpl  {

    /**
     * 采购入库库存更新命令工厂
     */
    @Resource
    private PurchaseInputStockUpdateCommandFactory<PurchaseInputOrderDTO> purchaseInputStockUpdateCommandFactory;
    /**
     * 退货入库库存更新命令工厂
     */
    @Resource
    private ReturnGoodsInputStockUpdateCommandFactory<ReturnGoodsInputOrderDTO> returnGoodsInputStockUpdateCommandFactory;

    /**
     * 通知库存中心"完成退货入库"事件发生了
     *
     * @param returnGoodsInputOrderDTO 退货入库单DTO
     * @return 处理结果
     */
    public Boolean informReturnGoodsInputFinished(ReturnGoodsInputOrderDTO returnGoodsInputOrderDTO) {
        GoodsStockUpdateCommand command = returnGoodsInputStockUpdateCommandFactory.createCommand(returnGoodsInputOrderDTO);
        if (command == null) {
            return false;
        }
        return command.updateGoodsStock();
    }

    /**
     * 通知库存中心"完成采购入库"事件发生了
     *
     * @param purchaseInputOrderDTO 采购入库单DTO
     * @return
     */
    public Boolean informPurchaseInputFinishedEvent(PurchaseInputOrderDTO purchaseInputOrderDTO) {
        GoodsStockUpdateCommand command = purchaseInputStockUpdateCommandFactory.createCommand(purchaseInputOrderDTO);
        if (command == null) {
            return false;
        }
        return command.updateGoodsStock();
    }

}
