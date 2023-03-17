package com.zhoumin.enums;

public enum AppHttpCodeEnum {
    // 成功
    SUCCESS(200,"操作成功"),
    // 登录
    NEED_LOGIN(401,"需要登录后操作"),
    NO_OPERATOR_AUTH(403,"无权限操作"),

    SYSTEM_ERROR(500,"出现错误"),

    REQUIRE_USERNAME(504, "必需填写用户名"),
    LOGIN_ERROR(505,"用户名或密码错误"),
    REQUIRE_comment(506, "内容不能为空"),
    FILE_TYPE_ERROR(507,"上传文件格式不符合要求"),

    //注册时判断注册条件 可能用到的错误代码
    USERNAME_NOT_NULL(601,"用户名不能为空"),
    NICKNAME_NOT_NULL(602,"昵称不能为空"),
    EMAIL_NOT_NULL(603,"邮箱不能为空"),
    PASSWORD_NOT_NULL(604,"密码不能为空"),
    USERNAME_EXIST(605,"用户名已存在"),
    NICKNAME_EXIST(606,"昵称已存在"),
    PHONENUMBER_EXIST(607,"手机号已存在"),
    EMAIL_EXIST(608, "邮箱已存在");

    //todo 重新整理所有的错误代码，按照提示功能进行分类









    int code;
    String msg;

    AppHttpCodeEnum(int code, String errorMessage){
        this.code = code;
        this.msg = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
