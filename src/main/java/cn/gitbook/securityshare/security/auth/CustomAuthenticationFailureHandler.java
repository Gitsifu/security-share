package cn.gitbook.securityshare.security.auth;

import cn.gitbook.securityshare.constants.CodeMsg;
import cn.gitbook.securityshare.dto.JsonObject;
import cn.gitbook.securityshare.exception.IllegalTokenException;
import cn.gitbook.securityshare.exception.MethodNotSupportException;
import cn.gitbook.securityshare.exception.UkeyNotSupportException;
import cn.gitbook.securityshare.exception.UserForceLoginOutException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);//设置http 返回请求错误码
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        JsonObject<String> resultVo = new JsonObject<>();
        resultVo.setData("");
        if(IllegalTokenException.class.isAssignableFrom(e.getClass())){
            resultVo.setResultCode(CodeMsg.accessDenied.getCode());
            resultVo.setMsg(CodeMsg.accessDenied.getMsg());
            //如果有单点登录的处理，需要在此处定制返回的错误信息，单点登录页属于认证出现了业务异常
        }else if(BadCredentialsException.class.isAssignableFrom(e.getClass())){
            resultVo.setResultCode(CodeMsg.passwordIncorrect.getCode());
            resultVo.setMsg(CodeMsg.passwordIncorrect.getMsg());
        }
        else if(UserForceLoginOutException.class.isAssignableFrom(e.getClass())){
            resultVo.setResultCode(CodeMsg.forceLoginOut.getCode());
            resultVo.setMsg(CodeMsg.forceLoginOut.getMsg());
       }else if(MethodNotSupportException.class.isAssignableFrom(e.getClass())){
            resultVo.setResultCode(CodeMsg.methodNotSupport.getCode());
           resultVo.setMsg(CodeMsg.methodNotSupport.getMsg());
        }else if(UkeyNotSupportException.class.isAssignableFrom(e.getClass())){
            resultVo.setResultCode(CodeMsg.ukeyNotSupported.getCode());
            resultVo.setMsg(CodeMsg.ukeyNotSupported.getMsg());
        }else {
            resultVo.setResultCode(CodeMsg.accessDenied.getCode());
            resultVo.setMsg(CodeMsg.accessDenied.getMsg());
        }
        response.getOutputStream().write(JSON.toJSONBytes(resultVo,SerializerFeature.BrowserCompatible));
     //mapper.writeValue(response.getWriter(), resultVo);
    }
}
