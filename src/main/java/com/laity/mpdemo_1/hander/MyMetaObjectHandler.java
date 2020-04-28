package com.laity.mpdemo_1.hander;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

//交由spring管理  自动填充
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {


    //使用mp实现添加操作，这个方法执行
    @Override
    public void insertFill(MetaObject metaObject) {
       //根据名称设置属性值
       this.setFieldValByName("createTime",new Date(),metaObject);
       this.setFieldValByName("updateTime",new Date(),metaObject);
       this.setFieldValByName("version",1,metaObject);
    }

    //使用mp实现修改操作，这个方法执行
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }
}
