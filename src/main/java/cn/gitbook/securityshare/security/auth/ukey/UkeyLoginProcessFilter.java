package cn.gitbook.securityshare.security.auth.ukey;

import cn.gitbook.securityshare.constants.CodeMsg;
import cn.gitbook.securityshare.exception.MethodNotSupportException;
import cn.gitbook.securityshare.security.auth.BaseLoginProcessFilter;
import cn.gitbook.securityshare.util.AjaxUitl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class UkeyLoginProcessFilter extends BaseLoginProcessFilter {
    private static Logger logger = LoggerFactory.getLogger(UkeyLoginProcessFilter.class);

    protected UkeyLoginProcessFilter(String defaultFilterProcessesUrl, AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failureHandler) {
        super(defaultFilterProcessesUrl, successHandler, failureHandler);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        if (!HttpMethod.POST.name().equals(request.getMethod()) || !AjaxUitl.isAjax(request)) {
            throw new MethodNotSupportException(CodeMsg.methodNotSupport.getMsg());
        }

        //uKey 相关的东西
        String realname = request.getParameter("realname");
        String encodePass = request.getParameter("encodePass");
        if(StringUtils.isEmpty(realname) || StringUtils.isEmpty(encodePass)){
            throw new AuthenticationServiceException(CodeMsg.paramLack.getMsg());
        }
        UkeyLoginToken token = new UkeyLoginToken(Collections.EMPTY_LIST,realname,encodePass);
        return this.getAuthenticationManager().authenticate(token);
    }

}
