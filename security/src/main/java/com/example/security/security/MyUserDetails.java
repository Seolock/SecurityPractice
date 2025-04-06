package com.example.security.security;

import com.example.security.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@RequiredArgsConstructor
public class MyUserDetails implements UserDetails {

    private final User user;


    public Collection<GrantedAuthority> getAuthorities(){
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(user::getRole);
        return authorities;
    }

    public String getPassword() {
        return user.getPassword();
    }

    public String getUsername() {
        return user.getUsername();
    }

// 아래 함수들은 return true 가 디폴트기 때문에 필요하면 재정의한다
//
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//    public boolean isEnabled() {
//        return true;
//    }

}
