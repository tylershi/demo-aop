package com.example.aop.annotation;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * @Author tyler.shi
 * @Date 2019/9/4 14:12
 * @Version 1.0
 * @Description TODO
 */
@Aspect
@Component
public class DemoLoggingAspect {

  @Pointcut("@annotation(com.example.aop.annotation.Demo)")
  public void pointCut() {
  }

  @Before("@annotation(demo)")
  public void beforeMethod(Demo demo) {
    // 接口访问需要的权限
    ArrayList<String> list = Lists.newArrayList(demo.value());
    validateUserPermission(list);
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

  void validateUserPermission(List<String> permissions) {
    // 不需要权限直接放行
    if (CollectionUtils.isEmpty(permissions)) {
      System.out.println("不需要权限，直接放行");
      return;
    }
    // 获取用户权限
    List<String> userPermissions = getUserPermission();
    // 校验权限
    if (!userPermissions.containsAll(permissions)) {
      throw new RuntimeException("没有权限，禁止访问");
    }

  }

  private List<String> getUserPermission() {
    System.out.println("获取用户权限");
    return Lists.newArrayList("ROLE_ADMIN");
  }

}
