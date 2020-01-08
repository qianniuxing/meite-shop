package com.hello.strategy.pay;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface IPayStrategy {

    JSONObject pay(JSONObject paramp);

    JSONObject query(JSONObject param);

    JSONObject refund(JSONObject param);

    JSONObject refundQuery(JSONObject param);

}
