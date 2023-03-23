package com.zhoumin.controller;

import com.zhoumin.annotation.SystemLog;
import com.zhoumin.domain.ResponseResult;
import com.zhoumin.domain.entity.Article;
import com.zhoumin.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.NamedBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
@Api(tags = "文章相关接口")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

//    @GetMapping("/list")
//    public List<Article> test(){
//        return articleService.list();
//    }

    @GetMapping("/hotArticleList")
    @SystemLog(businessName = "查询热门文章")
    @ApiOperation(value = "查询热门文章")
    public ResponseResult hotArticleList() {
        //查询热门文章 封装后返回
        return articleService.hotArticleList();
    }

    //分页查询文章
    @GetMapping("/articleList")
    @SystemLog(businessName = "分页查询文章列表")
    @ApiOperation(value = "分页查询文章列表")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "pageNum", value = "页号", required = true, paramType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, paramType = "Integer"),
            @ApiImplicitParam(name = "categoryId", value = "分类id", required = true, paramType = "Integer")
    }
    )
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        //查询热门文章 封装后返回
        return articleService.articleList(pageNum, pageSize, categoryId);
    }


    @GetMapping("/{id}")
    @SystemLog(businessName = "文章详情")
    @ApiOperation(value = "文章详情")
    @ApiImplicitParam(name = "id", value = "文章id")
    public ResponseResult getArticleDetail(@PathVariable("id") Long id) {
        return articleService.getArticleDetail(id);
    }


    @PutMapping("/updateViewCount/{id}")
    @SystemLog(businessName = "更新文章浏览量")
    @ApiOperation(value = "更新文章浏览量")
    @ApiImplicitParam(name = "id", value = "文章id")
    public ResponseResult updateViewCount(@PathVariable("id") Long id) {
        return articleService.updateViewCount(id);
    }


}
