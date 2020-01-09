package com.hello.api.pay.impl;

import com.alibaba.fastjson.JSONObject;
import com.hello.api.pay.IPayApi;
import com.hello.api.pay.IPayMentTransacApi;
import com.hello.exception.ResponseCodeException;
import com.hello.factory.StrategyFactory;
import com.hello.mapper.pay.IPayTransactionMapper;
import com.hello.strategy.pay.IPayStrategy;
import com.hello.strategy.pay.PayStrategyAddres;
import com.hello.utils.response.ResponseUtil;
import com.hello.utils.LogUtil;
import com.hello.utils.response.ResponseCode;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PayApi implements IPayApi {

    private final Log log = LogFactory.getLog(this.getClass());

    @Autowired
    IPayTransactionMapper payTransactionMapper;

    @Autowired
    IPayMentTransacApi payMentTransacApi;

    @Override
    public JSONObject toPayHtml(@RequestBody JSONObject param) {
        // 1.使用渠道id查询渠道信息
        Map resultMap = new HashMap();
        Map channel = this.payTransactionMapper.paymentChannelByCloumn(param);
        if (channel == null) {
            try {
                return ResponseUtil.customResponse(9004,"没有查询到该渠道信息");
            } catch (ResponseCodeException e) {
                e.printStackTrace();
            }
        }
        // 2.使用payToken查询待支付信息
        Map transac = payMentTransacApi.tokenByPayMentTransac(param);
        if (!"200".equals(transac.get("code").toString())) {
            try {
                return ResponseUtil.customResponse(9004,"没有查询到支付信息");
            } catch (ResponseCodeException e) {
                e.printStackTrace();
            }
        }
        // 3.使用Java反射机制初始化子类
        String classAddres = (String) channel.get("CLASS_ADDRES");
        IPayStrategy payStrategy = StrategyFactory.getPayStrategy(classAddres);
        if (payStrategy == null) {
            try {
                return ResponseUtil.customResponse(9005,"支付系统网关错误");
            } catch (ResponseCodeException e) {
                e.printStackTrace();
            }
        }
        // 4.直接执行子类实现方法
        param.put("MERCHANT_ID", channel.get("MERCHANT_ID"));
        param.putAll((Map) transac.get("data"));
        JSONObject result = payStrategy.pay(param);
        return ResponseUtil.success();
    }

    @Override
    public JSONObject toPayHtmlTest(@RequestBody JSONObject param) {
        param.put("CHANNEL_ID", "ali_pay");
        // 支付宝
        String classAddres = PayStrategyAddres.getClassAddres(param.getString("CHANNEL_ID"));
        IPayStrategy payStrategy = StrategyFactory.getPayStrategy(classAddres);
        if (payStrategy == null) {
            return ResponseUtil.lack("支付系统网关错误!");
        }
//        param.put("out_trade_no", "test202001081634");
//        param.put("total_amount", "100");
//        param.put("subject", "华为mata30 pro");
        JSONObject result = payStrategy.pay(param);
        return result;

        //银联
//        String classAddres = "com.hello.strategy.pay.unionPay.UnionPayStrategy";
//        IPayStrategy payStrategy = StrategyFactory.getPayStrategy(classAddres);
//        if (payStrategy == null) {
//            return "支付系统网关错误!";
//        }
//        map.put("MERCHANT_ID", "777290058176236");
//        map.put("PAYMENT_ID", "test2020010316");
//        map.put("PAY_AMOUNT", "1000");
//        map.put("CREATED_TIME", new Date());
//        String payHtml = payStrategy.toPayHtml(map);
//        return payHtml;
    }

    @Override
    public JSONObject query(@RequestBody JSONObject param) {
        param.put("CHANNEL_ID", "ali_pay");
        // 支付宝
        String classAddres = PayStrategyAddres.getClassAddres(param.getString("CHANNEL_ID"));
        IPayStrategy payStrategy = StrategyFactory.getPayStrategy(classAddres);
        if (payStrategy == null) {
            try {
                return ResponseUtil.customResponse(9005,"支付系统网关错误");
            } catch (ResponseCodeException e) {
                e.printStackTrace();
            }
        }
//        param.put("out_trade_no", "test202001081634");
        JSONObject payQueryResult = payStrategy.query(param);
        return payQueryResult;
    }

    @Override
    public JSONObject refund(@RequestBody JSONObject param) {
        param.put("CHANNEL_ID", "ali_pay");
        // 支付宝
        String classAddres = PayStrategyAddres.getClassAddres(param.getString("CHANNEL_ID"));
        IPayStrategy payStrategy = StrategyFactory.getPayStrategy(classAddres);
        if (payStrategy == null) {
            try {
                return ResponseUtil.customResponse(9005,"支付系统网关错误");
            } catch (ResponseCodeException e) {
                e.printStackTrace();
            }
        }
//        param.put("out_trade_no", "test20200106");
//        param.put("refund_amount", "300.00");
//        param.put("out_request_no", "requestNo20200106");
        JSONObject refundResult = payStrategy.refund(param);
        return refundResult;
    }

    @Override
    public JSONObject refundQuery(@RequestBody JSONObject param) {
        param.put("CHANNEL_ID", "ali_pay");
        // 支付宝
        String classAddres = PayStrategyAddres.getClassAddres(param.getString("CHANNEL_ID"));
        IPayStrategy payStrategy = StrategyFactory.getPayStrategy(classAddres);
        if (payStrategy == null) {
            try {
                return ResponseUtil.customResponse(9005,"支付系统网关错误");
            } catch (ResponseCodeException e) {
                e.printStackTrace();
            }
        }
        param.put("out_trade_no", "test20200105");
        JSONObject refundResult = payStrategy.refundQuery(param);
        return refundResult;
    }

    @Override
    public JSONObject create(@RequestBody JSONObject param) {
        param.put("CHANNEL_ID", "ali_pay");
        // 支付宝
        String classAddres = PayStrategyAddres.getClassAddres(param.getString("CHANNEL_ID"));
        IPayStrategy payStrategy = StrategyFactory.getPayStrategy(classAddres);
        if (payStrategy == null) {
            return ResponseUtil.lack("支付系统网关错误!");
        }
//        param.put("out_trade_no", "test202001081634");
//        param.put("total_amount", "100");
//        param.put("subject", "华为mata30 pro");
        JSONObject result = payStrategy.create(param);
        return result;
    }

    @Override
    public JSONObject close(@RequestBody JSONObject param) {
        param.put("CHANNEL_ID", "ali_pay");
        // 支付宝
        String classAddres = PayStrategyAddres.getClassAddres(param.getString("CHANNEL_ID"));
        IPayStrategy payStrategy = StrategyFactory.getPayStrategy(classAddres);
        if (payStrategy == null) {
            try {
                return ResponseUtil.customResponse(9005,"支付系统网关错误");
            } catch (ResponseCodeException e) {
                e.printStackTrace();
            }
        }
//        param.put("out_trade_no", "test202001081634");
        JSONObject payQueryResult = payStrategy.close(param);
        return payQueryResult;
    }


}
