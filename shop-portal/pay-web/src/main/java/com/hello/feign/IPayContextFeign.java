package com.hello.feign;

import com.hello.api.pay.IPayApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "api-pay")
public interface IPayContextFeign extends IPayApi {

}
