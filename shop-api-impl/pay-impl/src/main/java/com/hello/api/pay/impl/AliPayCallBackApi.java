package com.hello.api.pay.impl;

import com.alipay.api.internal.util.AlipaySignature;
import com.hello.api.pay.IAliPayCallBackApi;
import com.hello.strategy.pay.aliPay.AlipayConfig;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
public class AliPayCallBackApi implements IAliPayCallBackApi {


    @Override
    public void payCallBack(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("支付宝回调成功");
        try {
            //获取支付宝POST过来反馈信息
            Map<String, String> params = new HashMap<String, String>();
            Map requestParams = request.getParameterMap();
            for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
                String name = (String) iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                }
                //乱码解决，这段代码在出现乱码时使用。
                //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
                params.put(name, valueStr);
            }
//            boolean flag = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
            // 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
            String order_no = params.get("out_trade_no"); // 获取订单号

//			String trade_no = request.getParameter("trade_no"); // 支付宝交易号
//			String total_amount = request.getParameter("total_amount"); // 获取总金额
//			String subject = new String(request.getParameter("subject")
//					.getBytes("ISO-8859-1"), "gbk");// 商品名称、订单名称
//			String body = "";
//			if (request.getParameter("body") != null) {
//				body = new String(request.getParameter("body").getBytes(
//						"ISO-8859-1"), "gbk");// 商品描述、订单备注、描述
//			}
//			String buyer_email = request.getParameter("buyer_email"); // 买家支付宝账号
            String trade_status = request.getParameter("trade_status"); // 交易状态
            // 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
//            if (flag == true) {// 验证成功
//                try {
//
//                } catch (Exception e) {
//                    System.out.println("支付宝支付异常");
//                    e.printStackTrace();
//                }
//            }
            response.getWriter().write("success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
