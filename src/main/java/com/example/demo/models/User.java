package com.example.demo.models;


import com.example.demo.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    private Integer id;
    private String username;
    private String password;
    private Boolean enable;
    private Boolean locked;
    private List<Role> roles;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Autowired
    private UserMapper userMapper;

    /*获取当前用户对象所具有的角色信息*/
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : this.roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    /*当前账户是否未过期*/
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /*当前账户是否未锁定*/
    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    /*当前账号密码是否未过期*/
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /*当前账号是否可用*/
    @Override
    public boolean isEnabled() {
        return enable;
    }

}

