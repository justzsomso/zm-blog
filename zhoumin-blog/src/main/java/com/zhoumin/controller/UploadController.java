package com.zhoumin.controller;

import com.zhoumin.annotation.SystemLog;
import com.zhoumin.domain.ResponseResult;
import com.zhoumin.service.UploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Api(tags = "上传相关接口")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("/upload")
    @SystemLog(businessName = "上传用户信息")
    @ApiOperation(value = "上传用户信息")
    @ApiImplicitParam(name = "img", value = "图片", required = true, paramType = "MultipartFile")
    public ResponseResult uploadImg(MultipartFile img){
        return uploadService.uploadImg(img);
    }
}
