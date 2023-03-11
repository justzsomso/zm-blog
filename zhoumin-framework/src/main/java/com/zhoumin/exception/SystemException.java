package com.zhoumin.exception;

import com.zhoumin.enums.AppHttpCodeEnum;

/**
 * @program: zhouminBlog
 * @description: 统一异常处理
 * @author: zhoumin
 * @create: 2023-03-11 10:54
 **/
public class SystemException extends RuntimeException{
    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public SystemException(AppHttpCodeEnum httpCodeEnum) {
        super(httpCodeEnum.getMsg());
        this.code = httpCodeEnum.getCode();
        this.msg = httpCodeEnum.getMsg();
    }
}