package cn.gitbook.securityshare.exception;

import org.springframework.security.core.AuthenticationException;

public class UkeyNotSupportException extends AuthenticationException {
    public UkeyNotSupportException(String msg) {
        super(msg);
    }
}
