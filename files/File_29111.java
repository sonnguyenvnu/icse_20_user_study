package com.sohu.cache.constant;

/**
 * 水平扩容结果标识
 * @author leifu
 * @Date 2016年12月4日
 * @Time 下�?�1:30:19
 */
public class HorizontalResult {

    private int status;

    private String message;

    public HorizontalResult(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public static HorizontalResult checkSuccess() {
        return new HorizontalResult(1, "所有检查都�?功，�?�以开始水平扩容了!");
    }
    
    public static HorizontalResult scaleSuccess() {
        return new HorizontalResult(1, "水平扩容已�?�?功开始!");
    }

    public static HorizontalResult fail(String message) {
        return new HorizontalResult(0, message);
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
		return "HorizontalResult [status=" + status + ", message=" + message + "]";
	}

}
