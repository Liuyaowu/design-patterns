package com.ansun.designpatterns.example1.compositeandvisitor;

import cn.hutool.core.collection.CollUtil;
import com.ansun.designpatterns.convertor.PriorityDOConvertor;
import com.ansun.designpatterns.example1.entity.PriorityDO;
import com.ansun.designpatterns.mapper.example1.PriorityMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 删除节点访问者
 *
 * @author liuyaowu
 * @date 2022-08-03 15:01
 */
@Component
public class RemovePriorityOperation implements PriorityOperation<Boolean> {

    /**
     * 权限
     */
    @Resource
    private PriorityMapper priorityMapper;

    @Resource
    private PriorityDOConvertor priorityDOConvertor;

    /**
     * 访问权限树节点
     *
     * @param node 权限树节点
     */
    @Override
    public Boolean doExecute(Priority node) throws Exception {
        List<PriorityDO> priorityDOs = priorityMapper.listChildPriorities(node.getId());

        // 不是叶子节点则递归
        if (CollUtil.isNotEmpty(priorityDOs)) {
            for (PriorityDO priorityDO : priorityDOs) {
                Priority priorityNode = priorityDOConvertor.doToPriority(priorityDO);
                priorityNode.execute(this);
            }
        }

        return removePriority(node);
    }

    /**
     * 删除权限
     *
     * @param node 权限树节点
     */
    private Boolean removePriority(Priority node) throws Exception {
        return priorityMapper.removePriority(node.getId()) == 1;
    }

}
