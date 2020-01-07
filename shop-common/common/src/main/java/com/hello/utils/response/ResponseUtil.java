package com.hello.utils.response;

import com.alibaba.fastjson.JSONObject;

import java.util.Objects;

public class ResponseUtil {


    /**
     * success
     * @return
     */
    public static JSONObject success() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ResponseCode.SUCCESS.getCode());
        jsonObject.put("msg", ResponseCode.SUCCESS.getMsg());
        return jsonObject;
    }

    /**
     * success，携带数据
     * @param objData
     * @return
     */
    public static JSONObject success(Object objData) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ResponseCode.SUCCESS.getCode());
        jsonObject.put("msg", ResponseCode.SUCCESS.getMsg());
        jsonObject.put("data", objData);
        return jsonObject;
    }

    /**
     * success，携带提示信息和数据
     * @param msg
     * @param objData
     * @return
     */
    public static JSONObject success(String msg, Object objData) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ResponseCode.SUCCESS.getCode());
        jsonObject.put("msg", msg);
        jsonObject.put("data", objData);
        return jsonObject;
    }

    /**
     * 失败
     * @return
     */
    public static JSONObject fail() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ResponseCode.EXCEPTION.getCode());
        jsonObject.put("msg", ResponseCode.EXCEPTION.getMsg());
        return jsonObject;
    }

    /**
     * 失败，自定义提示信息
     * @return
     */
    public static JSONObject fail(String msg) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ResponseCode.EXCEPTION.getCode());
        jsonObject.put("msg", msg);
        return jsonObject;
    }

    /**
     * 参数缺失
     * @return
     */
    public static JSONObject lack() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ResponseCode.LACK.getCode());
        jsonObject.put("msg", ResponseCode.LACK.getMsg());
        return jsonObject;
    }

    /**
     * 参数缺失，自定义提示信息
     * @param msg
     * @return
     */
    public static JSONObject lack(String msg) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ResponseCode.LACK.getCode());
        jsonObject.put("msg", msg);
        return jsonObject;
    }

    public static JSONObject responseCode(ResponseCode responseCode) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", responseCode.getCode());
        jsonObject.put("msg", responseCode.getMsg());
        return jsonObject;
    }

    public static JSONObject responseCode(ResponseCode responseCode, Object objData) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", responseCode.getCode());
        jsonObject.put("msg", responseCode.getMsg());
        jsonObject.put("data", objData);
        return jsonObject;
    }


    /**
     * 自定义状态码与提示信息
     * @param code
     * @param msg
     * @return
     */
    public static JSONObject customResponse(Integer code, String msg) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ResponseCode.checkResponseCode(code));
        jsonObject.put("msg", msg);
        return jsonObject;
    }

    /**
     * 自定义状态码与提示信息，并携带数据
     * @param code
     * @param msg
     * @param objData
     * @return
     */
    public static JSONObject customResponse(Integer code, String msg, Objects objData) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ResponseCode.checkResponseCode(code));
        jsonObject.put("msg", msg);
        jsonObject.put("data", objData);
        return jsonObject;
    }





}
