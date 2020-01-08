package com.hello.utils;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @Name LogUtil
 * @Description TODO 日志工具类，将日志信息转换为json格式
 * @Date 2020/1/8 12:08
 * @Version 1.0.0
 */
public class LogUtil {

    /**
     * @description log信息转json格式
     * @date 2020/1/8 12:09
     * @param buss : 请求路径或者包路径等，可作为阿里云日志查找关键字，快速定位到本条日志
     * @param msg : 日志信息
     * @return java.lang.String
     * @throws
     */
    public static String toJSONString(String buss, String msg) {
        Map<String, String> map = new HashMap();
        map.put("buss", buss);
        map.put("msg", msg);
        return JSONObject.toJSONString(map);
    }

    /**
     * @description log转json格式，包含请求或响应等报文信息
     * @date 2020/1/8 12:09
     * @param buss : 请求路径或者包路径等，可作为阿里云日志查找关键字，快速定位到本条日志
     * @param msg : 日志信息
     * @param notice : 报文（请求或响应等报文信息）
     * @return java.lang.String
     * @throws
     */
    public static String toJSONString(String buss, String msg, String notice) {
        Map<String, String> map = new HashMap();
        map.put("buss", buss);
        map.put("msg", msg);
        map.put("notice", notice);
        return JSONObject.toJSONString(map);
    }

//    /**
//     * @description log转json格式，包含简短异常信息
//     * @date 2020/1/8 12:10
//     * @param buss : 请求路径或者包路径等，可作为阿里云日志查找关键字，快速定位到本条日志
//     * @param msg : 日志信息
//     * @param throwable : 异常信息(简短)
//     * @return java.lang.String
//     * @throws
//     */
//    public static String toJSONString(String buss, String msg, Throwable throwable) {
//        Map<String, String> map = new HashMap();
//        map.put("buss", buss);
//        map.put("msg", msg);
//        map.put("exception_msg", throwable.getMessage());
//        return JSONObject.toJSONString(map);
//    }

    /**
     * @description log转json格式，包含异常详细信息
     * @date 2020/1/8 12:11
     * @param buss : 请求路径或者包路径等，可作为阿里云日志查找关键字，快速定位到本条日志
     * @param msg : 日志信息
     * @param exception : 异常对象(由此获取详细异常信息)
     * @return java.lang.String
     * @throws
     */
    public static String toJSONString(String buss, String msg, Exception exception) {
        Map<String, String> map = new HashMap();
        map.put("buss", buss);
        map.put("msg", msg);
        map.put("exception", getExceptionDetail(exception));
        return JSONObject.toJSONString(map);
    }

//    /**
//     * @description log转json格式，包含报文（请求或响应等报文信息）、简短异常信息
//     * @date 2020/1/8 12:11
//     * @param buss : 请求路径或者包路径等，可作为阿里云日志查找关键字，快速定位到本条日志
//     * @param msg : 日志信息
//     * @param notice : 报文（请求或响应等报文信息）
//     * @param throwable : 异常信息(简短)
//     * @return java.lang.String
//     * @throws
//     */
//    public static String toJSONString(String buss, String msg, String notice, Throwable throwable) {
//        Map<String, String> map = new HashMap();
//        map.put("buss", buss);
//        map.put("msg", msg);
//        map.put("notice", notice);
//        map.put("exception_msg", throwable.getMessage());
//        return JSONObject.toJSONString(map);
//    }

    /**
     * @description log转json格式，包含报文（请求或响应等报文信息）、异常详细信息
     * @date 2020/1/8 12:12
     * @param buss : 请求路径或者包路径等，可作为阿里云日志查找关键字，快速定位到本条日志
     * @param msg : 日志信息
     * @param notice : 报文（请求或响应等报文信息）
     * @param exception : 异常对象(由此获取详细异常信息)
     * @return java.lang.String
     * @throws
     */
    public static String toJSONString(String buss, String msg, String notice, Exception exception) {
        Map<String, String> map = new HashMap();
        map.put("buss", buss);
        map.put("msg", msg);
        map.put("notice", notice);
        map.put("exception", getExceptionDetail(exception));
        return JSONObject.toJSONString(map);
    }

    /**
     * @description 获取异常详细信息，知道出了什么错，错在哪个类的第几行
     * @date 2020/1/8 12:09
     * @param exception : 异常对象(由此获取详细异常信息)
     * @return java.lang.String
     * @throws
     */
    private static String getExceptionDetail(Exception exception) {
        String exceptionDetail = null;
        ByteArrayOutputStream out = null;
        PrintStream pout = null;
        try {
            out = new ByteArrayOutputStream();
            pout = new PrintStream(out);
            exception.printStackTrace(pout);
            exceptionDetail = new String(out.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != out) pout.close();
            if (null != pout) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return exceptionDetail;
    }

}
