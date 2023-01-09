package com.zhoumin.controller;

import com.zhoumin.domain.ResponseResult;
import com.zhoumin.domain.entity.Article;
import com.zhoumin.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

//    @GetMapping("/list")
//    public List<Article> test(){
//        return articleService.list();
//    }

    @GetMapping("/hotArticleList")
    private ResponseResult hotArticleList(){
        //查询热门文章 封装后返回
        return articleService.hotArticleList();
    }

    //分页查询文章
    @GetMapping("/articleList")
    private ResponseResult articleList(Integer pageNum ,Integer pageSize ,Long categoryId){
        //查询热门文章 封装后返回
        return articleService.articleList(pageNum,pageSize,categoryId);
    }
}
