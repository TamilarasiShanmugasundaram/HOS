package com.Hos.core.common.dto;

import com.Hos.core.common.model.City;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
public class RequestDTO {
    private long id;

    private String username;

    private String type;

    private String category;

	private Set<City> cities;

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

    private boolean isRequestClosed;

    private List<Long> responserId;


    private String info;

    private boolean isMyRequest;
}
