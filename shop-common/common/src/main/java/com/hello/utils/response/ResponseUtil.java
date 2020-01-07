package com.hello.utils.response;

import com.alibaba.fastjson.JSONObject;

import java.util.Objects;

public class ResponseUtil {


    public static JSONObject success() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ResponseCode.SUCCESS.getCode());
        jsonObject.put("msg", ResponseCode.SUCCESS.getMsg());
        return jsonObject;
    }

    public static JSONObject success(Object objData) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ResponseCode.SUCCESS.getCode());
        jsonObject.put("msg", ResponseCode.SUCCESS.getMsg());
        jsonObject.put("data", objData);
        return jsonObject;
    }

    public static JSONObject success(String msg, Object objData) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ResponseCode.SUCCESS.getCode());
        jsonObject.put("msg", msg);
        jsonObject.put("data", objData);
        return jsonObject;
    }

    public static JSONObject fail() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ResponseCode.EXCEPTION.getCode());
        jsonObject.put("msg", ResponseCode.EXCEPTION.getMsg());
        return jsonObject;
    }

    public static JSONObject fail(String msg) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ResponseCode.EXCEPTION.getCode());
        jsonObject.put("msg", msg);
        return jsonObject;
    }

    /**
     * 参数缺失
     * @param strData
     * @return
     */
    public static JSONObject lack() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ResponseCode.LACK.getCode());
        jsonObject.put("msg", ResponseCode.LACK.getMsg());
        return jsonObject;
    }
    public static JSONObject lack(String msg) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ResponseCode.LACK.getCode());
        jsonObject.put("msg", msg);
        return jsonObject;
    }


    public static JSONObject customResponse(Integer code, String msg) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ResponseCode.checkResponseCode(code));
        jsonObject.put("msg", msg);
        return jsonObject;
    }

    public static JSONObject customResponse(Integer code, String msg, Objects objData) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ResponseCode.checkResponseCode(code));
        jsonObject.put("msg", msg);
        jsonObject.put("data", objData);
        return jsonObject;
    }





}
