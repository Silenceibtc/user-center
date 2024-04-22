package cn.edu.dlmu.back.common;

/**
 * 返回工具类
 *
 * @author silenceibtc
 */
public class ResultUtils {

    /**
     * 成功
     *
     * @param data 返回数据
     * @return 通用结果返回对象
     * @param <T> 返回数据类型
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(0, data, "ok");
    }

    /**
     * 失败
     *
     * @param errorCode 错误类
     * @return 通用结果返回对象
     */
    public static <T> BaseResponse<T> error(ErrorCode errorCode) {
        return new BaseResponse<>(errorCode);
    }

    /**
     * 失败
     *
     * @param code 自定义状态码
     * @param message 自定义信息
     * @param description 自定义描述
     * @return 通用结果返回对象
     */
    public static <T> BaseResponse<T> error(int code, String message, String description) {
        return new BaseResponse<>(code, null, message, description);
    }
}
