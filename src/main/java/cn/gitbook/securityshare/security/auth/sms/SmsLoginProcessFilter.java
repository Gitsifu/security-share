package cn.gitbook.securityshare.security.auth.sms;

import cn.gitbook.securityshare.constants.CodeMsg;
import cn.gitbook.securityshare.dto.SmsLoginRequest;
import cn.gitbook.securityshare.security.auth.BaseLoginProcessFilter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

public class SmsLoginProcessFilter extends BaseLoginProcessFilter {
    private static Logger logger = LoggerFactory.getLogger(SmsLoginProcessFilter.class);

    public SmsLoginProcessFilter(String defaultFilterProcessesUrl, AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failureHandler, ObjectMapper mapper) {
        super(defaultFilterProcessesUrl, successHandler, failureHandler,mapper);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        checkMethod(request);
        SmsLoginRequest smsLoginRequest = getMapper().readValue(request.getReader(),SmsLoginRequest.class);
//        if(StringUtils.isEmpty(phone) || StringUtils.isEmpty(verifyCode)){
//            throw new AuthenticationServiceException(CodeMsg.paramLack.getMsg());
//        }
        SmsLoginToken smsLoginToken = new SmsLoginToken(Collections.EMPTY_LIST,smsLoginRequest.getPhone(),smsLoginRequest.getVerifyCode());
        return this.getAuthenticationManager().authenticate(smsLoginToken);
    }
}
