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
 */
@Aspect
@Component
public class DemoLoggingAspect1 {

  @Pointcut("bean(helloServiceImpl)")
  public void pointCut() {
  }

  @Before("@annotation(demo)")
  public void beforeMethod(Demo demo) {
    String require = "demo";
    ArrayList<String> list = Lists.newArrayList(demo.value());
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
