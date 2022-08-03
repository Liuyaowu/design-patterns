package com.ansun.designpatterns.mapper.example1;

import com.ansun.designpatterns.example1.entity.PriorityDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 权限
 *
 * @author liuyaowu
 * @date 2022-08-03 14:36
 */
public interface PriorityMapper {

    /**
     * 根据父权限id查询子权限
     * @param parentId 父权限id
     * @return 子权限
     */
    @Select("SELECT "
            + "id,"
            + "code,"
            + "url,"
            + "priority_comment,"
            + "priority_type,"
            + "parent_id,"
            + "gmt_create,"
            + "gmt_modified "
            + "FROM auth_priority "
            + "WHERE parent_id = #{parentId}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "code", property = "code"),
            @Result(column = "url", property = "url"),
            @Result(column = "priority_comment", property = "priorityComment"),
            @Result(column = "priority_type", property = "priorityType"),
            @Result(column = "parent_id", property = "parentId"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    List<PriorityDO> listChildPriorities(@Param("parentId") Long parentId);

    /**
     * 删除权限
     *
     * @param id 权限id
     */
    @Delete("DELETE FROM auth_priority WHERE id=#{id}")
    Integer removePriority(@Param("id") Long id);

    /**
     * 根据id查询权限
     *
     * @param id 权限id
     * @return 权限
     */
    @Select("SELECT "
            + "id,"
            + "code,"
            + "url,"
            + "priority_comment,"
            + "priority_type,"
            + "parent_id,"
            + "gmt_create,"
            + "gmt_modified "
            + "FROM auth_priority "
            + "WHERE id = #{id}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "code", property = "code"),
            @Result(column = "url", property = "url"),
            @Result(column = "priority_comment", property = "priorityComment"),
            @Result(column = "priority_type", property = "priorityType"),
            @Result(column = "parent_id", property = "parentId"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modified", property = "gmtModified")
    })
    PriorityDO getPriorityById(@Param("id") Long id);

}
