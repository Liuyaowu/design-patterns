package com.ansun.designpatterns.mapper.example1;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 账号和角色权限
 *
 * @author liuyaowu
 * @date 2022-08-03 14:37
 */
public interface AccountPriorityRelationshipMapper {

    /**
     * 根据权限id查询记录数
     * @param priorityId 权限id
     * @return 记录数
     */
    @Select("SELECT count(*) "
            + "FROM auth_account_priority_relationship "
            + "WHERE priority_id=#{priorityId}")
    Long countByPriorityId(@Param("priorityId") Long priorityId);

}
