package com.zhoumin.controller;

import com.zhoumin.annotation.SystemLog;
import com.zhoumin.domain.ResponseResult;
import com.zhoumin.domain.entity.Article;
import com.zhoumin.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @SystemLog(businessName = "查询热门文章")
    public ResponseResult hotArticleList(){
        //查询热门文章 封装后返回
        return articleService.hotArticleList();
    }

    //分页查询文章
    @GetMapping("/articleList")
    @SystemLog(businessName = "分页查询文章列表")
    public ResponseResult articleList(Integer pageNum ,Integer pageSize ,Long categoryId){
        //查询热门文章 封装后返回
        return articleService.articleList(pageNum,pageSize,categoryId);
    }


    @GetMapping("/{id}")
    @SystemLog(businessName = "文章详情")
    public ResponseResult getArticleDetail(@PathVariable("id") Long id){
        return articleService.getArticleDetail(id);
    }


    @PutMapping("/updateViewCount/{id}")
    @SystemLog(businessName = "更新文章浏览量")
    public ResponseResult updateViewCount(@PathVariable("id") Long id){
        return articleService.updateViewCount(id);
    }



}
