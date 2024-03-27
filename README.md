# Spring Boot中使用面向切面编程（AOP）跟踪业务数据更新

## AOP 的概念和术语
- 切面: 切面是实现跨多个类（如事务管理）的企业应用程序关注点的类。切面可以通过Spring XML配置的普通类，或者我们可以使用@Aspect注解将类定义为Aspect。
- 连接点: 连接点是应用程序中的特定点，例如方法执行、异常处理、更改对象变量值等。在 Spring AOP 中，连接点通常是方法的执行。
- 通知: 通知是针对特定连接点执行的操作。在编程方面，它们是在应用程序中达到具有匹配切点的某个连接点时执行的方法。您可以将通知视为Struts2拦截器或Servlet过滤器。
- 切点: 切点是与连接点匹配的表达式，用于确定是否需要执行通知。切点使用与连接点匹配的不同类型的表达式，Spring框架使用AspectJ切点表达式语言。
- 目标对象: 它们是通知执行的对象。Spring AOP是使用运行时代理实现的，因此此对象始终是代理对象。这意味着在运行时创建一个子类，其中目标方法被重写，并根据其配置包含通知。
- AOP代理: Spring AOP实现使用JDK动态代理来创建具有目标类和通知调用的 Proxy 类，这些称为 AOP 代理类。 我们还可以通过在 Spring AOP 项目中将其添加为依赖项来使用 CGLIB 代理。
- 织入: 它是将切面与其他对象链接以创建通知的代理对象的过程。这可以在编译时、加载时或运行时完成。Spring AOP在运行时实施织入。


## 构建与运行前提条件

在当前机器上安装下列软件:
1. Java JDK 17
2. Apache Maven 4.0.0-alpha-8或更高版本


## 创建测试数据库

请在运行代码之前安装PostgreSQL数据库，并创建名为test的数据库。

可在命令行使用下面命令创建数据库：

    psql -h localhost -U postgres

输入postgres用户密码后登录成功，再使用下面命令创建数据库test。

    CREATE DATABASE test;

## 构建Spring Boot应用
在代码根目录下，运行以下命令来构建应用: 
```
mvn clean package
```
它将在应用下得/target目录产生对应的jar文件。


## 如何运行

在代码根目录下，使用以下命令来运行应用：
```
cd target
java -jar aop-0.0.1-SNAPSHOT.jar
```

## 如何测试

在应用运行成功后，可以使用测试工具Jmeter（版本5.6.2）来进行测试，代码根目录里包含有一个Test.jmx的Jmeter测试脚本文件，可以安装Jmeter后打开此脚本进行测试增改删功能，最后检查数据库Trace表测试数据是否正确。
