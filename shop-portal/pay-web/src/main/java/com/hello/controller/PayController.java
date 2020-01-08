package com.hello.controller;


import com.alibaba.fastjson.JSONObject;
import com.hello.feign.IPayContextFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@Controller
public class PayController {

    @Autowired
    IPayContextFeign payContextFeign;

    @RequestMapping("indes")
    public String indes() {
        return "主页";
    }


    @GetMapping("/toPayHtmlTest")
    public void toPayHtmlTest(HttpServletResponse response) throws IOException {
        JSONObject result = payContextFeign.toPayHtmlTest(new JSONObject());
        String payHtml = result.getString("data");
        System.out.println(payHtml);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(payHtml);//直接将完整的表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();
    }

    @RequestMapping("/toPayYes")
    public void toPayYes() {
        System.out.println("支付成功--前端");
    }




}
