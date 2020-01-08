package com.hello.api.pay;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/aliPayCallBack")
public interface IAliPayCallBackApi {

    @RequestMapping("/payCallBack")
    void payCallBack(HttpServletRequest request, HttpServletResponse response);


}
