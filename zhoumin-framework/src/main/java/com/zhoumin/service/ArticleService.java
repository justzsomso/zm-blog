package com.zhoumin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhoumin.domain.ResponseResult;
import com.zhoumin.domain.entity.Article;

public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);
}
