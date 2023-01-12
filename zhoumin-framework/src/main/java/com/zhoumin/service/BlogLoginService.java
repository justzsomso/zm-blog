package com.zhoumin.service;

import com.zhoumin.domain.ResponseResult;
import com.zhoumin.domain.entity.User;

public interface BlogLoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
