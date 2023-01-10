package com.zhoumin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhoumin.constants.SystemConstants;
import com.zhoumin.domain.ResponseResult;
import com.zhoumin.domain.entity.Link;
import com.zhoumin.domain.vo.LinkVo;
import com.zhoumin.mapper.LinkMapper;
import com.zhoumin.service.LinkService;
import com.zhoumin.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 友链(Link)表服务实现类
 *
 * @author makejava
 * @since 2023-01-10 13:26:39
 */
@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Override
    public ResponseResult getAllLink() {
        //查询所有审核通过的友情链接
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper();

        queryWrapper.eq(Link::getStatus, SystemConstants.Link_STATUS_NORMAL);

        List<Link> list = list(queryWrapper);
        //转换成vo
        List<LinkVo> LinkVoList = BeanCopyUtils.copyBeanList(list, LinkVo.class);
        //封装返回
        return ResponseResult.okResult(LinkVoList);

    }
}

