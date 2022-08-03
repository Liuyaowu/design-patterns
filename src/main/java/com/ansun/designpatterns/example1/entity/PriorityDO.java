package com.ansun.designpatterns.example1.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * @author liuyaowu
 * @date 2022-08-03 14:48
 */
@Data
@ToString
@EqualsAndHashCode
public class PriorityDO {

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

}
