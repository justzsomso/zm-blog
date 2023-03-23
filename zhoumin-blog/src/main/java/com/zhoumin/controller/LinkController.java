package com.zhoumin.controller;

import com.zhoumin.annotation.SystemLog;
import com.zhoumin.domain.ResponseResult;
import com.zhoumin.service.LinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/link")
@Api(tags = "友链相关接口")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @GetMapping("/getAllLink")
    @SystemLog(businessName = "友链查询")
    @ApiOperation(value = "友链查询")
    public ResponseResult getAllLink(){

        return linkService.getAllLink();
    }

}
