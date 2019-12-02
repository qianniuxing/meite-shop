package com.hello.api.pay.unionPay.impl;

import com.hello.api.pay.strategy.IPayStrategy;

import java.util.Map;

public class UnionPayStrategy implements IPayStrategy {

    @Override
    public String toPayHtml(Map map) {
        return "银联支付";
    }
}
