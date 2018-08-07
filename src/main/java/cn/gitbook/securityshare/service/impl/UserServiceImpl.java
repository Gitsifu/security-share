package cn.gitbook.securityshare.service.impl;

import cn.gitbook.securityshare.entity.Role;
import cn.gitbook.securityshare.entity.User;
import cn.gitbook.securityshare.service.IUserService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {


    @Override
    public User getUserByUserName(String username) {
        if("user1".equals(username)){
            return User.builder().id(1L).username("user1").password("123").phone("13888888888").build();
        }else if("user2".equals(username)){
            return User.builder().id(2L).username("user2").password("123").phone("13888888888").build();
        }
        return null;
    }


    @Override
    public User getUserById(Long id) {
        if(1L == id){
            return User.builder().id(1L).username("user1").password("123").phone("13888888888").build();
        }else if(2L == id){
            return User.builder().id(2L).username("user2").password("123").phone("13888888888").build();
        }
        return null;
    }

    @Override
    public List<Role> getUserRolesById(Long id) {
        List<Role> list = Lists.newArrayList();
        if(1L == id){
            list.add(Role.builder().role("query_order").build());
            return list;
        }else if(2L == id){
            list.add(Role.builder().role("").build());
            return list;
        }
        return Collections.emptyList();
    }

    @Override
    public User getUserByPhone(String phone) {
        if("13888888888".equals(phone)){
            return User.builder().id(1L).username("user1").password("123").phone("13888888888").build();
        }
        return null;
    }

    @Override
    public List<GrantedAuthority> getUserRolesByUserId(Long id) {
       List<Role> roleList =  getUserRolesById(id);
        List<GrantedAuthority> grantedAuthorities = Lists.newArrayList();
        if(null != roleList && roleList.size() > 0){
            roleList.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
        }
        return grantedAuthorities;
    }
}
