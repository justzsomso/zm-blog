package com.zhoumin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhoumin.constants.SystemConstants;
import com.zhoumin.domain.ResponseResult;
import com.zhoumin.domain.entity.Comment;
import com.zhoumin.domain.vo.CommentVo;
import com.zhoumin.domain.vo.PageVo;
import com.zhoumin.enums.AppHttpCodeEnum;
import com.zhoumin.exception.SystemException;
import com.zhoumin.mapper.CommentMapper;
import com.zhoumin.service.CommentService;
import com.zhoumin.service.UserService;
import com.zhoumin.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author makejava
 * @since 2023-01-12 15:47:04
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private UserService userService;


    @Override
    public ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize) {

        //查询对应文章的根评论
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper();
        //通过articleId来进行查询
        queryWrapper.eq(SystemConstants.ARTICLE_TYPE_NORMAL.equals(commentType),Comment::getId, articleId);
        //根评论的rootId是-1
        queryWrapper.eq(Comment::getRootId, SystemConstants.COMMENT_ROOTID_DRAFT);

        queryWrapper.eq(Comment::getType,commentType);

        //分页查询
        Page<Comment> page = new Page(pageNum, pageSize);
        page(page, queryWrapper);

        //todo 优化是否有办法一次性全都取出来
        List<CommentVo> commentVoList = toCommentList(page.getRecords());

        //查询所有根评论对应的子评论的集合，并且赋值给对应的属性
        for (CommentVo commentVo : commentVoList) {
            //查询的子评论
            List<CommentVo> children = getChildrenList(commentVo.getId());

            //赋值
            commentVo.setChildren(children);
        }
        return ResponseResult.okResult(new PageVo(commentVoList, page.getTotal()));
    }

    @Override
    public ResponseResult addComment(Comment comment, String token) {

        //校验是否有内容
        if (StringUtils.hasText(comment.getContent())){
            throw  new SystemException(AppHttpCodeEnum.REQUIRE_comment);
        }
        //校验是否已经登录
        if (StringUtils.hasText(token)){
            throw  new SystemException(AppHttpCodeEnum.NO_OPERATOR_AUTH);
        }
        save(comment);
        return ResponseResult.okResult();
    }

    /**
     * @param id 根评论id
     * @return
     */
    private List<CommentVo> getChildrenList(Long id) {
        LambdaQueryWrapper<Comment> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Comment::getRootId, id);
        lambdaQueryWrapper.orderByAsc(Comment::getCreateTime);

        List<Comment> list = list(lambdaQueryWrapper);
        List<CommentVo> commentVos = toCommentList(list);
        return commentVos;
    }

    private List<CommentVo> toCommentList(List<Comment> list) {
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(list, CommentVo.class);
        for (CommentVo commentVo : commentVos) {
            String nickName = userService.getById(commentVo.getCreateBy()).getNickName();
            commentVo.setUsername(nickName);

            //注意需要在tocommentUserId不为-1的时候进行
            if (commentVo.getToCommentId() != -1) {
                String getToCommentUsername = userService.getById(commentVo.getToCommentUserId()).getNickName();
                commentVo.setUsername(getToCommentUsername);
            }
        }
        return commentVos;
    }
}

