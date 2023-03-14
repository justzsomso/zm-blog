package com.zhoumin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhoumin.domain.ResponseResult;
import com.zhoumin.domain.entity.User;
import com.zhoumin.domain.vo.UserInfoVo;
import com.zhoumin.mapper.UserMapper;
import com.zhoumin.service.UserService;
import com.zhoumin.utils.BeanCopyUtils;
import com.zhoumin.utils.SecurityUtils;
import org.springframework.stereotype.Service;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2023-01-12 17:29:22
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public ResponseResult userInfo() {
        //获取当前用户id
        Long userId = SecurityUtils.getUserId();

        //根据用户id查询用户信息
        User userInfo = getById(userId);

        //封装userInfoVo
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(userInfo, UserInfoVo.class);

        return ResponseResult.okResult(userInfoVo);
    }
}

