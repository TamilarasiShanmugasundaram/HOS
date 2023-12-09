package com.Hos.core.request.service;

import com.Hos.core.common.dto.CityDTO;
import com.Hos.core.common.model.Request;

import java.util.List;

public interface RequestService {
    List<Request> getRequest();

    List<CityDTO> getAllCities();
}
