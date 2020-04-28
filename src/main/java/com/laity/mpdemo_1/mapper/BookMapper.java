package com.laity.mpdemo_1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laity.mpdemo_1.entity.Book;
import org.springframework.stereotype.Repository;

@Repository
public interface BookMapper extends BaseMapper<Book> {
}
