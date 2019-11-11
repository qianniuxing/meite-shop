package com.hello;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 * @ClassName WeChatImplApplication
 * @Description TODO
 * @Author niu
 * @Date 2019/11/9
 * @Version 1.0
 **/

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2Doc
public class WeChatImplApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeChatImplApplication.class, args);
    }

}
