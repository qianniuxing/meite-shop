package com.hello.strategy.pay;

import org.springframework.beans.factory.annotation.Autowired;

public enum PayStrategyAddres {


    ALIPAY("ali_pay", "com.hello.strategy.pay.aliPay.AliPayStrategy"),
    UNIONPAY("union_pay", "com.hello.strategy.pay.unionPay.UnionPayStrategy");

    private String id;
    private String classAddres;
//    private Map<String, String> classAddresMap = new HashMap<>();
    @Autowired
    IPayStrategy aliPayStrategy;

    PayStrategyAddres(String id, String classAddres) {
        this.id = id;
        this.classAddres = classAddres;
//        this.classAddresMap.put(id, classAddres);
    }

    /**
     * 自己定义一个静态方法,通过id返回枚举常量对象
     * @param id
     * @return
     */
    public static String getClassAddres(String id) {
        for (PayStrategyAddres p : values()) {
            if (p.id == id) {
                return p.classAddres;
            }
        }
        return null;
    }


}