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

  @Demo("demo")
  @Override
  public void sayHello(String msg) {
    System.out.println(msg);
  }

  @Demo
  @Override
  public void sayHelloThenException(String msg) {
    System.out.println(msg);
    throw new RuntimeException();
  }
}
