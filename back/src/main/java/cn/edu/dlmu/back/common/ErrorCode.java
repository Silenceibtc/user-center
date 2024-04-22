package cn.edu.dlmu.back.common;

/**
 * 错误码
 *
 * @author silenceibtc
 */
public enum ErrorCode {
    PARAMS_ERROR(40000, "请求参数错误", ""),
    NULL_ERROR(400001, "请求数据为空", ""),
    NOT_LOGIN(40100, "未登录", ""),
    NO_AUTH(40101, "无权限", "");

    /**
     * 错误码
     */
    private final int code;

    /**
     * 错误信息
     */
    private final String message;

    /**
     * 具体描述
     */
    private final String description;

    ErrorCode(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
