|@Aspect 注解用于标识此类为一个AOP横切面对象|
|@Pointcut 注解用于定义本类中的切入点，本案例中切入点表达式用的是bean表达式，这个表达式以bean开头，bean括号中的内容为一个spring管理的某个bean对象的id。|
|@Before 用于定义一个前置通知（满足切入点表达式的核心业务方法执行之前要执行的一个操作）|
|@After  用于定义一个后置通知（满足切入点表达式的核心业务方法执行之后要执行的一个操作）|

	指示符	作用
1.		bean	用于匹配指定bean id的的方法执行
2.		within	用于匹配指定包名下类型内的方法执行
3.		execution	用于进行细粒度方法匹配执行具体业务

bean应用于类级别，实现粗粒度的控制：

bean("userServiceImpl"))	指定一个类
bean("*Service")	指定所有的后缀为service的类


within应用于类级别，实现粗粒度的切面表达式定义：
within("aop.service.UserServiceImpl")	指定类，只能指定一个类
within("aop.service.*")	只包括当前目录下的类
within("aop.service..*")	指定当前目录包含所有子目录中的类


execution方法级别，细粒度的控制：
语法：execution(返回值类型 包名.类名.方法名(参数列表))
execution(void aop.service.UserServiceImpl.addUser())	匹配方法
execution(void aop.service.PersonServiceImpl.addUser(String))	方法参数必须为字符串
execution(* aop.service..*.*(..))	万能配置



在AOP编程中有五种类型的通知：
1）	前置通知 (@Before)
2）	返回通知 (@AfterReturning)
3）	异常通知 (@AfterThrowing)
4）	后置通知 (@After) 
5）	环绕通知 (@Around) :课后扩展了解

