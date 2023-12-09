package com.Hos.core.user.service;

import com.Hos.core.common.dto.UserDTO;
import com.Hos.core.common.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserByUsername(String username);

    String sendOtp(String username);
}