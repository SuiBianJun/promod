### springboot 项目模板

1、response处理 +
    统一的Response处理
        带数据
        不带数据
        携带异常信息

2、错误处理(ExceptionHandler,ErrorController) +
    自定义异常处理(ExceptionHandler)
    未捕获异常处理(ErrorController)
        返回html错误页面
        返回json数据
        
3、日志记录（spring aop）+、按天记录 +

4、分页功能、复杂查询（mysql,redis,mongodb）+
    jpa内置的分页查询与多条件查询接口

5、接口文档配置swagger +
    项目API查看
    项目接口测试

6、spring事务+ ,多数据源配置，数据库连接池配置
    spring事务与jpa事务

7、自动配置
    @Configration
        WebMvcConfigration: Intercepter（拦截器）、CorsRegister(跨域处理)
        Filter（过滤器）
 
    SpringMVC配置
    
a、springboot编译打包成可在tomcat上运行的war包 + (ProjectmoduleApplicationTomcat.java)
    1、修改启动类
    2、排除tomcat依赖
    3、添加servlet-api依赖

---------------------------------

8、token验证 +, JWT +
    配置Intercepter
    JWT 可以带少量用户信息，减少sql负担，同时带有token签名验证，更加安全

9、跨域 +
    跨域需要【提供资源方】设置【响应头】告诉浏览器允许 解析 跨域请求 返回的数据。提供资源方指的是提供跨域资源的那方
    使用Configration配置或者使用Filter

10、权限控制rbac
    shiro权限认证框架(https://www.iteye.com/blog/jinnianshilongnian-2018398   https://github.com/zhangkaitao/shiro-example)

11、任务调度
    springboot 定时任务 +
    springboot quartz 任务调度
        动态添加定时任务，类似于设定时间执行某项任务，带数据

12、时间处理 +

13、sso、cas
    sso: 用户在一个系统中登录后，即可直接访问其他相互信任的系统

------------------------------------

不同模块添加swagger的测试接口