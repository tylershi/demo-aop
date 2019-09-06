package com.example.demoaop;

import com.example.aop.DemoAopApplication;
import com.example.aop.service.HelloService;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoAopApplication.class)
public class DemoAopApplicationTests {

  @Resource
  private HelloService helloService;


  @Test
  public void testSayHelloByAdmin() {
    helloService.sayHelloByAdmin("hello world");
  }

  @Test
  public void testSayHelloWithoutPermission() {
    helloService.sayHelloWithoutPermission("hello world");
  }

  @Test
  public void testSayHelloWithMorePermission(){
    helloService.sayHelloWithMorePermission("hello world");
  }

}
