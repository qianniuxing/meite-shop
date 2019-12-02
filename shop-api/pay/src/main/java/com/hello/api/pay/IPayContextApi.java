package com.hello.api.pay;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

public interface IPayContextApi {

    @GetMapping("/toPayHtml")
    Map toPayHtml(Map map);

}
