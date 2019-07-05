package entity;

/**
 * Created by zhonglq on 2019/6/25.
 */
public class Result<T> {
    private boolean flag;
    private Integer code;
    private String message;
    private Object data;

    public Result(boolean flag, Integer code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public Result() {

    }

    public static Result error() {
        return error("操作失败，请稍后再试");
    }

    public static Result error(String message) {
        return error(StatusCode.ERROR, message);
    }

    public static Result error(int code, String message) {
        return new Result(false, code, message);
    }

    public static Result ok() {
        return ok(null);
    }

    public static Result ok(Object data) {
        return new Result(true, StatusCode.OK, "success", data);
    }


    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
