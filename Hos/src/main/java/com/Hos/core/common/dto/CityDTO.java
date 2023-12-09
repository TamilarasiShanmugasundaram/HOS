package com.Hos.core.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
public class CityDTO {

    private long id;
    private String name;

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
