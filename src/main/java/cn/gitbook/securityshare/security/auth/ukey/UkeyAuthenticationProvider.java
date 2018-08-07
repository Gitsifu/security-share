package cn.gitbook.securityshare.security.auth.ukey;

import cn.gitbook.securityshare.constants.CodeMsg;
import cn.gitbook.securityshare.exception.UkeyNotSupportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class UkeyAuthenticationProvider implements AuthenticationProvider {
    private static final Logger logger = LoggerFactory.getLogger(UkeyAuthenticationProvider.class);
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UkeyLoginToken token = (UkeyLoginToken)authentication;
        //ukey中一般会传递用户的唯一信息例如身份证号 进行解析
        String realname = token.getPrincipal().toString();
        String encodePass = token.getCredentials().toString();
        logger.info("======= 银行登录传递过来的参数 realname: "+ realname);
        logger.info("======= 银行登录传递过来的参数 encodePass: "+ encodePass);
        throw new UkeyNotSupportException(CodeMsg.ukeyNotSupported.getMsg());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UkeyLoginToken.class.isAssignableFrom(aClass);
    }
}
