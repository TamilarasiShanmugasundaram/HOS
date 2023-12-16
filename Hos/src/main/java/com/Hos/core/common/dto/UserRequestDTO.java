package com.Hos.core.common.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRequestDTO {
	private long id;

	private Boolean isMyRequest;

	private String type;

	private List<Long> cityIds;

	private Long createdBy;

	private boolean isRequestClosed;

}
