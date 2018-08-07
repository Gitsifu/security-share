package cn.gitbook.securityshare.security.auth.password;

import cn.gitbook.securityshare.dto.PasswordLoginRequest;
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

public class PasswordLoginProcessFilter extends BaseLoginProcessFilter {
    private static Logger logger = LoggerFactory.getLogger(PasswordLoginProcessFilter.class);

    public PasswordLoginProcessFilter(String defaultFilterProcessesUrl, AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failureHandler,ObjectMapper mapper) {
        super(defaultFilterProcessesUrl, successHandler, failureHandler,mapper);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        checkMethod(request);
        PasswordLoginRequest loginRequest = new PasswordLoginRequest();
        loginRequest.setUsername(request.getParameter("username"));
        loginRequest.setPassword(request.getParameter("password"));
        UsernamePasswordToken token = new UsernamePasswordToken(null,loginRequest.getUsername(),loginRequest.getPassword());
        return this.getAuthenticationManager().authenticate(token);
    }

}
