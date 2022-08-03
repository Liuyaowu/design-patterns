package com.ansun.designpatterns.example1.compositeandvisitor;

import lombok.Data;

import java.util.Date;

/**
 * 权限节点
 *
 * @author liuyaowu
 * @date 2022-08-03 14:27
 */
@Data
public class Priority {

    /**
     * id
     */
    private Long id;

    /**
     * 权限编号
     */
    private String code;

    /**
     * 权限URL
     */
    private String url;

    /**
     * 权限备注
     */
    private String priorityComment;

    /**
     * 权限类型
     */
    private Integer priorityType;

    /**
     * 父权限id
     */
    private Long parentId;

    /**
     * 权限的创建时间
     */
    private Date gmtCreate;

    /**
     * 权限的修改时间
     */
    private Date gmtModified;

    /**
     * 接收一个权限树访问者
     *
     * @param operation 权限树访问者
     */
    public <T> T execute(PriorityOperation<T> operation) throws Exception {
        return operation.doExecute(this);
    }

}
