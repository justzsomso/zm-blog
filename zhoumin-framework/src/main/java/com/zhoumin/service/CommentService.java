package com.zhoumin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhoumin.domain.ResponseResult;
import com.zhoumin.domain.entity.Comment;


/**
 * 评论表(Comment)表服务接口
 *
 * @author makejava
 * @since 2023-01-12 15:47:03
 */
public interface CommentService extends IService<Comment> {

    ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize);

    ResponseResult addComment(Comment comment, String token);
}

