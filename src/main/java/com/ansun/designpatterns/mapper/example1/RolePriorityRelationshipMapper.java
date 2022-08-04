package com.ansun.designpatterns.mapper.example1;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 角色与权限关系
 *
 * @author liuyaowu
 * @date 2022-08-03 14:36
 */
public interface RolePriorityRelationshipMapper {

    /**
     * 根据权限id查询记录数
     * @param priorityId 权限id
     * @return 记录数
     */
    @Select("SELECT count(*) "
            + "FROM auth_role_priority_relationship "
            + "WHERE priority_id=#{priorityId}")
    Long countByPriorityId(@Param("priorityId") Long priorityId);

}
