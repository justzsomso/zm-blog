package com.zhoumin.controller;

import com.zhoumin.annotation.SystemLog;
import com.zhoumin.domain.ResponseResult;
import com.zhoumin.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: zhouminBlog
 * @description: 标签控制类
 * @author: zhoumin
 * @create: 2023-04-04 11:20
 **/
@RestController
@RequestMapping("/content/tag")
@Api(tags = "标签相关接口")
public class TagController {

    @Autowired
    private TagService tagService;


    @SystemLog(businessName = "查询所有标签")
    @ApiOperation(value = "查询所有标签")
    @GetMapping("/list")
    public ResponseResult list(){
        return ResponseResult.okResult(tagService.list());
    }

//    @SystemLog(businessName = "删除指定标签")
//    @ApiOperation(value = "删除指定标签")
//    @GetMapping("/list")
//    public ResponseResult list(){
//        return ResponseResult.okResult(tagService.list());
//    }
}