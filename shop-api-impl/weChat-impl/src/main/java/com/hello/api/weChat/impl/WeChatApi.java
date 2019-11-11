package com.hello.api.weChat.impl;

import com.hello.api.weChat.IWeChatApi;
import com.hello.entity.App;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName IWeChatApiimpl
 * @Description TODO
 * @Author niu
 * @Date 2019/11/9
 * @Version 1.0
 **/

@RestController
public class WeChatApi implements IWeChatApi {



    @Override
    public App getApp() {
        App a = new App();
        a.setAppId("1241412");
        a.setAppName("niujunwei");
        return a;
    }


}
