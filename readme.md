### springboot 项目模板

1、response处理 +
    统一的Response处理
        带数据
        不带数据

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

6、spring事务+ ,多数据源配置，数据库连接池配置
    spring事务与jpa事务

7、自动配置
    @Configration
        WebMvcConfigration: Intercepter（拦截器）、CorsRegister(跨域处理)
        Filter（过滤器）
 
    SpringMVC配置

---------------------------------

8、token验证 +, JWT
    配置Intercepter

9、跨域 +
    跨域需要【提供资源方】设置【响应头】告诉浏览器允许跨域请求
    使用Configration配置或者使用Filter

10、权限控制rbac

11、任务调度

12、时间处理

13、sso、cas