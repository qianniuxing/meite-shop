package com.hello.api.pay.impl;

import com.hello.api.pay.IPayContextApi;
import com.hello.api.pay.IPayMentTransacApi;
import com.hello.api.pay.factory.StrategyFactory;
import com.hello.api.pay.strategy.IPayStrategy;
import com.hello.mapper.pay.IPayTransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class PayContextApi implements IPayContextApi {

    @Autowired
    IPayTransactionMapper payTransactionMapper;

    @Autowired
    IPayMentTransacApi payMentTransacApi;

    @Override
    public Map toPayHtml(Map map) {
        // 1.使用渠道id查询渠道信息
        Map resultMap = new HashMap();
        Map channel = this.payTransactionMapper.paymentChannelByCloumn(map);
        if (channel == null) {
            resultMap.put("code", 201);
            resultMap.put("mag", "没有查询到该渠道信息!");
            return resultMap;
        }
        // 2.使用payToken查询待支付信息
        Map transac = payMentTransacApi.tokenByPayMentTransac(map);
        if (!"200".equals(transac.get("code"))) {
            return transac;
        }
        // 3.使用Java反射机制初始化子类
        String classAddres = (String) channel.get("ClassAddres");
        IPayStrategy payStrategy = StrategyFactory.getPayStrategy(classAddres);
        if (payStrategy == null) {
            resultMap.put("code", 201);
            resultMap.put("mag", "支付系统网关错误!");
            return resultMap;
        }
        map.put("channel", channel);
        map.put("transac", transac.get("data"));
        // 4.直接执行子类实现方法
        String payHtml = payStrategy.toPayHtml(map);
        resultMap.put("code", 200);
        resultMap.put("payHtml", payHtml);
        return resultMap;
    }

}
