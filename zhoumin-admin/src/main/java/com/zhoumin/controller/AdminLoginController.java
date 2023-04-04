package com.zhoumin.controller;

import com.zhoumin.annotation.SystemLog;
import com.zhoumin.domain.ResponseResult;
import com.zhoumin.domain.dto.LoginUserDto;
import com.zhoumin.domain.entity.User;
import com.zhoumin.enums.AppHttpCodeEnum;
import com.zhoumin.exception.SystemException;
import com.zhoumin.service.AdminLoginService;
import com.zhoumin.utils.BeanCopyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: zhouminBlog
 * @description: 后台登录控制器
 * @author: zhoumin
 * @create: 2023-04-04 13:37
 **/
@RestController
@Api(tags = "登录注销相关接口")
public class AdminLoginController {

    @Autowired
    private AdminLoginService adminLoginService;


    @PostMapping("/user/login")
    @SystemLog(businessName = "登录接口")
    @ApiOperation(value = "登录接口")
    public ResponseResult login(@RequestBody LoginUserDto loginUserDto){
        if (!StringUtils.hasText(loginUserDto.getUserName())){
            //提示 必须要传用户名
//            throw new RuntimeException();
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        User user = BeanCopyUtils.copyBean(loginUserDto, User.class);
        return adminLoginService.login(user);
    }

}