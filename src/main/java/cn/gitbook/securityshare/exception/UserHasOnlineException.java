package cn.gitbook.securityshare.exception;

import cn.gitbook.securityshare.constants.CodeMsg;

//用户已经在线的异常
public class UserHasOnlineException extends BusinessException {
    public UserHasOnlineException(CodeMsg msg){
        super(msg);
    }
}
