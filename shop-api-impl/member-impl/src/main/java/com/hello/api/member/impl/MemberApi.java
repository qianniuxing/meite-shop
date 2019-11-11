package com.hello.api.member.impl;

import com.hello.api.member.IMemberApi;
import com.hello.entity.App;
import com.hello.feign.member.IWeChatFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName MemberApi
 * @Description TODO
 * @Author niu
 * @Date 2019/11/9
 * @Version 1.0
 **/

@RestController
public class MemberApi implements IMemberApi {

    @Autowired
    IWeChatFeign weChatFeign;

    @Override
    public App memberIncokeWeChat() {
        return this.weChatFeign.getApp();
    }


}
