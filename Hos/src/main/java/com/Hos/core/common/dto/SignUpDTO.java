package com.Hos.core.common.dto;

import lombok.Data;

@Data
public class SignUpDTO {
    private String username;
    private String phoneNumber;
    private String otp;
    private String password;
}
