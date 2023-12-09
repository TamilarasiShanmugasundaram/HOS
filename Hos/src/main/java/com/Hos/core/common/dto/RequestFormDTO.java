package com.Hos.core.common.dto;

import lombok.Data;
@Data
public class RequestFormDTO {

    private String username;
    private String type;

    private String category;

    private long cityId;

    private Long createdBy;

    private Long updatedBy ;

}
