package com.Hos.core.user.service.impl;

import com.Hos.core.common.dto.SignUpDTO;
import com.Hos.core.common.dto.UserDTO;
import com.Hos.core.common.exception.CustomException;
import com.Hos.core.common.model.User;
import com.Hos.core.common.util.Constants;
import com.Hos.core.user.repository.UserRepository;
import com.Hos.core.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import java.util.*;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserRepository userRepository;

    private Random random = new Random();

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<User> getAllUsers() {
        return userRepository.findByIsDeletedFalse();
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsernameAndIsDeletedFalse(username);
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findByIdAndIsDeletedFalse(id);
    }

    private String generateUserOtp(String oldOtp) {
        String otp = null;
        boolean isCodeGenerated = Boolean.FALSE;
        while (!isCodeGenerated) {
            otp = String.valueOf(random.nextInt(900000) + 100000);
            if (!otp.equals(oldOtp)) isCodeGenerated = Boolean.TRUE;
        }
        return otp;
    }


    public String sendOtp(String username) {
        User user = getUserByUsername(username);
        String response = null;
        String otp = generateUserOtp(user.getOtp());
        String body = "HOS Security Code:\n" + otp +
                " is the code to complete your login process.\n" +
                "Do not share this code with anyone for security reasons.";
        if (Objects.nonNull(user)) {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo("tamilarasi.shanmugasundaram@ideas2it.com");
            simpleMailMessage.setSubject(Constants.OTP_EMAIL_SUBJECT);
            simpleMailMessage.setText(body);
            simpleMailMessage.setFrom(Constants.EMAIL_FROM);
//            javaMailSender.send(simpleMailMessage);
            response = Constants.OTP_SUCCESS_RESPONSE;
            user.setOtp(otp);
            userRepository.save(user);
        }
        return response;
    }

    @Override
    public UserDTO signup(SignUpDTO request) {
        User user = getUserByUsername(request.getUsername());
        if(!user.getOtp().equals(request.getOtp())) {
            throw new CustomException("Invalid OTP", "401");
        }
        if(!user.getPassword().equals(request.getPassword())) {
            throw new CustomException("Invalid Password", "401");
        }
        user.setPassword(request.getPassword());
        return new ModelMapper().map(userRepository.save(user), UserDTO.class);
    }

    @Override
    public List<User> getCommunityUsers(long cityId) {
        return userRepository.findByCityIdAndIsDeletedFalse(cityId);
    }
}
