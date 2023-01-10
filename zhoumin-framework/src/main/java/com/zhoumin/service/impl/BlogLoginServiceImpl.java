package com.zhoumin.service.impl;

import com.zhoumin.domain.ResponseResult;
import com.zhoumin.domain.entity.LoginUser;
import com.zhoumin.domain.entity.User;
import com.zhoumin.domain.vo.BlogUserLoginVo;
import com.zhoumin.domain.vo.UserInfoVo;
import com.zhoumin.service.BlogLoginService;
import com.zhoumin.utils.BeanCopyUtils;
import com.zhoumin.utils.JwtUtil;
import com.zhoumin.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BlogLoginServiceImpl implements BlogLoginService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //判断是否通过认证
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或密码错误");
        }

        //获取userId 生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        Long loginUserId = loginUser.getUser().getId();
        String jwt = JwtUtil.createJWT(String.valueOf(loginUserId));
        //将用户信息存入到redis
        redisCache.setCacheObject("bloglogin" + loginUserId, loginUser);

        //将token和userinfo封装 返回
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        BlogUserLoginVo vo = new BlogUserLoginVo(jwt, userInfoVo);
        return ResponseResult.okResult(vo);
    }
}
