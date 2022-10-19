package com.chaojilaji.meedudata;

public enum CommonResultStatus implements ResultStatus {
    OK(10200, "成功"),
    NOT_EXISTS(10404,"目标不存在");



    private int code;
    private String message;

    CommonResultStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static Integer findCodeByMessage(String message) {
        for (CommonResultStatus commonResultStatus : CommonResultStatus.values()) {
            if (commonResultStatus.message.equals(message)){
                return commonResultStatus.code;
            }
        }
        return 10405;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
