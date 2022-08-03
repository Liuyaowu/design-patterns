package com.ansun.designpatterns.convertor;

import com.ansun.designpatterns.example1.compositeandvisitor.Priority;
import com.ansun.designpatterns.example1.entity.PriorityDO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriorityDOConvertor {
    Priority doToPriority(PriorityDO priorityDO);

}
