package com.zhoumin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhoumin.constants.SystemConstants;
import com.zhoumin.domain.ResponseResult;
import com.zhoumin.domain.entity.Article;
import com.zhoumin.domain.entity.Category;
import com.zhoumin.domain.vo.CategoryVo;
import com.zhoumin.mapper.ArticleMapper;
import com.zhoumin.mapper.CategoryMapper;
import com.zhoumin.service.ArticleService;
import com.zhoumin.service.CategoryService;
import com.zhoumin.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.zhoumin.constants.SystemConstants.CATEGORY_STATUS_NORMAL;

/**
 * 分类表(Category)表服务实现类
 *
 * @author makejava
 * @since 2023-01-06 13:34:54
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private ArticleService articleService;

    @Override
    public ResponseResult getCategoryList() {
        //查询文章表，找到发布的文章
        LambdaQueryWrapper<Article> articlequeryWrapper = new LambdaQueryWrapper<>();
        articlequeryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> articleList = articleService.list(articlequeryWrapper);
        //获取到id集合，去重
        Set<Long> categoryIds = articleList.stream()
                .map(article -> article.getCategoryId())
                .collect(Collectors.toSet());

        //查询分类表
//        LambdaQueryWrapper<Category> categoryLambdaQueryWrapper = new LambdaQueryWrapper<>();
//        categoryLambdaQueryWrapper.eq(Category::getStatus,SystemConstants.CATEGORY_STATUS_NORMAL);

        List<Category> categories = listByIds(categoryIds);
        categories = categories.stream()
                .filter(category -> CATEGORY_STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());

        //封装
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);

        return ResponseResult.okResult(categoryVos);
    }
}

