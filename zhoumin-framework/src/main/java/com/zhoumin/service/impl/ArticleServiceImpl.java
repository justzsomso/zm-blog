package com.zhoumin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhoumin.domain.ResponseResult;
import com.zhoumin.domain.entity.Article;
import com.zhoumin.domain.entity.Category;
import com.zhoumin.domain.vo.ArticleDetail;
import com.zhoumin.domain.vo.ArticleListVo;
import com.zhoumin.domain.vo.HotArticleVo;
import com.zhoumin.domain.vo.PageVo;
import com.zhoumin.mapper.ArticleMapper;
import com.zhoumin.service.ArticleService;
import com.zhoumin.service.CategoryService;
import com.zhoumin.utils.BeanCopyUtils;
import com.zhoumin.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.zhoumin.constants.SystemConstants.ARTICLE_STATUS_NORMAL;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult hotArticleList() {
        //查询结果，封装后返回
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //非草稿，按浏览量降序查出前十条
        queryWrapper.eq(Article::getStatus, ARTICLE_STATUS_NORMAL);
        queryWrapper.orderByDesc(Article::getViewCount);
        Page<Article> page = new Page<>(1, 10);
        page(page, queryWrapper);
        List<Article> articleList = page.getRecords();

        //未写工具类前的bean拷贝代码
//        //bean拷贝
//        for (Article article : articleList) {
//            HotArticleVo vo = new HotArticleVo();
//            BeanUtils.copyProperties(article,vo);
//            hotArticleVoList.add(vo);
//        }
        //写了工具类的bean拷贝
        List<HotArticleVo> hotArticleVos = BeanCopyUtils.copyBeanList(articleList, HotArticleVo.class);

        return ResponseResult.okResult(hotArticleVos);
    }

    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper();
        //查询条件

        //如果有categoryId,则进行条件查询
        queryWrapper.eq(Objects.nonNull(categoryId) && categoryId > 0, Article::getCategoryId, categoryId);

        //置顶文章放在最上方 对isTop降序排序
        queryWrapper.orderByDesc(Article::getIsTop);
        //状态为正式发布的文章
        queryWrapper.eq(Article::getStatus, ARTICLE_STATUS_NORMAL);

        //分页查询
        Page<Article> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);


        //todo 优化点：多次查询数据库，数据库压力大，并且在循环中进行查库，问题很大

        //查询categoryName
        List<Article> articles = page.getRecords();

//        articles = articles.stream()
//                .map(article -> article.setCategoryName(categoryService.getById(article.getCategoryId()).getName()))
//                .collect(Collectors.toList());

        //articleId查询articleName进行设置
        for (Article article : articles) {
            Category category = categoryService.getById(article.getCategoryId());
            article.setCategoryName(category.getName());
        }


        List<ArticleListVo> articleListVo = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVo.class);

        PageVo pageVo = new PageVo(articleListVo, page.getTotal());

        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getArticleDetail(Long id) {
        //根据id查询文章
        Article article = getById(id);

        //阅读量从redis中获取
        Integer articleViewCount = redisCache.getCacheMapValue("articleViewCount", String.valueOf(id));
        article.setViewCount(articleViewCount.longValue());
        //转换成vo
        ArticleDetail articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetail.class);

        //根据分类id查询分类名
        Long categoryId = articleDetailVo.getCategoryId();
        Category category = categoryService.getById(categoryId);

        if (category != null) {
            articleDetailVo.setCategoryName(category.getName());
        }

        //封装返回
        return ResponseResult.okResult(articleDetailVo);
    }

    @Override
    public ResponseResult updateViewCount(Long id) {
        //更新redis中对应文章浏览量
        redisCache.incrementCacheMapValue("articleViewCount",String.valueOf(id),1);

        return ResponseResult.okResult();
    }
}
