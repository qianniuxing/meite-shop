package com.hello.api.pay;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/pay")
public interface IPayApi {

    @GetMapping("/toPayHtml")
    JSONObject toPayHtml(JSONObject param);

    @PostMapping("/toPayHtmlTest")
    JSONObject toPayHtmlTest(JSONObject param);

    @GetMapping("/payQuery")
    JSONObject payQuery(JSONObject param);

    @GetMapping("/refund")
    JSONObject refund(JSONObject param);

    @GetMapping("/refundQuery")
    JSONObject refundQuery(JSONObject param);

}
