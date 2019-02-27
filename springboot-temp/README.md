# 搭建通用的单机springboot

1. idea构建maven springboot
2. 数据库语言mysql 5.7
3. 数据库语言 mybatis / jpa  mybatis生成器编写
4. redis 3.2.8 集成
5. restTemplate 请求接口集成
6. 全局异常搭建
7. 开发环境和生成环境集成
8. 请求拦截器搭建
9. 工具类搭建:  xml解析和生成 json工具类 cookies工具类 redis工具类 token工具类 md5工具类 Bigdecimal工具类
10. pojo  / interface::service / dao / enum / vo / dto / formVaild 做成module /controller 是project
11. 日志 logback.xml
12. maven准备： lombok 、jackson、common-lang(工具类) 、swagger2(文档生成) 、redis3.2.8 、 mybatis/jpa、restTemplate、 



## 基于Mybatis的应用：数据库表增加一个字段，还要改哪些地方?

a、写sql脚本修改表结构

b、实体bean增加对象

c、mapper中所有涉及查询整个对象字段的sql，都需一一添加对应的新增字段

d、mapper配置中涉及整个对象的查询尽量采用以下方式



## springboot常用开发基本配置

### 详细代码查看spring security 课程代码

* 配置全局异常拦截器 `@ControllerAdvice`

* 配置filter（一般用的比较少） @Component

* 配置拦截器interceptor  `@Component，implements HandlerInterceptor`，可以获取方法名，调
用的类 

* 配置切片Aspect（Spring AOP）  `@Aspect，@Component` ，切片可以获取更多数据，还可以获取参数

* 上传文件：一般用oos、七牛云、fastdfs 文件服务器 `FileController.java`

* 下载文件：maven common-io包；`FileController.java`

* spring security 主要这三个配置
  
  1. extends WebSecurityConfigurerAdapter 配置权限页等匹配路径
  
  2. implements UserDetailsService, SocialUserDetailsService 配置一些信息，比如登录信息，token,角色等配置，登录成功会返回这些信息
  
  3.
  
