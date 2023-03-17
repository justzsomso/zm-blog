package com.zhoumin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhoumin.domain.ResponseResult;
import com.zhoumin.domain.entity.User;


/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2023-01-12 17:29:22
 */
public interface UserService extends IService<User> {

    ResponseResult userInfo();

    ResponseResult updateUserInfo(User user);

    ResponseResult register(User user);
}

