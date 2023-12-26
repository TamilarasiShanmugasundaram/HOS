package com.Hos.core.common.dto;

import com.Hos.core.common.model.User;
import lombok.Data;

@Data
public class UserResponseDTO {

	private User user;
	private String Message;
}
