package com.example.administrator.myoschina.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/4.
 */

public class PostResponse {


    /**
     * post_list : [{"answerCount":1,"answer":{"name":"淡定歌","time":"2017-05-04 09:32:25"},"author":"达尔文","id":2240582,"viewCount":547,"title":"【开源访谈】奇虎 360 陈宗志：开源能让项目走得更长久","portrait":"https://static.oschina.net/uploads/user/1451/2903254_50.jpg?t=1475462273000","authorid":2903254,"pubDate":"2017-05-03 14:22:32"},{"answerCount":40,"answer":{"name":"hi2012","time":"2017-05-04 07:29:44"},"author":"局长","id":2240334,"viewCount":2143,"title":"高手问答第 151 期 \u2014 人工智能／机器学习在电商场景下的应用","portrait":"https://static.oschina.net/uploads/user/1360/2720166_50.jpg?t=1470892376000","authorid":2720166,"pubDate":"2017-05-01 22:17:28"},{"answerCount":0,"answer":"","author":"若望夏雪","id":2240665,"viewCount":0,"title":"appium连接真机时报错：failed to connect to the serve。。。appium的版本1.4.16","portrait":"https://static.oschina.net/uploads/user/1731/3463681_50.jpg?t=1493788556000","authorid":3463681,"pubDate":"2017-05-04 09:39:13"},{"answerCount":3,"answer":{"name":"mickelfeng","time":"2017-05-04 09:39:56"},"author":"sca7","id":2240588,"viewCount":25,"title":"pip install mysql  安装失败了","portrait":"https://static.oschina.net/uploads/user/995/1991666_50.jpg?t=1472456196000","authorid":1991666,"pubDate":"2017-05-03 15:12:41"},{"answerCount":1,"answer":{"name":"JFinal","time":"2017-05-04 09:34:58"},"author":"阿杰杰杰杰_gj","id":2240621,"viewCount":17,"title":"Jfinal能否在没有Action能匹配时执行通配Action","portrait":"https://static.oschina.net/uploads/user/1267/2535334_50.jpg?t=1448589225000","authorid":2535334,"pubDate":"2017-05-03 19:30:22"},{"answerCount":0,"answer":"","author":"Rable_PHP","id":2240662,"viewCount":2,"title":"如果网站是一套多个功能模块组合而成的产物，那么前端既然能和后端分离，那后端能否与模块完全分离","portrait":"https://static.oschina.net/uploads/user/1133/2267270_50.png?t=1463996250000","authorid":2267270,"pubDate":"2017-05-04 09:33:26"},{"answerCount":10,"answer":{"name":"烟头","time":"2017-05-04 09:34:16"},"author":"StringQiao","id":2240512,"viewCount":119,"title":"springboot 打包后无法调用webService接口","portrait":"https://www.oschina.net/img/portrait.gif","authorid":2849721,"pubDate":"2017-05-02 19:01:31"},{"answerCount":9,"answer":{"name":"IdleMan","time":"2017-05-04 09:34:02"},"author":"shinningEyes丶果果","id":2240529,"viewCount":221,"title":"Java如何在不引入插件的前提下实现word文档的解析","portrait":"https://static.oschina.net/uploads/user/1730/3461076_50.jpeg?t=1493694549000","authorid":3461076,"pubDate":"2017-05-03 08:33:44"},{"answerCount":0,"answer":"","author":"洛溪寒","id":2240660,"viewCount":0,"title":"遇到HTTP Status 500 - Servlet.init() for servlet spring threw exception怎么办？","portrait":"https://static.oschina.net/uploads/user/1694/3388216_50.jpeg?t=1490679490000","authorid":3388216,"pubDate":"2017-05-04 09:29:17"},{"answerCount":0,"answer":"","author":"sca7","id":2240659,"viewCount":0,"title":"三个不确定因素的敏感性分析表的推导过程","portrait":"https://static.oschina.net/uploads/user/995/1991666_50.jpg?t=1472456196000","authorid":1991666,"pubDate":"2017-05-04 09:24:57"},{"answerCount":0,"answer":"","author":"watertiger","id":2240658,"viewCount":9,"title":"迪杰斯特拉改进","portrait":"https://static.oschina.net/uploads/user/1129/2259252_50.jpg?t=1434085431000","authorid":2259252,"pubDate":"2017-05-04 09:16:06"},{"answerCount":0,"answer":"","author":"恶里怪","id":2240657,"viewCount":19,"title":"python scapy抓包","portrait":"https://www.oschina.net/img/portrait.gif","authorid":1053849,"pubDate":"2017-05-04 09:12:18"},{"answerCount":2,"answer":{"name":"cafelication","time":"2017-05-04 09:11:05"},"author":"愉快的码农","id":2240645,"viewCount":40,"title":"是不是hibernate的问题啊","portrait":"https://static.oschina.net/uploads/user/1586/3173265_50.jpg?t=1482500635000","authorid":3173265,"pubDate":"2017-05-03 22:30:33"},{"answerCount":2,"answer":{"name":"IdleMan","time":"2017-05-04 08:59:51"},"author":"阿丢丢","id":2240644,"viewCount":130,"title":"百度糯米的订单号是如何生成的","portrait":"https://static.oschina.net/uploads/user/524/1048694_50.jpg?t=1368067863000","authorid":1048694,"pubDate":"2017-05-03 22:28:14"},{"answerCount":8,"answer":{"name":"恩赐解脱r","time":"2017-05-04 08:54:36"},"author":"恩赐解脱r","id":2240381,"viewCount":107,"title":"关于mvaen的","portrait":"https://www.oschina.net/img/portrait.gif","authorid":3460919,"pubDate":"2017-05-02 11:58:39"},{"answerCount":0,"answer":"","author":"sca7","id":2240653,"viewCount":12,"title":"算法题目分享","portrait":"https://static.oschina.net/uploads/user/995/1991666_50.jpg?t=1472456196000","authorid":1991666,"pubDate":"2017-05-04 08:52:02"},{"answerCount":1,"answer":{"name":"啥都不会啥都不懂","time":"2017-05-04 08:28:05"},"author":"啥都不会啥都不懂","id":2240652,"viewCount":16,"title":"Spring源码导入eclipse 时候使用gradle 总是出现错误，版本不同，怎么回事？","portrait":"https://static.oschina.net/uploads/user/1462/2925143_50.jpeg?t=1475850769000","authorid":2925143,"pubDate":"2017-05-04 08:27:22"},{"answerCount":0,"answer":"","author":"西伯利亚狼1","id":2240651,"viewCount":18,"title":"jsessionid在每次访问后台接口时都会发生变化，浏览器没有关闭， 应该是会话级别的啊， 怎么老是变啊， 这样就拿不到对应的ｓｅｓｓｉｏｎ了","portrait":"https://www.oschina.net/img/portrait.gif","authorid":2892685,"pubDate":"2017-05-04 08:23:38"},{"answerCount":3,"answer":{"name":"MnameHZJ","time":"2017-05-04 08:02:11"},"author":"扬城艺人","id":2240504,"viewCount":57,"title":"session怎么用","portrait":"https://static.oschina.net/uploads/user/1728/3457622_50.jpg?t=1493383729000","authorid":3457622,"pubDate":"2017-05-02 18:04:01"},{"answerCount":3,"answer":{"name":"某刀","time":"2017-05-04 01:37:42"},"author":"oulaly","id":2240380,"viewCount":45,"title":"一级访问路由中，如何避免业务路由和唯一用户名的冲突","portrait":"https://static.oschina.net/uploads/user/496/993875_50.jpg?t=1380515057000","authorid":993875,"pubDate":"2017-05-02 11:57:54"}]
     * notice : {"referCount":0,"replyCount":0,"msgCount":0,"fansCount":0}
     */

    private NoticeBean notice;
    private List<PostListBean> post_list;

    public NoticeBean getNotice() {
        return notice;
    }

    public void setNotice(NoticeBean notice) {
        this.notice = notice;
    }

    public List<PostListBean> getPost_list() {
        return post_list;
    }

    public void setPost_list(List<PostListBean> post_list) {
        this.post_list = post_list;
    }

    public static class NoticeBean {
        /**
         * referCount : 0
         * replyCount : 0
         * msgCount : 0
         * fansCount : 0
         */

        private int referCount;
        private int replyCount;
        private int msgCount;
        private int fansCount;

        public int getReferCount() {
            return referCount;
        }

        public void setReferCount(int referCount) {
            this.referCount = referCount;
        }

        public int getReplyCount() {
            return replyCount;
        }

        public void setReplyCount(int replyCount) {
            this.replyCount = replyCount;
        }

        public int getMsgCount() {
            return msgCount;
        }

        public void setMsgCount(int msgCount) {
            this.msgCount = msgCount;
        }

        public int getFansCount() {
            return fansCount;
        }

        public void setFansCount(int fansCount) {
            this.fansCount = fansCount;
        }
    }

    public static class PostListBean {
        /**
         * answerCount : 1
         * answer : {"name":"淡定歌","time":"2017-05-04 09:32:25"}
         * author : 达尔文
         * id : 2240582
         * viewCount : 547
         * title : 【开源访谈】奇虎 360 陈宗志：开源能让项目走得更长久
         * portrait : https://static.oschina.net/uploads/user/1451/2903254_50.jpg?t=1475462273000
         * authorid : 2903254
         * pubDate : 2017-05-03 14:22:32
         */

        private int answerCount;
        private AnswerBean answer;
        private String author;
        private int id;
        private int viewCount;
        private String title;
        private String portrait;
        private int authorid;
        private String pubDate;

        public int getAnswerCount() {
            return answerCount;
        }

        public void setAnswerCount(int answerCount) {
            this.answerCount = answerCount;
        }

        public AnswerBean getAnswer() {
            return answer;
        }

        public void setAnswer(AnswerBean answer) {
            this.answer = answer;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getViewCount() {
            return viewCount;
        }

        public void setViewCount(int viewCount) {
            this.viewCount = viewCount;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public int getAuthorid() {
            return authorid;
        }

        public void setAuthorid(int authorid) {
            this.authorid = authorid;
        }

        public String getPubDate() {
            return pubDate;
        }

        public void setPubDate(String pubDate) {
            this.pubDate = pubDate;
        }

        public static class AnswerBean {
            /**
             * name : 淡定歌
             * time : 2017-05-04 09:32:25
             */

            private String name;
            private String time;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }
        }
    }
}
