package com.hello.utils.response;

public enum ResponseCode {

    // 成功
    SUCCESS(1000, "成功！"),

    // 失败
    FAIL(9400, "业务处理失败！"),

    // 异常
    EXCEPTION(9500, "业务处理异常！"),

    // 参数缺失
    LACK(9100, "参数缺失！"),
    ;

    /**
     * 响应状态码
     */
    private final int code;
    /**
     * 响应提示
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

    // 通过状态码获取响应信息
    public static ResponseCode getResponseCode(int code) {
        for (ResponseCode r : values()) {
            if (r.code == code) {
                return r;
            }
        }
        return null;
    }

    // 检查定义响应码是否重复？重复则抛出异常
    public static Integer checkResponseCode(Integer code) {
        for (ResponseCode r : values()) {
            if (r.code == code) {
                // 重复响应码
                throw new NumberFormatException();
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
