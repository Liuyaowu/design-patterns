package com.ansun.designpatterns.example2.commandandtemplate;

/**
 * 库存状态常量类
 */
public interface StockStatus {

	/**
	 * 有库存
	 */
	Integer IN_STOCK = 1;
	
	/**
	 * 无库存
	 */
	Integer NOT_IN_STOCK = 0;
	
}
