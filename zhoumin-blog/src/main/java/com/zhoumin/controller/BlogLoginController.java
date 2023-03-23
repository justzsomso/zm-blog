package com.zhoumin.controller;

import com.zhoumin.annotation.SystemLog;
import com.zhoumin.domain.ResponseResult;
import com.zhoumin.domain.entity.User;
import com.zhoumin.enums.AppHttpCodeEnum;
import com.zhoumin.exception.SystemException;
import com.zhoumin.service.BlogLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "登录注销相关接口")
public class BlogLoginController {

    @Autowired
    private BlogLoginService blogLoginService;

    @PostMapping("/login")
    @SystemLog(businessName = "登录接口")
    @ApiOperation(value = "登录接口")
    public ResponseResult login(@RequestBody User user){
        if (!StringUtils.hasText(user.getUserName())){
            //提示 必须要传用户名
//            throw new RuntimeException();
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return blogLoginService.login(user);
    }


    @PostMapping("/logout")
    @SystemLog(businessName = "退出登录接口")
    @ApiOperation(value = "退出登录接口")
    public ResponseResult logout(){
        return blogLoginService.logout();
    }
}
