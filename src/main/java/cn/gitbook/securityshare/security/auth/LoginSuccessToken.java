package cn.gitbook.securityshare.security.auth;

import cn.gitbook.securityshare.security.JwtUser;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class LoginSuccessToken extends AbstractAuthenticationToken {

    private final JwtUser user;

    public LoginSuccessToken(Collection<? extends GrantedAuthority> authorities,JwtUser user) {
        super(authorities);
        this.user = user;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }
}
