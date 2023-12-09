package com.Hos.core.user.service.impl;

import com.Hos.core.common.dto.UserDTO;
import com.Hos.core.user.repository.UserRepository;
import com.Hos.core.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public List<UserDTO> getAllUsers() {
        return modelMapper.map(userRepository.findByIsDeletedFalse(), new TypeToken<List<UserDTO>>() {
        }.getType());
    }
}
