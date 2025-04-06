package com.example.security.user;

import com.example.security.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public String join(UserDto userDto) {
        try{
            String username = userDto.getUsername();
            String password = userDto.getPassword();
            String hashedPassword = passwordEncoder.encode(password);
            userRepository.save(new User(username, hashedPassword));
        }
        catch(Exception e){
            return "failed";
        }
        return "success";
    }

}
