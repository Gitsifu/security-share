package cn.gitbook.securityshare.security.auth.password;

import cn.gitbook.securityshare.constants.CodeMsg;
import cn.gitbook.securityshare.entity.Role;
import cn.gitbook.securityshare.entity.User;
import cn.gitbook.securityshare.security.JwtUser;
import cn.gitbook.securityshare.security.auth.LoginSuccessToken;
import cn.gitbook.securityshare.service.IUserService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PasswordAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private IUserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken)authentication;
        //此处获取到前台提交的信息进行数据的校验
        String username = token.getUsername();
        User user = userService.getUserByUserName(username);
        if(null == user){
            throw new BadCredentialsException(CodeMsg.passwordIncorrect.getMsg());
        }
        if(user.getPassword().equals(token.getPassword())){
            List<GrantedAuthority> grantedAuthorities = userService.getUserRolesByUserId(user.getId());
            JwtUser sessionInfo = new JwtUser(user.getUsername(),"",grantedAuthorities,true,"");
            //此处将权限放入Authentication 中是为了在成功处理器中能拿到权限信息，实际项目中可以在成功处理器中，将用户的信息存入Redis，提高系统性能
            return new LoginSuccessToken(grantedAuthorities,sessionInfo);
        }else {
            throw new AuthenticationServiceException(CodeMsg.passwordIncorrect.getMsg());
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordToken.class.isAssignableFrom(aClass);
    }
}
