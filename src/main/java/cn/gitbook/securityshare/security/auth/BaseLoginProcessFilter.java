package cn.gitbook.securityshare.security.auth;

import cn.gitbook.securityshare.constants.CodeMsg;
import cn.gitbook.securityshare.exception.MethodNotSupportException;
import cn.gitbook.securityshare.util.AjaxUitl;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class BaseLoginProcessFilter extends AbstractAuthenticationProcessingFilter {
    private final AuthenticationSuccessHandler successHandler;
    private final AuthenticationFailureHandler failureHandler;
    protected BaseLoginProcessFilter(String defaultFilterProcessesUrl,AuthenticationSuccessHandler successHandler,
                                     AuthenticationFailureHandler failureHandler) {
        super(defaultFilterProcessesUrl);
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        successHandler.onAuthenticationSuccess(request, response, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        failureHandler.onAuthenticationFailure(request, response, failed);
    }

    public void checkMethod(HttpServletRequest request){
        if (!HttpMethod.POST.name().equals(request.getMethod()) || !AjaxUitl.isAjax(request)) {
            throw new MethodNotSupportException(CodeMsg.methodNotSupport.getMsg());
        }
    }
}
