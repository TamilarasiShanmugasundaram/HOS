package com.Hos.core.common.dto;

import lombok.Data;
@Data
public class RequestFormDTO {
    private long id;

    private String username;

    private String type;

    private String category;

    private long city;

    private Long createdBy;

    private Long updatedBy ;

}
