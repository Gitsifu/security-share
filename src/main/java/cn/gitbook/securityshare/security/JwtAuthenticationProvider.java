package cn.gitbook.securityshare.security;

import cn.gitbook.securityshare.constants.CodeMsg;
import cn.gitbook.securityshare.entity.User;
import cn.gitbook.securityshare.exception.IllegalTokenException;
import cn.gitbook.securityshare.service.IUserService;
import cn.gitbook.securityshare.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationProvider.class);

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private IUserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            String token = (String)authentication.getCredentials();
            //实际项目中用户信息放在redis中,从Redis中读取，使用Redis还可以方便的集成单点登录问题，用于用户踢出，检测用户是否登录
            String username = jwtUtil.getUsernameFromToken(token);
            User userinfo = userService.getUserByUserName(username);
            List<GrantedAuthority> authorityList = userService.getUserRolesByUserId(userinfo.getId());
            JwtUser user = new JwtUser(jwtUtil.getUsernameFromToken(token),"",authorityList,true,"");
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null, authorityList);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return  authToken;

        }catch (Exception e){
            LOGGER.error("登录异常",e);
            throw new IllegalTokenException(CodeMsg.tokenIllegal.getMsg());
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthToken.class.equals(authentication);
    }
}
