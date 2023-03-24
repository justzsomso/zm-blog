# zmBlog

#### 介绍
个人博客

#### 软件架构
软件架构说明
主流技术栈：SpringBoot,MybatisPlus,SpringSecurity,EasyExcel,Swagger2,Redis,Echarts,Vue,ElementUI


#### 安装教程

1.  xxxx
2.  xxxx
3.  xxxx

#### 使用说明

1.前段工程启动方式
    1.1 npm install
    1.2 npm run dev

2.初始账号
    username：sg
    password:1234

3.Swagger网址
http//localhost:7777/swagger-ui.html


问题，反思
1. 使用mybaitsPlus进行数据库查询时，能否直接查出需要的属性进行封装，不再进行vo的转换
    1.1正常使用有两种，一是把所有属性查出来之后进行筛选，二是指定列名称之后进行封装
    1.2两种方法都可以使用，主要的是看系统，如果两种处理方式性能无差别，那都可以使用
    1.3如果担心数据库压力或者查询速度慢，那就可以使用第二种处理方式
2. bean拷贝的效率是否可以提高
    2.1现在出现了实体类dto还有vo，使用场景很多，所以效率确实能提高
3. com.zhoumin.utils这个包中的方法都是静态方法，会在程序开启的时候创建，会不会浪费性能，改成需要的时候调用会不会好点
4. DTO和普通的实体类有什么区别
    4.1 dto是前端和后端传输的实体类，使用dto可以避免多余的属性修改，比如本次修改的是用户表的昵称，若有心人会把是否是会员这个属性注入，导致问题发生