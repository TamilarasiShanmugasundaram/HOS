package com.Hos.core.request.service;

import com.Hos.core.common.model.City;
import com.Hos.core.common.model.Request;

import java.util.List;

public interface RequestService {
    List<Request> getRequest();

    List<City> getAllCities();
    Request createRequest(Request request);
    City getCityById(long id);
}
