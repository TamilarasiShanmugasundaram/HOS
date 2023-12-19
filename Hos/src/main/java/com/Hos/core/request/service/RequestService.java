package com.Hos.core.request.service;

import com.Hos.core.common.dto.RequestFormDTO;
import com.Hos.core.common.dto.UserRequestDTO;
import com.Hos.core.common.model.City;
import com.Hos.core.common.model.Request;
import com.Hos.core.common.model.Response;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RequestService {
    List<Request> getRequest();

    List<City> getAllCities();
    Request createRequest(RequestFormDTO request);
    City getCityById(long id);
	Set<City> getCitiesById(List<Long> cityIds);
    Response saveResponse(Map<String, String> request);
    Request accecptResponse(Map<String, String> request);

    Request getRequestById(long id);
    List<Request> getUserRequestList(UserRequestDTO myRequestDTO) throws Exception;
    City getCityByName(String name);

}
