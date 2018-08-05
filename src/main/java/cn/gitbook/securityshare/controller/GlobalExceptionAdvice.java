package cn.gitbook.securityshare.controller;


import cn.gitbook.securityshare.constants.CodeMsg;
import cn.gitbook.securityshare.dto.JsonObject;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(AccessDeniedException.class)
    public JsonObject handleAccessDeniedException(){
        return JsonObject.error(CodeMsg.accessDenied);
    }
}
