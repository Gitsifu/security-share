package cn.gitbook.securityshare.service.impl;

import cn.gitbook.securityshare.entity.Role;
import cn.gitbook.securityshare.entity.User;
import cn.gitbook.securityshare.service.IUserService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public User getUserByUserName(String username) {
        return null;
    }


    @Override
    public User getUserById(Long id) {
        if(1L == id){
            return User.builder().id(1L).username("user1").password(encoder.encode("123")).build();
        }else if(2L == id){
            return User.builder().id(2L).username("user2").password(encoder.encode("123")).build();
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
}
