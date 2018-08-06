package cn.gitbook.securityshare.security.auth.ukey;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UkeyLoginToken extends AbstractAuthenticationToken {

    private final String realname;
    private final String encodePass;

    public UkeyLoginToken(Collection<? extends GrantedAuthority> authorities,String realname,String encodePass) {
        super(authorities);
        this.realname = realname;
        this.encodePass = encodePass;
    }

    @Override
    public Object getCredentials() {
        return encodePass;
    }

    @Override
    public Object getPrincipal() {
        return realname;
    }
}
