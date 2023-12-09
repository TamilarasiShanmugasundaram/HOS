package com.Hos.core.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

public class RequestDTO {
    private long id;

    private String username;


    private String type;

    private String category;

    private CityDTO city;

    private Long createdBy;

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
