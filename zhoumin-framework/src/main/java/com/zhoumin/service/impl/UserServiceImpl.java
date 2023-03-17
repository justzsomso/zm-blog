package com.zhoumin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhoumin.domain.ResponseResult;
import com.zhoumin.domain.entity.User;
import com.zhoumin.domain.vo.UserInfoVo;
import com.zhoumin.enums.AppHttpCodeEnum;
import com.zhoumin.exception.SystemException;
import com.zhoumin.mapper.UserMapper;
import com.zhoumin.service.UserService;
import com.zhoumin.utils.BeanCopyUtils;
import com.zhoumin.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.net.PasswordAuthentication;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2023-01-12 17:29:22
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

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

        //这里也需要进行判重操作

        //todo 优化 这里会有一些问题：导致user的其他信息被修改
        //建议过滤一下信息之后进行update操作
        updateById(user);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult register(User user) {
        //非空判断
        if (!StringUtils.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
        }
        if (!StringUtils.hasText(user.getPassword())){
            throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
        }
        if (!StringUtils.hasText(user.getNickName())){
            throw new SystemException(AppHttpCodeEnum.NICKNAME_NOT_NULL);
        }
        if (!StringUtils.hasText(user.getEmail())){
            throw new SystemException(AppHttpCodeEnum.EMAIL_NOT_NULL);
        }

        //重复判断
        if (isUserNameExist(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }

        if (isNickNameExist(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.NICKNAME_EXIST);
        }
        //todo 这里可能还有需要进行判空的地方


        //密码加密
        String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);

        //更新数据库
        save(user);

        return ResponseResult.okResult();
    }

    private boolean isNickNameExist(String nickName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(User::getNickName,nickName);
        return count(queryWrapper)>0;
    }

    private boolean isUserNameExist(String userName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(User::getUserName,userName);
        return count(queryWrapper)>0;
    }


}

