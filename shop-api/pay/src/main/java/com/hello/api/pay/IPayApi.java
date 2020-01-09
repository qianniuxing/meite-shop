package com.hello.api.pay;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/pay")
public interface IPayApi {

    @PostMapping("/toPayHtml")
    JSONObject toPayHtml(JSONObject param);

    @PostMapping("/toPayHtmlTest")
    JSONObject toPayHtmlTest(JSONObject param);

    @PostMapping("/query")
    JSONObject query(JSONObject param);

    @GetMapping("/refund")
    JSONObject refund(JSONObject param);

    @PostMapping("/refundQuery")
    JSONObject refundQuery(JSONObject param);

    @PostMapping("/create")
    JSONObject create(JSONObject param);

    @PostMapping("/close")
    JSONObject close(JSONObject param);

}
