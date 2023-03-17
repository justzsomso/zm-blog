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

    @Override
    public ResponseResult updateUserInfo(User user) {
        //todo 这里更新接口严谨一点需要先查看是否存在该用户，另外更新操作后需要删除redis缓存，最后更新完成后将信息更新到redis


        //todo 优化 这里会有一些问题：导致user的其他信息被修改
        //建议过滤一下信息之后进行update操作
        updateById(user);
        return ResponseResult.okResult();
    }
}

