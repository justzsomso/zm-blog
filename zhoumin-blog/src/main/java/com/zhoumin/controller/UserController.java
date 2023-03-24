package com.zhoumin.controller;

import com.zhoumin.annotation.SystemLog;
import com.zhoumin.domain.ResponseResult;
import com.zhoumin.domain.entity.User;
import com.zhoumin.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Api(tags = "用户相关接口")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userInfo")
    @SystemLog(businessName = "个人信息查询")
    @ApiOperation(value = "个人信息查询")
    public ResponseResult userInfo(){
        return userService.userInfo();
    }

    //todo 更新个人信息接口更新的内容有什么在创建一个dto
    @PostMapping("/userInfo")
    @SystemLog(businessName = "更新个人信息")
    @ApiOperation(value = "更新个人信息")
    @ApiImplicitParam(name = "user", value = "人员信息", required = true, paramType = "User")
    public ResponseResult updateUserInfo(@RequestBody User user){
        return userService.updateUserInfo(user);
    }

    //todo 用户注册接口更新的内容有什么在创建一个dto
    @PostMapping("/register")
    @SystemLog(businessName = "用户注册")
    @ApiOperation(value = "用户注册")
    @ApiImplicitParam(name = "user", value = "人员信息", required = true, paramType = "User")
    public ResponseResult register(@RequestBody User user){
        return userService.register(user);
    }
}
