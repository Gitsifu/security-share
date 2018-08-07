package cn.gitbook.securityshare.service;

import cn.gitbook.securityshare.entity.Role;
import cn.gitbook.securityshare.entity.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public interface IUserService {
    User getUserByUserName(String username);
    User getUserById(Long id);
    List<Role> getUserRolesById(Long id);
    User getUserByPhone(String phone);

    List<GrantedAuthority> getUserRolesByUserId(Long id);
}
