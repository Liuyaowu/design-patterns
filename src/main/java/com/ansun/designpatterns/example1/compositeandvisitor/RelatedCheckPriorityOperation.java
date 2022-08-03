package com.ansun.designpatterns.example1.compositeandvisitor;

import cn.hutool.core.collection.CollUtil;
import com.ansun.designpatterns.convertor.PriorityDOConvertor;
import com.ansun.designpatterns.example1.entity.PriorityDO;
import com.ansun.designpatterns.mapper.example1.AccountPriorityRelationshipMapper;
import com.ansun.designpatterns.mapper.example1.PriorityMapper;
import com.ansun.designpatterns.mapper.example1.RolePriorityRelationshipMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 检查权限是否被关联的访问者
 *
 * @author liuyaowu
 * @date 2022-08-03 14:42
 */
@Component
@Scope("prototype")
public class RelatedCheckPriorityOperation implements PriorityOperation<Boolean> {

    /**
     * 权限
     */
    @Resource
    private PriorityMapper priorityMapper;
    /**
     * 角色和权限关系
     */
    @Resource
    private RolePriorityRelationshipMapper rolePriorityRelationshipMapper;
    /**
     * 账号和权限关系
     */
    @Resource
    private AccountPriorityRelationshipMapper accountPriorityRelationshipMapper;

    @Resource
    private PriorityDOConvertor priorityDOConvertor;

    /**
     * 访问权限树节点
     *
     * @param node 权限节点
     * @return
     * @throws Exception
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

        return relateCheck(node);
    }

    /**
     * 检查权限是否被任何一个角色或者是账号关联了
     *
     * @param node 权限树节点
     * @return 是否被任何一个角色或者是账号关联了，如果有关联则为true；如果没有关联则为false
     */
    private Boolean relateCheck(Priority node) throws Exception {
        Long roleRelatedCount = rolePriorityRelationshipMapper.countByPriorityId(node.getId());
        if (roleRelatedCount != null && roleRelatedCount > 0) {
            return true;
        }

        Long accountRelatedCount = accountPriorityRelationshipMapper.countByPriorityId(node.getId());
        if (accountRelatedCount != null && accountRelatedCount > 0) {
            return true;
        }

        return false;
    }

}
