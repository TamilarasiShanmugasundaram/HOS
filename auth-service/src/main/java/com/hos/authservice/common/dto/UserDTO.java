package com.hos.authservice.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
public class UserDTO {
    private Long id;

    private String firstName;

    private String username;

    private String phoneNumber;

    @JsonIgnore
    private String otp;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private Long createdBy;

    @JsonIgnore
    private Long updatedBy ;

    @JsonIgnore
    @CreationTimestamp
    private Date createdAt;

    @JsonIgnore
    @UpdateTimestamp
    private Date updatedAt;

    private boolean isActive;

    private boolean isDeleted;
}
