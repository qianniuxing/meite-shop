package com.hello.api.pay.aliPay.impl;

import com.hello.api.pay.strategy.IPayStrategy;

import java.util.Map;

public class AliPayStrategy implements IPayStrategy {
    @Override
    public String toPayHtml(Map map) {
        return "阿里支付";
    }
}
