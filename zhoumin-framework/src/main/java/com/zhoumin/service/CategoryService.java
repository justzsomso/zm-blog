package com.zhoumin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhoumin.domain.ResponseResult;
import com.zhoumin.domain.entity.Category;


/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2023-01-06 13:34:53
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();
}

