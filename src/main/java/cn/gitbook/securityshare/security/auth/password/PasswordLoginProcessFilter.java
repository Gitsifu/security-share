package cn.gitbook.securityshare.security.auth.password;

import cn.gitbook.securityshare.security.auth.BaseLoginProcessFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

    protected PasswordLoginProcessFilter(String defaultFilterProcessesUrl, AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failureHandler) {
        super(defaultFilterProcessesUrl, successHandler, failureHandler);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        checkMethod(request);
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username,password);
        return this.getAuthenticationManager().authenticate(token);
    }

}
