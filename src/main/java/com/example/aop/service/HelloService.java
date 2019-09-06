package com.example.aop.service;

/**
 * @Author tyler.shi
 * @Date 2019/9/4 14:10
 * @Version 1.0
 * @Description TODO
 */
public interface HelloService {

  void sayHelloByAdmin(String msg);

  void sayHelloWithoutPermission(String msg);

  void sayHelloWithMorePermission(String msg);

}
