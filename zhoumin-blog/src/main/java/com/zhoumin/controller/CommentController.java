package com.zhoumin.controller;

import com.zhoumin.annotation.SystemLog;
import com.zhoumin.constants.SystemConstants;
import com.zhoumin.domain.ResponseResult;
import com.zhoumin.domain.dao.AddCommentDto;
import com.zhoumin.domain.entity.Comment;
import com.zhoumin.service.CommentService;
import com.zhoumin.utils.BeanCopyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@Api(tags = "评论相关接口")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/commentList")
    @SystemLog(businessName = "查询评论列表")
    @ApiOperation(value = "查询评论列表")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "articleId", value = "文章id", required = true, paramType = "Long"),
            @ApiImplicitParam(name = "pageNum", value = "页号", required = true, paramType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, paramType = "Integer")
    }
    )
    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize){
        return commentService.commentList(SystemConstants.ARTICLE_TYPE_NORMAL,articleId,pageNum,pageSize);
    }

    @PostMapping
    @SystemLog(businessName = "发表评论")
    @ApiOperation(value = "发表评论")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "articleId", value = "文章id", required = true, paramType = "Long"),
            @ApiImplicitParam(name = "pageNum", value = "页号", required = true, paramType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, paramType = "Integer")
    }
    )
    public ResponseResult addComment(@RequestBody AddCommentDto commentDto, @RequestHeader(value = "token",required = true ) String token){
        Comment comment = BeanCopyUtils.copyBean(commentDto, Comment.class);
        return commentService.addComment(comment,token);
    }

    @GetMapping("/linkCommentList")
    @SystemLog(businessName = "友联评论列表")
    @ApiOperation(value = "友联评论列表")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "articleId", value = "文章id", required = true, paramType = "Long"),
            @ApiImplicitParam(name = "pageNum", value = "页号", required = true, paramType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, paramType = "Integer")
    }
    )
    public ResponseResult linkcommentList(Long articleId, Integer pageNum, Integer pageSize){
        return commentService.commentList(SystemConstants.ARTICLE_TYPE_DRAFT,null,pageNum,pageSize);
    }
}
