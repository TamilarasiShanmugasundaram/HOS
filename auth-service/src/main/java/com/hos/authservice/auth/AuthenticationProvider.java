package com.hos.authservice.auth;

import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.hos.authservice.common.model.User;
import com.hos.authservice.user.repository.UserRepository;


public class AuthenticationProvider implements org.springframework.security.authentication.AuthenticationProvider {

    @Autowired
    UserRepository userRepository;

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = String.valueOf(authentication.getPrincipal()).toLowerCase();
        String password = String.valueOf(authentication.getCredentials());
        User user = userRepository.findByUsernameAndPasswordAndIsDeletedFalse(username, password);
        if (!Objects.isNull(user)) {
            return new UsernamePasswordAuthenticationToken(user, password, Set.of());
        }
        throw new BadCredentialsException("Invalid username or password");
    }

    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
