package com.hello.api.member;

import com.hello.entity.App;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(tags = "会员服务接口",description = "member")
@RequestMapping("/member")
public interface IMemberApi {


    @ApiOperation(value = "会员调用微信")
    @GetMapping("/memberIncokeWeChat")
    App memberIncokeWeChat();

}
