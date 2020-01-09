package com.hello.utils.response;

import com.hello.exception.ResponseCodeException;

/**
 * @Name ResponseCode
 * @Description TODO 响应状态信息
 * @Date 2020/1/7 10:58
 * @Version 1.0.0
 */
public enum ResponseCode {

    // 成功
    SUCCESS(10000, "成功！"),
    // 系统繁忙
    BUSY(10001, "系统繁忙！"),
    // 请刷新重试
    RETRY(10003, "请刷新重试！"),
    // 失败
    FAIL(10004, "失败！"),
    // 异常
    EXCEPTION(10005, "异常！"),
    // 参数缺失
    LACK(10007, "参数缺失！"),
    ;

    /**
     * 响应状态码
     */
    private final int code;
    /**
     * 响应提示信息
     */
    private final String msg;

    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }

    /**
     * @description 通过状态码获取响应信息
     * @date 2020/1/8 11:55
     * @param code : 状态码
     * @return com.hello.utils.response.ResponseCode
     * @throws
     */
    public static ResponseCode getResponseCode(int code) {
        for (ResponseCode r : values()) {
            if (r.code == code) {
                return r;
            }
        }
        return null;
    }

    /**
     * @description 检查定义响应码是否是系统状态码？
     * @date 2020/1/8 11:55
     * @param code : 状态码
     * @return java.lang.Integer
     * @throws ResponseCodeException 如果状态码是系统状态码，则抛出异常
     */
    public static Integer checkResponseCode(Integer code) throws ResponseCodeException {
        for (ResponseCode r : values()) {
            if (r.code == code) {
                // 重复响应码
                throw new ResponseCodeException(code+ " is the current system response status code, not available（" +code+ "是当前系统响应状态码，不可使用）");
            }
        }
//        // 检查自定义提示信息是否有重复
//        for (CustomMsg c : CustomMsg.values()) {
//            if (c.code == code) {
//                // 重复响应码
//                throw new NumberFormatException();
//            }
//        }
        return code;
    }



//    // 自定义提示信息
//    enum CustomMsg {
//
//        LACK(9100, "未定义参数缺失信息！"),
//        ;
//
//        private final int code;
//        private String msg;
//
//        CustomMsg(int code, String msg) {
//            this.code = code;
//            this.msg = msg;
//        }
//        public int getCode() {
//            return code;
//        }
//        public String getMsg() {
//            return msg;
//        }
//        public CustomMsg setMsg(String msg) {
//            this.msg = msg;
//            return this;
//        }
//
//        public static CustomMsg getCustomMsg(int code) {
//            for (CustomMsg c : values()) {
//                if (c.code == code) {
//                    return c;
//                }
//            }
//            return null;
//        }
//    }
//
//
//
//    // 自定义状态码与提示信息
//    enum Custom {
//
//        RESULT(9999, "未定义状态码！");
//
//        private int code;
//        private String msg;
//
//        Custom(int code, String msg) {
//            this.code = code;
//            this.msg = msg;
//        }
//        public int getCode() {
//            return code;
//        }
//        public void setCode(int code) {
//            this.code = code;
//        }
//        public String getMsg() {
//            return msg;
//        }
//        public void setMsg(String msg) {
//            this.msg = msg;
//        }
//        public Custom setResult(int code, String msg) {
//            this.code = code;
//            this.msg = msg;
//            return RESULT;
//        }
//
//    }

}
