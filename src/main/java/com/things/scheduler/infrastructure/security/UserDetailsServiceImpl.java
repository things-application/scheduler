package com.things.scheduler.infrastructure.security;

import com.things.scheduler.business.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.things.scheduler.infrastructure.client.UserClient;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl {

    private final UserClient client;

    public  UserDetails loadUserByUsername(String token, String email) throws UsernameNotFoundException {
        UserResponse user = client.getUserByEmail(token, email);
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail()) // Define o nome de usuário como o e-mail
                .password(user.getPassword()) // Define a senha do usuário
                .build(); // Constrói o objeto UserDetails
    }
}
