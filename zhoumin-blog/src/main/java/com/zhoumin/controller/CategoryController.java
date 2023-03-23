package com.zhoumin.controller;

import com.zhoumin.annotation.SystemLog;
import com.zhoumin.domain.ResponseResult;
import com.zhoumin.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
@Api(tags = "文章分类相关接口")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getCategoryList")
    @SystemLog(businessName = "查询分类列表")
    @ApiOperation(value = "查询分类列表")
    public ResponseResult getCategoryList(){
        return categoryService.getCategoryList();
    }
}
