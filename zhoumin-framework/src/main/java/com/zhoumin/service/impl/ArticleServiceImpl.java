package com.zhoumin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhoumin.domain.ResponseResult;
import com.zhoumin.domain.entity.Article;
import com.zhoumin.mapper.ArticleMapper;
import com.zhoumin.service.ArticleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Override
    public ResponseResult hotArticleList() {
        //查询结果，封装后返回
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //非草稿，按浏览量降序查出前十条
        queryWrapper.eq(Article::getStatus,0);
        queryWrapper.orderByDesc(Article::getViewCount);
        Page<Article> page = new Page<>(1,10);
        page(page,queryWrapper);
        List<Article> articleList = page.getRecords();
        return ResponseResult.okResult(articleList);
    }
}
