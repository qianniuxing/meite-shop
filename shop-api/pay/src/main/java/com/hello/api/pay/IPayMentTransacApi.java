package com.hello.api.pay;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Api(tags = "支付安全服务")
@RequestMapping("/payTransac")
public interface IPayMentTransacApi {


    @ApiOperation(value = "创建支付令牌")
    @PostMapping("/cratePayToken")
    Map cratePayToken(Map map);

    @ApiOperation("通过令牌获取支付信息")
    @PostMapping("/tokenByPayMentTransac")
    Map tokenByPayMentTransac(Map map);

}
