package com.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @ClassName EurekaApplication
 * @Description TODO Enable注册中心
 * @Author niu
 * @Date 2019/11/9
 * @Version 1.0
 **/

@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {


    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class, args);
    }
}
