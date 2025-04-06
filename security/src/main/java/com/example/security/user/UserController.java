package com.example.security.user;

import com.example.security.security.MyUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public String mainPage(){
        return "main page";
    }

    @PostMapping("/join")
    public String join(@RequestBody UserDto userDto) {
        return userService.join(userDto);
    }

    @PostMapping("/login")
    public void login(@RequestBody UserDto userDto){}

    @GetMapping("/user")
    public String checkUser(@AuthenticationPrincipal MyUserDetails myUserDetails) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        String rol = SecurityContextHolder.getContext().getAuthentication().getAuthorities().iterator().next().getAuthority();

        String username = myUserDetails.getUsername();
        String password = myUserDetails.getPassword();
        String role = myUserDetails.getAuthorities().iterator().next().getAuthority();
        return "AuthenticationPrincipal: "+username+" / "+password+" / "+role +"\nSecurityContextHolder: " +name+" / "+rol ;
    }

    @GetMapping("/admin")
    public String checkAdmin(@AuthenticationPrincipal MyUserDetails myUserDetails) {
        String username = myUserDetails.getUsername();
        String role = myUserDetails.getAuthorities().iterator().next().getAuthority();
        return username+" : "+role;
    }
}
