package cn.gitbook.securityshare.dto;

import cn.gitbook.securityshare.constants.CodeMsg;
import lombok.Data;

import java.io.Serializable;

@Data
public class JsonObject<T> implements Serializable {
    private Long resultCode;
    private String msg;
    private T data;

    public static <T> JsonObject<T> success(T data) {
        JsonObject<T> obj = new JsonObject<>();
        obj.setResultCode(CodeMsg.sucess.getCode());
        obj.setData(data);
        obj.setMsg("SUCCESS");
        return obj;
    }

    public static <T> JsonObject<T> error(CodeMsg cm) {
        JsonObject<T> obj = new JsonObject<>();
        obj.setResultCode(cm.getCode());
        obj.setMsg(cm.getMsg());
        return obj;
    }

}
