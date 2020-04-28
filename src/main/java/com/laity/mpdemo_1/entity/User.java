package com.laity.mpdemo_1.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User  {
    /**
     * @TableId(type = IdType.AUTO)
     * AUTO自动增长
     * INPUT :手动输入 设置id值
     * NONE: 输入 没有任何策略
     * UUID: 没次生成UUID 随机唯一值
     * MP自带策略：
     * ID_WORKER mp自带策略 ，生成19位值，数字类型使用这种策略 比如long类型,
     * ID_WORKER_STR  字符串类型使用，其余同上
     */


    @TableId(value = "id",type = IdType.ID_WORKER_STR)
    private String id;
    private String name;
    private Integer age;
    private String email;


    //自动填充属性的步骤  官方网站 https://mp.baomidou.com/guide/auto-fill-metainfo.html
    //1. 添加mp的自动填充属性的注解
    //2.创建类 实现接口，implements MetaObjectHandler 然后实现接口里的方法

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    //添加或者修改的时候都会给updateTime赋值
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


    // 事务：
    // 读问题：脏读,不可重复读，幻读
    //写问题 ：丢失更新问题 ：后提交的数据会覆盖先提交的数据,解决方法， //使用悲观锁或者是乐观锁
    /**
     *  乐观锁: 主要用来解决丢失更新的问题,用version 版本号来确定 ，
     *  在最终提交事务的时候比较当前数据版本和数据库版本是否一样，
     *  一样才能最终提交事务，并且将数据库版本+1
     *  具体实现
     *  1.表添加字段，作为乐观锁版本号
     *  2，对应实体类添加版本号属性
     *  3.在实体类版本号属性里边添加注解  @Version
     *  4.
     *
     *
     */
    //悲观锁：一次只能有一个人执行操作

    @Version
    @TableField(fill = FieldFill.INSERT) //做新增操作时自动填充
    private Integer version; //版本号


    @TableLogic    //添加逻辑或物理删除注解
    private Integer deleted; //逻辑/物理删除      0/1
























}
