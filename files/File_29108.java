package com.sohu.cache.constant;

/**
 * æ•°æ?®æ ¼å¼?æ£€æµ‹
 * @author leifu
 * @Date 2016å¹´7æœˆ4æ—¥
 * @Time ä¸‹å?ˆ5:37:03
 */
public class DataFormatCheckResult {

    private int status;

    private String message;
    
    private final static int SUCCESS = 1;
    private final static int FAIL = 0;

    public DataFormatCheckResult(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public boolean isSuccess() {
        if (status == SUCCESS) {
            return true;
        }
        return false;
    }

    public static DataFormatCheckResult success(String message) {
        return new DataFormatCheckResult(SUCCESS, message);
    }

    public static DataFormatCheckResult fail(String message) {
        return new DataFormatCheckResult(FAIL, message);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DataFormatCheckResult [status=" + status + ", message=" + message + "]";
    }


}
