package cn.gitbook.securityshare.dto;

import lombok.Data;

@Data
public class PasswordLoginRequest {
    private String username;
    private String password;
}
