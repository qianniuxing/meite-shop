package com.hello.utils.response;

import com.alibaba.fastjson.JSONObject;
import com.hello.exception.ResponseCodeException;

import java.util.Objects;

/**
 * @Name ResponseUtil
 * @Description TODO 响应工具类
 * @Date 2020/1/7 11:53
 * @Version 1.0.0
 */
public class ResponseUtil {

    /**
     * @description 业务处理成功
     * @date 2020/1/8 11:34
     * @param
     * @return com.alibaba.fastjson.JSONObject
     * @throws
     */
    public static JSONObject success() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ResponseCode.SUCCESS.getCode());
        jsonObject.put("msg", ResponseCode.SUCCESS.getMsg());
        return jsonObject;
    }

    /**
     * @description 业务处理成功，响应携带数据
     * @date 2020/1/8 11:34
     * @param objData : 数据
     * @return com.alibaba.fastjson.JSONObject
     * @throws
     */
    public static JSONObject success(Object objData) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ResponseCode.SUCCESS.getCode());
        jsonObject.put("msg", ResponseCode.SUCCESS.getMsg());
        jsonObject.put("data", objData);
        return jsonObject;
    }

    /**
     * @description 业务处理成功，自定义提示信息，并携带数据（无数据传null）
     * @date 2020/1/8 11:39
     * @param msg : 提示信息
     * @param objData : 数据
     * @return com.alibaba.fastjson.JSONObject
     * @throws
     */
    public static JSONObject success(String msg, Object objData) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ResponseCode.SUCCESS.getCode());
        jsonObject.put("msg", msg);
        if (null != objData) jsonObject.put("data", objData);
        return jsonObject;
    }

    /**
     * @description 业务处理失败
     * @date 2020/1/8 11:40
     * @param
     * @return com.alibaba.fastjson.JSONObject
     * @throws
     */
    public static JSONObject fail() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ResponseCode.FAIL.getCode());
        jsonObject.put("msg", ResponseCode.FAIL.getMsg());
        return jsonObject;
    }

    /**
     * @description 业务处理失败，自定义提示信息
     * @date 2020/1/8 11:44
     * @param msg : 提示信息
     * @return com.alibaba.fastjson.JSONObject
     * @throws
     */
    public static JSONObject fail(String msg) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ResponseCode.FAIL.getCode());
        jsonObject.put("msg", msg);
        return jsonObject;
    }

    /**
     * @description 业务处理失败，自定义提示信息，并携带数据
     * @date 2020/1/8 11:42
     * @param msg : 提示信息
     * @param objData : 数据
     * @return com.alibaba.fastjson.JSONObject
     * @throws
     */
    public static JSONObject fail(String msg, Object objData) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ResponseCode.FAIL.getCode());
        jsonObject.put("msg", msg);
        jsonObject.put("data", objData);
        return jsonObject;
    }

    /**
     * @description 参数缺失
     * @date 2020/1/8 11:46
     * @param
     * @return com.alibaba.fastjson.JSONObject
     * @throws
     */
    public static JSONObject lack() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ResponseCode.LACK.getCode());
        jsonObject.put("msg", ResponseCode.LACK.getMsg());
        return jsonObject;
    }

    /**
     * @description 参数缺失，自定义提示信息
     * @date 2020/1/8 11:47
     * @param msg : 提示信息
     * @return com.alibaba.fastjson.JSONObject
     * @throws
     */
    public static JSONObject lack(String msg) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ResponseCode.LACK.getCode());
        jsonObject.put("msg", msg);
        return jsonObject;
    }

    /**
     * @description 参数缺失，自定义提示信息，并携带数据
     * @date 2020/1/8 11:47
     * @param msg : 提示信息
     * @param objData : 数据
     * @return com.alibaba.fastjson.JSONObject
     * @throws
     */
    public static JSONObject lack(String msg, Object objData) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ResponseCode.LACK.getCode());
        jsonObject.put("msg", msg);
        jsonObject.put("data", objData);
        return jsonObject;
    }

    /**
     * @description 系统定义的响应信息ResponseCode
     * @date 2020/1/8 11:48
     * @param responseCode : 响应信息
     * @return com.alibaba.fastjson.JSONObject
     * @throws
     */
    public static JSONObject systemResponse(ResponseCode responseCode) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", responseCode.getCode());
        jsonObject.put("msg", responseCode.getMsg());
        return jsonObject;
    }

    /**
     * @description 系统定义的响应信息ResponseCode，并携带数据
     * @date 2020/1/8 11:48
     * @param responseCode : 响应信息
     * @param objData : 数据
     * @return com.alibaba.fastjson.JSONObject
     * @throws
     */
    public static JSONObject systemResponse(ResponseCode responseCode, Object objData) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", responseCode.getCode());
        jsonObject.put("msg", responseCode.getMsg());
        jsonObject.put("data", objData);
        return jsonObject;
    }

    /**
     * @description 自定义状态码与提示信息
     * @date 2020/1/8 11:49
     * @param code : 状态码
     * @param msg : 提示信息
     * @return com.alibaba.fastjson.JSONObject
     * @throws ResponseCodeException 如果定义状态码是系统状态码时会抛出异常
     */
    public static JSONObject customResponse(Integer code, String msg) throws ResponseCodeException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ResponseCode.checkResponseCode(code));
        jsonObject.put("msg", msg);
        return jsonObject;
    }

    /**
     * @description 自定义状态码与提示信息，并携带数据
     * @date 2020/1/8 11:50
     * @param code : 状态码
     * @param msg : 提示信息
     * @param objData : 数据
     * @return com.alibaba.fastjson.JSONObject
     * @throws ResponseCodeException 如果定义状态码是系统状态码时会抛出异常
     */
    public static JSONObject customResponse(Integer code, String msg, Objects objData) throws ResponseCodeException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ResponseCode.checkResponseCode(code));
        jsonObject.put("msg", msg);
        jsonObject.put("data", objData);
        return jsonObject;
    }





}
