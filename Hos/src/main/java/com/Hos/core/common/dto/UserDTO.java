package com.Hos.core.common.dto;

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

    private Long createdBy;

    private Long updatedBy ;

    @JsonIgnore
    @CreationTimestamp
    private Date createdAt;

    @JsonIgnore
    @UpdateTimestamp
    private Date updatedAt;

    private boolean isActive = true;

    private boolean isDeleted = false;
}
