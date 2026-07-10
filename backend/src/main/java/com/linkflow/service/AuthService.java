package com.linkflow.service;

import com.linkflow.domain.User;
import com.linkflow.dto.AuthResponse;
import com.linkflow.dto.LoginRequest;
import com.linkflow.dto.RegisterRequest;
import com.linkflow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public AuthResponse register(RegisterRequest request){
        if(userRepository.existsByEmail(request.email())){
            throw new BusinessException("Email já cadastrado");
        }

        User user = User.builder()
                .name(request.name())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(User.Role.USER)
                .active(true)
                .build();

        userRepository.save(user);

        String token = jwtService.generateToken(user.getEmail(), user.getRole().name());
        return AuthResponse.of(token, user.getName(), user.getEmail(),  user.getRole().name());
    }

    @Transactional(readOnly = true)
    public AuthResponse login(LoginRequest request){
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new BusinessException("Credenciais inválidas"));

        if(!passwordEncoder.matches(request.password(), user.getPassword())){
            throw new BusinessExeption("Credenciais inválidas");
        }

        if(!user.getActive()){
            throw new BusinessException("Usuário inativo");
        }

        String token = jwtService.generateToken(user.getEmail(), user.getRole().name());
        return AuthResponse.of(token, user.getName(), user.getEmail(), user.getRole().name());
    }
}
