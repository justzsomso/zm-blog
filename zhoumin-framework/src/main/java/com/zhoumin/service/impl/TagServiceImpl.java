package com.zhoumin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhoumin.domain.entity.Tag;
import com.zhoumin.mapper.TagMapper;
import com.zhoumin.service.TagService;
import org.springframework.stereotype.Service;

/**
 * 标签(Tag)表服务实现类
 *
 * @author 
 * @since 2023-04-03 15:55:05
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

}

