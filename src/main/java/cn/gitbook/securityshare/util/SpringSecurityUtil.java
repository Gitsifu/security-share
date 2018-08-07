package cn.gitbook.securityshare.util;

import cn.gitbook.securityshare.security.JwtUser;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SpringSecurityUtil {
    private SpringSecurityUtil(){}
    public static String getUsername() {
        String temp = "";
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(null != authentication){
                temp = authentication.getName();
            }
        } catch (Exception e) {
            temp = "";
        }
        return temp;
    }

    public static JwtUser getJwtUser(){
        Authentication  authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getClass().isAssignableFrom(AnonymousAuthenticationToken.class)){
            return null;
        }else{
            JwtUser info = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return info;
        }

    }
}
