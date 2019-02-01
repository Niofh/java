### 搭建通用的单机springboot

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



#### 基于Mybatis的应用：数据库表增加一个字段，还要改哪些地方?

a、写sql脚本修改表结构

b、实体bean增加对象

c、mapper中所有涉及查询整个对象字段的sql，都需一一添加对应的新增字段

d、mapper配置中涉及整个对象的查询尽量采用以下方式：