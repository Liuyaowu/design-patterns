package com.ansun.designpatterns.example1.compositeandvisitor.service;

import com.ansun.designpatterns.convertor.PriorityDOConvertor;
import com.ansun.designpatterns.example1.compositeandvisitor.Priority;
import com.ansun.designpatterns.example1.compositeandvisitor.RelatedCheckPriorityOperation;
import com.ansun.designpatterns.example1.compositeandvisitor.RemovePriorityOperation;
import com.ansun.designpatterns.mapper.example1.PriorityMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 权限
 *
 * @author liuyaowu
 * @date 2022-08-03 15:10
 */
@Service
public class PriorityService {

    @Resource
    private PriorityDOConvertor priorityDOConvertor;
    @Resource
    private PriorityMapper priorityMapper;

    @Resource
    private RelatedCheckPriorityOperation relatedCheckPriorityOperation;
    @Resource
    private RemovePriorityOperation removePriorityOperation;

    public Boolean removePriority(Long id) throws Exception {
        // 根据id查询权限
        Priority priority = priorityDOConvertor.doToPriority(priorityMapper.getPriorityById(id));

        // 检查这个权限以及其下任何一个子权限,是否被角色或者账号给关联着
        if (relatedCheckPriorityOperation.doExecute(priority)) {
            return false;
        }

        // 删除该节点以及其子节点
        return removePriorityOperation.doExecute(priority);
    }

}
