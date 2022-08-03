package com.ansun.designpatterns.example1.compositeandvisitor;

/**
 * 访问者
 *
 * @author liuyaowu
 * @date 2022-08-03 14:30
 */
public interface PriorityOperation<T> {

    /**
     * 访问者要执行的操作
     *
     * @param priority 权限节点
     * @return 处理结果
     * @throws Exception
     */
    T doExecute(Priority priority) throws Exception;

}
