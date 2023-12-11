package com.Hos.core.user.service;

import com.Hos.core.common.dto.SignUpDTO;
import com.Hos.core.common.dto.UserDTO;
import com.Hos.core.common.exception.CustomException;
import com.Hos.core.common.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<User> getAllUsers();

    User getUserByUsername(String username);

    String sendOtp(String username);
    UserDTO signup(SignUpDTO request) throws CustomException;

    List<User> getCommunityUsers(long cityId);
}