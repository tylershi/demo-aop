package com.example.aop.annotation;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author tyler.shi
 * @Date 2019/9/4 14:12
 * @Version 1.0
 * @Description TODO
 * 切入点表达式 bean 应用于类级别，实现粗粒度的控制：
 * bean("userServiceImpl"))	指定一个类
 * bean("*Service")	指定所有的后缀为service的类
 * <p>
 * within 应用于类级别，实现粗粒度的切面表达式定义：
 * within("aop.service.UserServiceImpl")	指定类，只能指定一个类
 * within("aop.service.*")	只包括当前目录下的类
 * within("aop.service..*")	指定当前目录包含所有子目录中的类
 * <p>
 * execution	用于进行细粒度方法匹配执行具体业务
 * execution方法级别，细粒度的控制：
 * 语法：execution(返回值类型 包名.类名.方法名(参数列表))
 * execution(void aop.service.UserServiceImpl.addUser())	匹配方法
 * execution(void aop.service.PersonServiceImpl.addUser(String))	方法参数必须为字符串
 * execution(* aop.service..*.*(..))	万能配置
 * <p>
 * 在AOP编程中有五种类型的通知：
 * 前置通知 (@Before)
 * 返回通知 (@AfterReturning)
 * 异常通知 (@AfterThrowing)
 * 后置通知 (@After)
 * 环绕通知 (@Around)
 */
@Aspect
@Component
public class DemoLoggingAspect {

  @Pointcut("@annotation(com.example.aop.annotation.Demo)")
  public void pointCut() {
  }

  @Before("@annotation(demo)")
  public void beforeMethod(Demo demo) {
    String[] value = demo.value();
    String require = "demo";
    ArrayList<String> list = Lists.newArrayList(value);
    if (list.contains(require)) {
      System.out.println("param valid success");
    } else {
      System.out.println("param valid failed");
    }
    System.out.println("method start time " + System.currentTimeMillis());
  }

  @After("pointCut()")
  public void afterMethod() {
    System.out.println("method end time " + System.currentTimeMillis());
  }

  /**
   * 核心业务正常结束时执行 说明：假如有after，先执行after,再执行returning
   */
  @AfterReturning("pointCut()")
  public void doAfterReturning() {
    System.out.println("after returning time" + System.currentTimeMillis());
  }

  @AfterThrowing("pointCut()")
  public void doAfterThrowing() {
    System.out.println("after throwing time " + System.currentTimeMillis());
  }

  @Around("pointCut()")
  public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
    System.out.println("事务开始 time " + System.currentTimeMillis());
    Object result = joinPoint.proceed();
    System.out.println("result ->" + result);
    System.out.println("事务结束 time ->" + System.currentTimeMillis());
    return result;
  }


}
