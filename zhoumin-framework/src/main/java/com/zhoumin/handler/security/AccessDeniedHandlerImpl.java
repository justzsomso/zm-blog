package com.zhoumin.handler.security;

import com.alibaba.fastjson.JSON;
import com.zhoumin.domain.ResponseResult;
import com.zhoumin.enums.AppHttpCodeEnum;
import com.zhoumin.utils.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.accessibility.AccessibleAction;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: zhouminBlog
 * @description: 授权失败处理器
 * @author: zhoumin
 * @create: 2023-03-10 21:14
 **/
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        e.printStackTrace();
        ResponseResult responseResult = ResponseResult.errorResult(AppHttpCodeEnum.NO_OPERATOR_AUTH);
        WebUtils.renderString(response, JSON.toJSONString(responseResult));
    }
}