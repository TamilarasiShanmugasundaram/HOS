package com.Hos.core.common.dto;

import com.Hos.core.common.model.User;
import lombok.Data;

@Data
public class ResponseDTO {
    private long id;

    private User user;

    private User request;
}
