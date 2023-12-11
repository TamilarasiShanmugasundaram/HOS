package com.Hos.core.common.dto;

import com.Hos.core.common.model.City;
import lombok.Data;

@Data
public class CommunityDTO {
    private long id;
    private String locality;
    private City cityId;
}
