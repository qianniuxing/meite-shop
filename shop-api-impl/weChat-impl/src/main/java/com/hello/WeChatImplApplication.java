package com.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @ClassName WeChatImplApplication
 * @Description TODO
 * @Author niu
 * @Date 2019/11/9
 * @Version 1.0
 **/

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
public class WeChatImplApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeChatImplApplication.class, args);
    }

}
