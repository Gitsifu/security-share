package cn.gitbook.securityshare.security.auth.sms;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class SmsLoginToken extends AbstractAuthenticationToken {

    private final String phone;
    private final String verifyCode;

    public SmsLoginToken(Collection<? extends GrantedAuthority> authorities,String phone,String verifyCode) {
        super(authorities);
        this.phone = phone;
        this.verifyCode = verifyCode;
    }

    @Override
    public Object getCredentials() {
        return verifyCode;
    }

    @Override
    public Object getPrincipal() {
        return phone;
    }
}
