package cn.gitbook.securityshare.security.auth.ukey;

import cn.gitbook.securityshare.dto.UkeyLoginRequest;
import cn.gitbook.securityshare.security.auth.BaseLoginProcessFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    protected UkeyLoginProcessFilter(String defaultFilterProcessesUrl, AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failureHandler, ObjectMapper mapper) {
        super(defaultFilterProcessesUrl, successHandler, failureHandler,mapper);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        //uKey 相关的东西
        UkeyLoginRequest loginRequest = getMapper().readValue(request.getReader(),UkeyLoginRequest.class);
        UkeyLoginToken token = new UkeyLoginToken(Collections.EMPTY_LIST,loginRequest.getRealName(),loginRequest.getEncodePass());
        return this.getAuthenticationManager().authenticate(token);
    }

}
