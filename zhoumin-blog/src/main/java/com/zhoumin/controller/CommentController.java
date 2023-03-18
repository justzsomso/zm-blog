package com.zhoumin.controller;

import com.zhoumin.annotation.SystemLog;
import com.zhoumin.constants.SystemConstants;
import com.zhoumin.domain.ResponseResult;
import com.zhoumin.domain.entity.Comment;
import com.zhoumin.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/commentList")
    @SystemLog(businessName = "查询评论列表")
    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize){
        return commentService.commentList(SystemConstants.ARTICLE_TYPE_NORMAL,articleId,pageNum,pageSize);
    }

    @PostMapping
    @SystemLog(businessName = "发表评论")
    public ResponseResult addComment(@RequestBody Comment comment,@RequestHeader(value = "token",required = true ) String token){
        return commentService.addComment(comment,token);
    }

    @GetMapping("/linkCommentList")
    @SystemLog(businessName = "友联评论列表")
    public ResponseResult linkcommentList(Long articleId, Integer pageNum, Integer pageSize){
        return commentService.commentList(SystemConstants.ARTICLE_TYPE_DRAFT,null,pageNum,pageSize);
    }
}
