package cn.gitbook.securityshare.exception;


import cn.gitbook.securityshare.constants.CodeMsg;
import lombok.Data;

/**
 * 强制业务异常必须提供code码，便于统一维护
 */
public class BusinessException extends RuntimeException {
    private final CodeMsg msg;
    private Object data;
    public BusinessException(CodeMsg msg){
        this.msg = msg;
    }
    public BusinessException(CodeMsg msg,Object data){
        this.msg = msg;
        this.data = data;
    }

    public CodeMsg getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
