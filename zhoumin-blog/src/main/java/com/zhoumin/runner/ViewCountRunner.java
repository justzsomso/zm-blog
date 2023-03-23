package com.zhoumin.runner;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhoumin.domain.entity.Article;
import com.zhoumin.mapper.ArticleMapper;
import com.zhoumin.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@Component
public class ViewCountRunner implements CommandLineRunner {

    /*
    这里为啥不注入service
    是因为直接查询，不涉及业务，不涉及修改信息
     */
    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private RedisCache redisCache;

    @Override
    public void run(String... args) throws Exception {
        //查询博客信息
        List<Article> articleList = articleMapper.selectList(null);
        Map<String, Integer> viewCountMap = articleList.stream()
                .collect(Collectors.toMap(article -> article.getId().toString(), article -> {
                    return article.getViewCount().intValue();//
                }));

        //存到redis中
        redisCache.setCacheMap("articleViewCount",viewCountMap);
    }
}
