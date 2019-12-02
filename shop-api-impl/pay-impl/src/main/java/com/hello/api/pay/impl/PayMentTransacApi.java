package com.hello.api.pay.impl;

import com.hello.api.pay.IPayMentTransacApi;
import com.hello.mapper.pay.IPayTransactionMapper;
import com.hello.utils.base.GenerateToken;
import com.hello.utils.twitter.SnowflakeIdUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
public class PayMentTransacApi implements IPayMentTransacApi {

    @Autowired
    GenerateToken generateToken;

    @Autowired
    IPayTransactionMapper payTransactionMapper;


    @Override
    public Map cratePayToken(@RequestBody Map map) {
        String orderId = (String) map.get("ORDER_ID");
        Map resultMap = new HashMap();
        if (StringUtils.isEmpty(orderId)) {
            resultMap.put("code", 201);
            resultMap.put("mag", "订单号码不能为空!");
            return resultMap;
        }
        Long payAmount = Long.parseLong((String) map.get("PAY_AMOUNT"));
        if (payAmount == null) {
            resultMap.put("code", 201);
            resultMap.put("mag", "金额不能为空!");
            return resultMap;
        }

        Long userId =  Long.parseLong((String) map.get("USER_ID"));
        if (userId == null) {
            resultMap.put("code", 201);
            resultMap.put("mag", "userId不能为空!");
            return resultMap;
        }
        // 使用雪花算法 生成全局id
        String paymentId = SnowflakeIdUtils.nextId();
        map.put("PAYMENT_ID", paymentId);
        map.put("PAYMENT_STATUS", 0);
        // 预支付
        Integer affectedRows = this.payTransactionMapper.insertPayTransaction(map);
        if (affectedRows < 1) {
            resultMap.put("code", 201);
            resultMap.put("mag", "生成预支付错误!");
            return resultMap;
        }
        Long id = (Long) map.get("ID");

        // 生成对应支付令牌
        String keyPrefix = "pay_";
        String token = generateToken.createToken(keyPrefix, id + "");
        resultMap.put("code", 200);
        resultMap.put("mag", "创建支付令牌成功！");
        resultMap.put("token", token);
        return resultMap;
    }

    @Override
    public Map tokenByPayMentTransac(@RequestBody Map map) {
        Map resultMap = new HashMap();
        // 1.验证token是否为空
        if (StringUtils.isEmpty((String) map.get("token"))) {
            resultMap.put("code", 201);
            resultMap.put("mag", "token参数不能空!");
            return resultMap;
        }
        // 2.使用token查询redisPayMentTransacID
        String value = generateToken.getToken((String) map.get("token"));
        if (StringUtils.isEmpty(value)) {
            resultMap.put("code", 201);
            resultMap.put("mag", "该Token可能已经失效或者已经过期!");
            return resultMap;
        }
        // 3.转换为整数类型
        Long transactionId = Long.parseLong(value);
        // 4.使用transactionId查询支付信息
        map.put("id", transactionId);
        Map payTransaction = this.payTransactionMapper.payTransactionById(map);
        if (Objects.isNull(payTransaction)) { resultMap.put("code", 201);
            resultMap.put("mag", "未查询到该支付信息!");
        }
        resultMap.put("code", 200);
        resultMap.put("data", payTransaction);
        return resultMap;
    }


}
