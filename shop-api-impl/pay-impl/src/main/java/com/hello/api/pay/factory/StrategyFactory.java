package com.hello.api.pay.factory;

import com.hello.api.pay.strategy.IPayStrategy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StrategyFactory {

    private static Map<String, IPayStrategy> strategyBean = new ConcurrentHashMap<String, IPayStrategy>();

    /**I
     * 使用Java反射机制初始化类
     *
     * @param classAddres
     * @return
     */
    public static IPayStrategy getPayStrategy(String classAddres) {
        try {
            IPayStrategy beanPayStrategy = strategyBean.get(classAddres);
            if (beanPayStrategy != null) {
                return beanPayStrategy;
            }
            Class<?> forName = Class.forName(classAddres);
            IPayStrategy payStrategy = (IPayStrategy) forName.newInstance();
            strategyBean.put(classAddres, payStrategy);
            return payStrategy;
        } catch (Exception e) {
            return null;
        }
    }


}
