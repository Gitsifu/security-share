package cn.gitbook.securityshare.security.auth;

import cn.gitbook.securityshare.constants.CodeMsg;
import cn.gitbook.securityshare.dto.JsonObject;
import cn.gitbook.securityshare.exception.IllegalTokenException;
import cn.gitbook.securityshare.exception.MethodNotSupportException;
import cn.gitbook.securityshare.exception.UkeyNotSupportException;
import cn.gitbook.securityshare.exception.UserForceLoginOutException;
import cn.gitbook.securityshare.util.HandleHttpErrorUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private final ObjectMapper mapper;
    @Autowired
    public CustomAuthenticationFailureHandler(ObjectMapper mapper){
        this.mapper = mapper;
    }
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException e) throws IOException, ServletException {
        try{
            HandleHttpErrorUtil.handleHttpError(request,response,e);
        }catch (Exception throwable){
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
        }
     //mapper.writeValue(response.getWriter(), resultVo);
    }
}
