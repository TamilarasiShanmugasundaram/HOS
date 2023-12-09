package com.Hos.core.user.service;

import com.Hos.core.common.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
}