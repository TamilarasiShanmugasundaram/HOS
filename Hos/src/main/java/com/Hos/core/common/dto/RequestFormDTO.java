package com.Hos.core.common.dto;

import java.util.List;

import lombok.Data;
@Data
public class RequestFormDTO {
    private long id;

    private String username;

    private String type;

    private String category;

	private List<Long> cityIds;

    private String info;

    private Long createdBy;

    private Long updatedBy;

}
