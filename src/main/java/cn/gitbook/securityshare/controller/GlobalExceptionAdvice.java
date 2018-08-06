package cn.gitbook.securityshare.controller;


import cn.gitbook.securityshare.constants.CodeMsg;
import cn.gitbook.securityshare.dto.JsonObject;
import cn.gitbook.securityshare.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionAdvice {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionAdvice.class);
    @ExceptionHandler(AccessDeniedException.class)
    public JsonObject handleAccessDeniedException(){
        return JsonObject.error(CodeMsg.accessDenied);
    }


    @ExceptionHandler(BusinessException.class)
    public JsonObject handleBusinessException(BusinessException e){
        return JsonObject.error(e.getMsg());
    }

    @ExceptionHandler(Exception.class)
    public JsonObject handleException(Exception e){
        logger.error("系统异常",e);
        return JsonObject.error(CodeMsg.sysError);
    }
}
