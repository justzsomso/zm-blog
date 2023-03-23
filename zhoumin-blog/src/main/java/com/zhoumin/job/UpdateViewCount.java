package com.zhoumin.job;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.zhoumin.domain.entity.Article;
import com.zhoumin.mapper.ArticleMapper;
import com.zhoumin.service.ArticleService;
import com.zhoumin.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @program: zm-blog
 * @description: 定时任务将redis数据更新到数据库
 * @author: zhoumin
 * @create: 2023-03-23 15:15
 **/
@Component
public class UpdateViewCount {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ArticleService articleService;

    @Scheduled(cron = "0 0/10 * * * ?")
    public void UpdateViewCount(){
        //获取redis中的浏览量

        Map<String, Integer> articleViewCountMap = redisCache.getCacheMap("articleViewCount");

        List<Article> articleList = articleViewCountMap.entrySet()
                .stream()
                .map(entry -> new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue()))
                .collect(Collectors.toList());

        for (Article article : articleList) {
            LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(Article :: getId, article.getId());
            updateWrapper.set(Article :: getViewCount, article.getViewCount());
            articleService.update(updateWrapper);
        }
    }
}