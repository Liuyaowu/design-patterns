package com.ansun.designpatterns.convertor;

import com.ansun.designpatterns.example4.state.dto.GoodsDTO;
import com.ansun.designpatterns.example4.state.entity.GoodsDO;
import org.mapstruct.Mapper;

/**
 * GoodsDTO对象转换器
 */

@Mapper(componentModel = "spring")
public interface GoodsDTOConvertor {

    /**
     * GoodsDTO转GoodsDO
     *
     * @param dto
     * @return
     */
    GoodsDO dtoToDo(GoodsDTO dto);

}
