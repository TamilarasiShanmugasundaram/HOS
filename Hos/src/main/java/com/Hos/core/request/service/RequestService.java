package com.Hos.core.request.service;

import com.Hos.core.common.model.City;
import com.Hos.core.common.model.Request;
import com.Hos.core.common.model.Response;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface RequestService {
    List<Request> getRequest();

    List<City> getAllCities();
    Request createRequest(Request request);
    City getCityById(long id);
    Response saveResponse(Map<String, String> request);
    Request getRequestById(long id);

}
