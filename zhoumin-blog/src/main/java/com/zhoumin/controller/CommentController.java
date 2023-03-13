package com.zhoumin.controller;

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
    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize){
        return commentService.commentList(articleId,pageNum,pageSize);
    }

    @PostMapping
    public ResponseResult addComment(@RequestBody Comment comment,@RequestHeader(value = "token",required = true ) String token){
        return commentService.addComment(comment,token);
    }


}
