package com.hello.feign;

import com.hello.api.member.IUserApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "api-member")
public interface IUserFeign extends IUserApi {
}
