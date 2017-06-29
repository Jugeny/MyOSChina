package com.example.administrator.myoschina.utils;

/**
 * Created by Administrator on 2017/4/27.
 */

public class OSChinaApi {
    public  static  final  String HOST = "http://www.oschina.net"; //主机地址

    public  static  final  String TOKEN = HOST+ "/action/openapi/token";

    public  static  final  String NEWS_LIST = HOST+ "/action/openapi/news_list";
    //最新推荐
    public  static  final  String BLOG_RECOMMEND_LIST = HOST+ "/action/openapi/blog_recommend_list";

    //问答
    public  static  final  String POST_LIST = HOST+ "/action/openapi/post_list";
    //获取动弹列表（最新动弹列表 我的动弹）
    public final static String TWEET_LIST=HOST+"/action/openapi/tweet_list";
    public final static String TWEET_DETAIL=HOST+"/action/openapi/tweet_detail";

    public final static String PROJECT_CATALOG_LIST=HOST+"/action/openapi/project_catalog_list";
    //Tag下的软件列表
    public final static String PROJECT_TAG_LIST=HOST+"/action/openapi/project_catalog_list";
    //获取评论列表
    public final static String COMMENT_LIST=HOST+"/action/openapi/comment_list";
    //发表评论
    public final static String COMMENT_PUB=HOST+"/action/openapi/comment_pub";
    //获取新闻详情
    public final static String NEWS_DETAIL=HOST+"/action/openapi/news_detail";

    public final static String MY_INFORMATION=HOST+"/action/openapi/my_information";

}
