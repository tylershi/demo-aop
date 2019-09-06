package com.example.aop.service.impl;

import com.example.aop.annotation.Demo;
import com.example.aop.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * @Author tyler.shi
 * @Date 2019/9/4 14:11
 * @Version 1.0
 * @Description TODO
 */
@Service
public class HelloServiceImpl implements HelloService {

  @Demo("ROLE_ADMIN")
  @Override
  public void sayHelloByAdmin(String msg) {
    System.out.println(msg);
  }

  @Demo
  @Override
  public void sayHelloWithoutPermission(String msg) {
    System.out.println(msg);
  }

  @Demo(value = {"ROLE_ADMIN", "ROLE_TEST"})
  @Override
  public void sayHelloWithMorePermission(String msg) {
    System.out.println(msg);
  }
}
