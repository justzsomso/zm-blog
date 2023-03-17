package com.zhoumin.constants;

public class SystemConstants
{
    /**
     *  文章是草稿
     */
    public static final int ARTICLE_STATUS_DRAFT = 1;
    /**
     *  文章是正常分布状态
     */
    public static final int ARTICLE_STATUS_NORMAL = 0;
    /**
     * 类别正常发布状态
     */
    public static final String CATEGORY_STATUS_NORMAL = "0";
    /**
     * 类别禁用
     */
    public static final String CATEGORY_STATUS_DISABLE = "1";

    /**
     *  友情链接审核通过
     */
    public static final String Link_STATUS_NORMAL = "1";
    /**
     *  友情链接审核未通过
     */
    public static final String Link_STATUS_DRAFT = "0";

    /**
     *  评论的rootId是否是-1，判断是否是根评论
     */
    public static final int COMMENT_ROOTID_DRAFT = -1;

    /**
     *  评论类型：友链评论
     */
    public static final String ARTICLE_TYPE_DRAFT = "1";
    /**
     *  评论类型：文章评论
     */
    public static final String ARTICLE_TYPE_NORMAL = "0";
}