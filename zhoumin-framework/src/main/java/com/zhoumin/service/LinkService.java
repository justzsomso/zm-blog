package com.zhoumin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhoumin.domain.ResponseResult;
import com.zhoumin.domain.entity.Link;


/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2023-01-10 13:26:37
 */
public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();
}

