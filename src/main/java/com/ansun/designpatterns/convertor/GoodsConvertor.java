package com.ansun.designpatterns.convertor;

import com.ansun.designpatterns.example4.state.dto.GoodsDTO;
import com.ansun.designpatterns.example4.state.entity.GoodsDO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface GoodsConvertor {
    GoodsDO dtoToDo(GoodsDTO goods);

    GoodsDTO doToDto(GoodsDO byId);

}
