package com.hello.api.weChat;

import com.hello.entity.App;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(tags = "微信服务")
@RequestMapping("/weChat")
public interface IWeChatApi {


    @ApiOperation(value = "获取app信息")
    @GetMapping("/getApp")
    App getApp();
}
