package cn.gitbook.securityshare.security.auth.sms;

import cn.gitbook.securityshare.constants.CodeMsg;
import cn.gitbook.securityshare.entity.SmsRecord;
import cn.gitbook.securityshare.entity.User;
import cn.gitbook.securityshare.security.JwtUser;
import cn.gitbook.securityshare.security.auth.LoginSuccessToken;
import cn.gitbook.securityshare.service.ISmsRecordService;
import cn.gitbook.securityshare.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SmsAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private ISmsRecordService smsRecordService;

    @Autowired
    private IUserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsLoginToken token = (SmsLoginToken)authentication;
        String phone = token.getPrincipal().toString();
        String verityCode = token.getCredentials().toString();
        User user = userService.getUserByPhone(phone);
        if(null == user){
            throw new BadCredentialsException(CodeMsg.passwordIncorrect.getMsg());
        }
        //查询db 进行校验
        SmsRecord smsRecord = smsRecordService.getSmsRecordByPhone(phone);
        if(null == smsRecord){
            throw new BadCredentialsException(CodeMsg.passwordIncorrect.getMsg());
        }
        if(smsRecord.getCode().equals(verityCode)){
            List<GrantedAuthority> grantedAuthorities = userService.getUserRolesByUserId(user.getId());
            JwtUser sessionInfo = new JwtUser(user.getUsername(),"",grantedAuthorities,true,"");
            return new LoginSuccessToken(grantedAuthorities,sessionInfo);
        }else {
            throw new AuthenticationServiceException(CodeMsg.passwordIncorrect.getMsg());
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return SmsLoginToken.class.isAssignableFrom(aClass);
    }
}
