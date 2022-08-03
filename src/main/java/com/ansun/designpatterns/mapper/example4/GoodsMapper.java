package com.ansun.designpatterns.mapper.example4;

import com.ansun.designpatterns.example4.state.entity.GoodsDO;
import org.apache.ibatis.annotations.*;

/**
 * 商品
 *
 * @author liuyaowu
 * @date 2022-08-03 17:33
 */
public interface GoodsMapper {

    /**
     * 根据id查询商品
     * @param id 商品id
     * @return 商品
     */
    @Select("SELECT "
            + "id,"
            + "category_id,"
            + "brand_id,"
            + "code,"
            + "name,"
            + "sub_name,"
            + "gross_weight,"
            + "length,"
            + "width,"
            + "height,"
            + "status,"
            + "service_guarantees,"
            + "package_list,"
            + "freight_template_id,"
            + "gmt_create,"
            + "gmt_modified "
            + "FROM commodity_goods "
            + "WHERE id=#{id} ")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "brand_id", property = "brandId"),
            @Result(column = "code", property = "code"),
            @Result(column = "name", property = "name"),
            @Result(column = "sub_name", property = "subName"),
            @Result(column = "gross_weight", property = "grossWeight"),
            @Result(column = "length", property = "length"),
            @Result(column = "width", property = "width"),
            @Result(column = "height", property = "height"),
            @Result(column = "status", property = "status"),
            @Result(column = "service_guarantees", property = "serviceGuarantees"),
            @Result(column = "package_list", property = "packageList"),
            @Result(column = "freight_template_id", property = "freightTemplateId"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    GoodsDO getById(@Param("id") Long id);

    /**
     * 新增商品
     * @param goods 商品
     */
    @Insert("INSERT INTO commodity_goods("
            + "category_id,"
            + "brand_id,"
            + "code,"
            + "name,"
            + "sub_name,"
            + "gross_weight,"
            + "length,"
            + "width,"
            + "height,"
            + "status,"
            + "service_guarantees,"
            + "package_list,"
            + "freight_template_id,"
            + "gmt_create,"
            + "gmt_modified"
            + ") VALUES("
            + "#{categoryId},"
            + "#{brandId},"
            + "#{code},"
            + "#{name},"
            + "#{subName},"
            + "#{grossWeight},"
            + "#{length},"
            + "#{width},"
            + "#{height},"
            + "#{status},"
            + "#{serviceGuarantees},"
            + "#{packageList},"
            + "#{freightTemplateId},"
            + "#{gmtCreate},"
            + "#{gmtModified}"
            + ")")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    Long save(GoodsDO goods);

    /**
     * 更新商品
     * @param goods 商品
     */
    @Update("UPDATE commodity_goods SET "
            + "category_id=#{categoryId},"
            + "brand_id=#{brandId},"
            + "code=#{code},"
            + "name=#{name},"
            + "sub_name=#{subName},"
            + "gross_weight=#{grossWeight},"
            + "length=#{length},"
            + "width=#{width},"
            + "height=#{height},"
            + "service_guarantees=#{serviceGuarantees},"
            + "package_list=#{packageList},"
            + "freight_template_id=#{freightTemplateId},"
            + "gmt_modified=#{gmtModified} "
            + " WHERE id=#{id}")
    void update(GoodsDO goods);

    /**
     * 更新商品状态
     * @param goods 商品
     */
    @Update("UPDATE commodity_goods SET "
            + "status=#{status},  "
            + "gmt_modified=#{gmtModified} "
            + " WHERE id=#{id}")
    void updateStatus(GoodsDO goods);

}
