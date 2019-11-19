package com.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName MemberImplApplication
 * @Description TODO
 * @Author niu
 * @Date 2019/11/9
 * @Version 1.0
 **/

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableSwagger2
public class MemberImplApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemberImplApplication.class, args);
    }

}
