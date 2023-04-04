package com.zhoumin.service;


import com.zhoumin.domain.ResponseResult;
import com.zhoumin.domain.entity.User;

public interface AdminLoginService {

    ResponseResult login(User user);
}
