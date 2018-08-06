package cn.gitbook.securityshare.security;

import cn.gitbook.securityshare.constants.CodeMsg;
import cn.gitbook.securityshare.dto.JsonObject;
import cn.gitbook.securityshare.exception.IllegalTokenException;
import cn.gitbook.securityshare.exception.MethodNotSupportException;
import cn.gitbook.securityshare.exception.UserForceLoginOutException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setStatus(SC_FORBIDDEN);//设置http 返回请求错误码
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        JsonObject<String> resultVo = new JsonObject<>();
        resultVo.setData("");
        if(IllegalTokenException.class.isAssignableFrom(authException.getClass())){
            resultVo.setResultCode(CodeMsg.accessDenied.getCode());
            resultVo.setMsg(CodeMsg.accessDenied.getMsg());
        }else if(UserForceLoginOutException.class.isAssignableFrom(authException.getClass())){
            resultVo.setResultCode(CodeMsg.forceLoginOut.getCode());
            resultVo.setMsg(CodeMsg.forceLoginOut.getMsg());
        }else if(MethodNotSupportException.class.isAssignableFrom(authException.getClass())){
            resultVo.setResultCode(CodeMsg.methodNotSupport.getCode());
            resultVo.setMsg(CodeMsg.methodNotSupport.getMsg());
        }
        else {
            resultVo.setResultCode(CodeMsg.accessDenied.getCode());
            resultVo.setMsg(CodeMsg.accessDenied.getMsg());
        }
        response.getOutputStream().write(JSON.toJSONBytes(resultVo,SerializerFeature.BrowserCompatible));
    }
}
