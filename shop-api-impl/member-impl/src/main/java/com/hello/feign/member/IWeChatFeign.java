package com.hello.feign.member;

import com.hello.api.weChat.IWeChatApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="api-weChat")
public interface IWeChatFeign extends IWeChatApi {
}
