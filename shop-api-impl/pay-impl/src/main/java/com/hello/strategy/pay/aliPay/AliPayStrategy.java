package com.hello.strategy.pay.aliPay;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.*;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.hello.strategy.pay.IPayStrategy;
import com.hello.utils.LogUtil;
import com.hello.utils.response.ResponseUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * @Name AliPayStrategy
 * @Description TODO 支付宝电脑网站支付
 * @Date 2020/1/8 12:32
 * @Version 1.0.0
 */
public class AliPayStrategy implements IPayStrategy {

    private final Log log = LogFactory.getLog(this.getClass());

    /**
     * @description 下单并支付页面，预下单请求
     * PC场景下单并支付
     * 文档地址：https://docs.open.alipay.com/api_1/alipay.trade.page.pay/
     * @date 2020/1/8 12:37
     * @param param :
     * @return com.alibaba.fastjson.JSONObject
     * @throws
     */
    @Override
    public JSONObject pay(JSONObject param) {
        if (StringUtils.isBlank(param.getString("out_trade_no"))) {
            log.info(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.pay", "支付宝电脑网站支付--预下单--缺失订单号", param.toJSONString()));
            return ResponseUtil.lack("支付宝电脑网站支付--预下单--缺失订单号");
        }
        if (StringUtils.isBlank(param.getString("total_amount"))) {
            log.info(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.pay", "支付宝电脑网站支付--预下单--缺失订单总金额", param.toJSONString()));
            return ResponseUtil.lack("支付宝电脑网站支付--预下单--缺失订单总金额");
        }
        if (StringUtils.isBlank(param.getString("subject"))) {
            log.info(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.pay", "支付宝电脑网站支付--预下单--缺失商品标题/交易标题/订单标题", param.toJSONString()));
            return ResponseUtil.lack("支付宝电脑网站支付--预下单--缺失商品标题/交易标题/订单标题");
        }
        // 获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.SERVER_URL, AlipayConfig.APP_ID, AlipayConfig.MERCHANT_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGN_TYPE); //获得初始化的AlipayClient
        // 设置请求参数，并设置回到地址
        AlipayTradePagePayRequest payRequest = new AlipayTradePagePayRequest();
        // 设置回调地址
        payRequest.setNotifyUrl(AlipayConfig.NOTIFY_URL);
        payRequest.setReturnUrl(AlipayConfig.RETURN_URL);
        // 业务参数
        AlipayTradePagePayModel payModel = new AlipayTradePagePayModel();
        payModel.setOutTradeNo(param.getString("out_trade_no")); //商户订单号,64个字符以内、可包含字母、数字、下划线；需保证在商户端不重复
        payModel.setProductCode(AlipayConfig.PRODUCT_CODE); //销售产品码，与支付宝签约的产品码名称。(注：目前仅支持FAST_INSTANT_TRADE_PAY)
        payModel.setTotalAmount(param.getString("total_amount")); //订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]。
        payModel.setSubject(param.getString("subject")); //商品的标题/交易标题/订单标题/订单关键字等
        payModel.setTimeoutExpress("20m"); //该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m
        payRequest.setBizModel(payModel);
        // 调用SDK生成表单
        AlipayTradePagePayResponse response = null;
        try {
            log.info(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.pay", "支付宝电脑网站支付--预下单--调用SDK生成表单", JSONObject.toJSONString(payModel)));
            response = alipayClient.pageExecute(payRequest);
        } catch (AlipayApiException e) {
            e.printStackTrace();
            log.error(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.pay", "支付宝电脑网站支付--预下单--调用SDK生成表单--异常", e));
            return ResponseUtil.fail();
        }
        if (!response.isSuccess()) {
            log.error(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.pay", "支付宝电脑网站支付--预下单--调用SDK生成表单--调用失败", JSONObject.toJSONString(response)));
            return ResponseUtil.fail(response.getSubMsg());
        }
        log.info(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.pay", "支付宝电脑网站支付--预下单--调用SDK生成表单--调用成功", JSONObject.toJSONString(response)));
        String responseBody = response.getBody();
        return ResponseUtil.success(responseBody);
    }

    /**
     * @description 交易查询
     * 文档地址：https://docs.open.alipay.com/api_1/alipay.trade.query
     * @date 2020/1/8 12:39
     * @param param :
     *            out_trade_no	支付时传入的商户订单号，与trade_no必填一个
     *            trade_no	支付时返回的支付宝交易号，与out_trade_no必填一个
     * @return java.util.Map
     * @throws
     */
    @Override
    public JSONObject query(JSONObject param) {
        if (StringUtils.isBlank(param.getString("out_trade_no"))) {
            log.info(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.query", "支付宝电脑网站支付--交易查询接口--缺失订单号", param.toJSONString()));
            return ResponseUtil.lack("支付宝电脑网站支付--交易查询接口--缺失订单号");
        }
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.SERVER_URL, AlipayConfig.APP_ID, AlipayConfig.MERCHANT_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGN_TYPE); //获得初始化的AlipayClient
        AlipayTradeQueryRequest queryRequest = new AlipayTradeQueryRequest();//创建API对应的request类
        AlipayTradeQueryModel queryModel = new AlipayTradeQueryModel();
        queryModel.setOutTradeNo(param.getString("out_trade_no"));
        queryRequest.setBizModel(queryModel);
        AlipayTradeQueryResponse response = null;
        try {
            log.info(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.query", "支付宝电脑网站支付--交易查询接口--调用请求", JSONObject.toJSONString(queryModel)));
            response = alipayClient.execute(queryRequest);
        } catch (AlipayApiException e) {
            e.printStackTrace();
            log.error(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.query", "支付宝电脑网站支付--交易查询接口--调用异常", e));
            return ResponseUtil.fail();
        }
        if (!response.isSuccess()) {
            log.error(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.query", "支付宝电脑网站支付--交易查询接口--调用失败", JSONObject.toJSONString(response)));
            return ResponseUtil.fail(response.getSubMsg());
        }
        log.info(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.query", "支付宝电脑网站支付--交易查询接口--调用成功", JSONObject.toJSONString(response)));
        JSONObject responseBody = JSONObject.parseObject(response.getBody());
        JSONObject responseResult = responseBody.getJSONObject("alipay_trade_query_response");
        if (!"10000".equals(responseResult.getString("code")) || !"Success".equals(responseResult.getString("msg"))) {
            log.error(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.query", "支付宝电脑网站支付--交易查询接口--查询失败"));
            return ResponseUtil.fail(responseResult.getString("sub_msg"));
        }
        log.info(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.query", "支付宝电脑网站支付--交易查询接口--查询成功", responseResult.toJSONString()));
        switch (responseResult.getString("trade_status")) {
            case "WAIT_BUYER_PAY":
                log.info(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.query", "支付宝电脑网站支付--交易查询接口--交易状态（trade_status）：交易创建，等待买家付款"));
                break;
            case "TRADE_CLOSED":
                log.info(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.query", "支付宝电脑网站支付--交易查询接口--交易状态（trade_status）：未付款交易超时关闭，或支付完成后全额退款"));
                break;
            case "TRADE_SUCCESS":
                log.info(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.query", "支付宝电脑网站支付--交易查询接口--交易状态（trade_status）：支付成功"));
                break;
            case "TRADE_FINISHED":
                log.info(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.query", "支付宝电脑网站支付--交易查询接口--交易状态（trade_status）：交易结束，不可退款"));
                break;
            default:
                log.warn(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.query", "支付宝电脑网站支付--交易查询接口--未查询到交易状态（trade_status）"));
                break;
        }
        return ResponseUtil.success(responseResult);
    }

    /**
     * @description 交易退款（支持单笔交易分多次退款）
     * 当交易发生之后一段时间内，由于买家或者卖家的原因需要退款时，卖家可以通过退款接口将支付款退还给买家，支付宝将在收到退款请求并且验证成功之后，按照退款规则将支付款按原路退到买家帐号上。
     * 交易超过约定时间（签约时设置的可退款时间）的订单无法进行退款，支付宝退款支持单笔交易分多次退款，多次退款需要提交原支付订单的商户订单号和设置不同的退款单号。一笔退款失败后重新提交，要采用原来的退款单号。
     * 总退款金额不能超过用户实际支付金额
     * 文档地址：https://docs.open.alipay.com/api_1/alipay.trade.refund
     * @date 2020/1/8 12:41
     * @param param :
     *              out_trade_no：订单支付时传入的商户订单号,不能和 trade_no同时为空。
     *              trade_no：支付宝交易号，和商户订单号不能同时为空
     *              out_request_no：标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传。
     * @return java.util.Map
     * @throws
     */
    @Override
    public JSONObject refund(JSONObject param) {
        if (StringUtils.isBlank(param.getString("out_trade_no"))) {
            log.info(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.refund", "支付宝电脑网站支付--交易退款--缺失订单号", param.toJSONString()));
            return ResponseUtil.lack("支付宝电脑网站支付--交易退款--缺失订单号");
        }
        if (StringUtils.isBlank(param.getString("refund_amount"))) {
            log.info(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.refund", "支付宝电脑网站支付--交易退款--缺失退款金额", param.toJSONString()));
            return ResponseUtil.lack("支付宝电脑网站支付--交易退款--缺失退款金额");
        }
        if (StringUtils.isBlank(param.getString("out_request_no"))) {
            log.info(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.refund", "支付宝电脑网站支付--交易退款--缺失退款请求号", param.toJSONString()));
            return ResponseUtil.lack("支付宝电脑网站支付--交易退款--缺失退款请求号");
        }
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.SERVER_URL, AlipayConfig.APP_ID, AlipayConfig.MERCHANT_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGN_TYPE); //获得初始化的AlipayClient
        AlipayTradeRefundRequest refundRequest = new AlipayTradeRefundRequest();
        AlipayTradeRefundModel refundModel = new AlipayTradeRefundModel();
        refundModel.setOutTradeNo(param.getString("out_trade_no"));
        refundModel.setRefundAmount(param.getString("refund_amount"));
        refundModel.setOutRequestNo(param.getString("out_request_no"));
        refundRequest.setBizModel(refundModel);
        AlipayTradeRefundResponse response = null;
        try {
            log.info(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.refund", "支付宝电脑网站支付--交易退款--调用请求", JSONObject.toJSONString(refundModel)));
            response = alipayClient.execute(refundRequest);
        } catch (AlipayApiException e) {
            e.printStackTrace();
            log.error(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.refund", "支付宝电脑网站支付--交易退款--调用异常", e));
            return ResponseUtil.fail();
        }
        if (!response.isSuccess()) {
            log.info(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.refund", "支付宝电脑网站支付--交易退款--调用失败", JSONObject.toJSONString(response)));
            return ResponseUtil.fail(response.getSubMsg());
        }
        log.info(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.refund", "支付宝电脑网站支付--交易退款--调用成功", JSONObject.toJSONString(response)));
        JSONObject responseBody = JSONObject.parseObject(response.getBody());
        JSONObject responseResult = responseBody.getJSONObject("alipay_trade_refund_response");
        if (!"10000".equals(responseResult.getString("code")) || !"Success".equals(responseResult.getString("msg"))) {
            log.error(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.refund", "支付宝电脑网站支付--交易退款--退款失败", responseResult.toJSONString()));
            return ResponseUtil.fail(responseResult.getString("sub_msg"));
        }
        log.info(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.refund", "支付宝电脑网站支付--交易退款--退款时间：" +responseResult.get("gmt_refund_pay"), responseResult.toJSONString()));
        return ResponseUtil.success(responseResult);
    }

    /**
     * @description 交易退款查询
     *  该接口的返回码10000，仅代表本次查询操作成功，不代表退款成功。
     *  如果该接口返回了查询数据，且refund_status为空或为REFUND_SUCCESS，则代表退款成功，如果没有查询到则代表未退款成功，可以调用退款接口进行重试。重试时请务必保证退款请求号一致。
     *  文档地址：https://docs.open.alipay.com/api_1/alipay.trade.fastpay.refund.query
     * @date 2020/1/8 17:08
     * @param param : 
     * @return com.alibaba.fastjson.JSONObject 
     * @throws 
     */
    @Override
    public JSONObject refundQuery(JSONObject param) {
        if (StringUtils.isBlank(param.getString("out_trade_no"))) {
            log.info(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.refundQuery", "支付宝电脑网站支付--交易退款查询--缺失订单号", param.toJSONString()));
            return ResponseUtil.lack("支付宝电脑网站支付--交易退款查询--缺失订单号");
        }
        if (StringUtils.isBlank(param.getString("out_request_no"))) {
            log.info(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.refundQuery", "支付宝电脑网站支付--交易退款查询--缺失退款请求号", param.toJSONString()));
            return ResponseUtil.lack("支付宝电脑网站支付--交易退款查询--缺失退款请求号");
        }
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.SERVER_URL, AlipayConfig.APP_ID, AlipayConfig.MERCHANT_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGN_TYPE); //获得初始化的AlipayClient
        AlipayTradeFastpayRefundQueryRequest refundQueryRequest = new AlipayTradeFastpayRefundQueryRequest();
        AlipayTradeFastpayRefundQueryModel refundQueryModel = new AlipayTradeFastpayRefundQueryModel();
        refundQueryModel.setOutTradeNo(param.getString("out_trade_no"));
        refundQueryModel.setOutRequestNo(param.getString("out_request_no"));
        refundQueryRequest.setBizModel(refundQueryModel);
        AlipayTradeFastpayRefundQueryResponse response = null;
        try {
            log.info(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.refundQuery", "支付宝电脑网站支付--交易退款查询--调用请求", JSONObject.toJSONString(refundQueryModel)));
            response = alipayClient.execute(refundQueryRequest);
        } catch (AlipayApiException e) {
            e.printStackTrace();
            log.error(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.refundQuery", "支付宝电脑网站支付--交易退款查询--调用异常", e));
            return ResponseUtil.fail();
        }
        if (!response.isSuccess()) {
            log.info(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.refundQuery", "支付宝电脑网站支付--交易退款查询--调用失败", JSONObject.toJSONString(response)));
            return ResponseUtil.fail(response.getSubMsg());
        }
        log.info(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.refundQuery", "支付宝电脑网站支付--交易退款查询--调用成功", JSONObject.toJSONString(response)));
        JSONObject responseBody = JSONObject.parseObject(response.getBody());
        JSONObject responseResult = responseBody.getJSONObject("alipay_trade_fastpay_refund_query_response");
        if (!"10000".equals(responseResult.getString("code")) || !"Success".equals(responseResult.getString("msg"))) {
            log.error(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.refundQuery", "支付宝电脑网站支付--交易退款查询--查询失败", responseResult.toJSONString()));
            return ResponseUtil.fail(responseResult.getString("sub_msg"));
        }
        log.info(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.refundQuery", "支付宝电脑网站支付--交易退款查询--查询成功", responseResult.toJSONString()));
        return ResponseUtil.success(responseResult);
    }

    /**
     * @description 交易创建
     * 商户通过该接口进行交易的创建下单
     * 文档地址：https://docs.open.alipay.com/api_1/alipay.trade.create/
     * @date 2020/1/9 14:31
     * @param param : 
     * @return com.alibaba.fastjson.JSONObject 
     * @throws
     */
    @Override
    public JSONObject create(JSONObject param) {
        if (StringUtils.isBlank(param.getString("out_trade_no"))) {
            log.info(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.create", "支付宝电脑网站支付--交易创建--缺失订单号", param.toJSONString()));
            return ResponseUtil.lack("支付宝电脑网站支付--交易创建--缺失订单号");
        }
        if (StringUtils.isBlank(param.getString("total_amount"))) {
            log.info(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.create", "支付宝电脑网站支付--交易创建--缺失订单总金额", param.toJSONString()));
            return ResponseUtil.lack("支付宝电脑网站支付--交易创建--缺失订单总金额");
        }
        if (StringUtils.isBlank(param.getString("subject"))) {
            log.info(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.create", "支付宝电脑网站支付--交易创建--缺失商品标题/交易标题/订单标题", param.toJSONString()));
            return ResponseUtil.lack("支付宝电脑网站支付--交易创建--缺失商品标题/交易标题/订单标题");
        }
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.SERVER_URL, AlipayConfig.APP_ID, AlipayConfig.MERCHANT_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGN_TYPE); //获得初始化的AlipayClient
        AlipayTradeCreateRequest createRequest = new AlipayTradeCreateRequest();
        AlipayTradeCreateModel createModel = new AlipayTradeCreateModel();
        createModel.setOutTradeNo(param.getString("out_trade_no"));
        createModel.setTotalAmount(param.getString("total_amount"));
        createModel.setSubject(param.getString("subject"));
//        createModel.setProductCode(AlipayConfig.PRODUCT_CODE);
        createRequest.setBizModel(createModel);
        AlipayTradeCreateResponse response = null;
        try {
            log.info(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.create", "支付宝电脑网站支付--交易创建--调用请求", JSONObject.toJSONString(createModel)));
            response = alipayClient.execute(createRequest);
        } catch (AlipayApiException e) {
            e.printStackTrace();
            log.error(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.create", "支付宝电脑网站支付--交易创建--调用异常", e));
            return ResponseUtil.fail();
        }
        if (!response.isSuccess()) {
            log.error(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.create", "支付宝电脑网站支付--交易创建--调用失败", JSONObject.toJSONString(response)));
            return ResponseUtil.fail(response.getSubMsg());
        }
        log.info(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.create", "支付宝电脑网站支付--交易创建--调用成功", JSONObject.toJSONString(response)));
        JSONObject responseBody = JSONObject.parseObject(response.getBody());
        JSONObject responseResult = responseBody.getJSONObject("alipay_trade_create_response");
        if (!"10000".equals(responseResult.getString("code")) || !"Success".equals(responseResult.getString("msg"))) {
            log.error(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.create", "支付宝电脑网站支付--交易创建--失败", responseResult.toJSONString()));
            return ResponseUtil.fail(responseResult.getString("sub_msg"));
        }
        log.info(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.create", "支付宝电脑网站支付--交易创建--成功", responseResult.toJSONString()));
        return ResponseUtil.success(responseResult);
    }

    /**
     * @description 交易关闭（交易创建（alipay.trade.create）后可关闭）
     * 用于交易创建后，用户在一定时间内未进行支付，可调用该接口直接将未付款的交易进行关闭。
     * 文档地址：https://docs.open.alipay.com/api_1/alipay.trade.close
     * @date 2020/1/9 10:24
     * @param param :
     * @return com.alibaba.fastjson.JSONObject
     * @throws
     */
    @Override
    public JSONObject close(JSONObject param) {
        if (StringUtils.isBlank(param.getString("out_trade_no"))) {
            log.info(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.close", "支付宝电脑网站支付--交易关闭--缺失订单号", param.toJSONString()));
            return ResponseUtil.lack("支付宝电脑网站支付--交易关闭--缺失订单号");
        }
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.SERVER_URL, AlipayConfig.APP_ID, AlipayConfig.MERCHANT_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGN_TYPE); //获得初始化的AlipayClient
        AlipayTradeCloseRequest closeRequest = new AlipayTradeCloseRequest();
        AlipayTradeCloseModel closeModel = new AlipayTradeCloseModel();
        closeModel.setOutTradeNo(param.getString("out_trade_no"));
        closeRequest.setBizModel(closeModel);
        AlipayTradeCloseResponse response = null;
        try {
            log.info(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.close", "支付宝电脑网站支付--交易关闭--调用请求", JSONObject.toJSONString(closeModel)));
            response = alipayClient.execute(closeRequest);
        } catch (AlipayApiException e) {
            e.printStackTrace();
            log.error(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.close", "支付宝电脑网站支付--交易关闭--调用异常", e));
            return ResponseUtil.fail();
        }
        if (!response.isSuccess()) {
            log.error(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.close", "支付宝电脑网站支付--交易关闭--调用失败", JSONObject.toJSONString(response)));
            return ResponseUtil.fail(response.getSubMsg());
        }
        log.info(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.close", "支付宝电脑网站支付--交易关闭--调用成功", JSONObject.toJSONString(response)));
        JSONObject responseBody = JSONObject.parseObject(response.getBody());
        JSONObject responseResult = responseBody.getJSONObject("alipay_trade_close_response");
        if (!"10000".equals(responseResult.getString("code")) || !"Success".equals(responseResult.getString("msg"))) {
            log.error(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.close", "支付宝电脑网站支付--交易关闭--失败"));
            return ResponseUtil.fail(responseResult.getString("sub_msg"));
        }
        log.info(LogUtil.toJSONString("com.hello.strategy.pay.aliPay.AliPayStrategy.close", "支付宝电脑网站支付--交易关闭--成功", responseResult.toJSONString()));
        return ResponseUtil.success(responseResult);
    }


}
