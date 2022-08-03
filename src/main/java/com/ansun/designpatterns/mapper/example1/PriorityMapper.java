package com.ansun.designpatterns.mapper.example1;

import com.ansun.designpatterns.example1.entity.PriorityDO;

import java.util.List;

/**
 * 权限
 *
 * @author liuyaowu
 * @date 2022-08-03 14:36
 */
public interface PriorityMapper {

    List<PriorityDO> listChildPriorities(Long id);

    Integer removePriority(Long id);

    PriorityDO getPriorityById(Long id);

}
