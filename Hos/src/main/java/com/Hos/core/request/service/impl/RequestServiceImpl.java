package com.Hos.core.request.service.impl;

import com.Hos.core.common.model.City;
import com.Hos.core.common.model.Request;
import com.Hos.core.common.model.Response;
import com.Hos.core.common.util.Constants;
import com.Hos.core.request.repository.CityRepository;
import com.Hos.core.request.repository.RequestRepository;
import com.Hos.core.request.repository.ResponseRepository;
import com.Hos.core.request.service.RequestService;
import com.Hos.core.user.repository.UserRepository;
import com.Hos.core.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private ResponseRepository responseRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private UserService userService;

    private ModelMapper modelMapper = new ModelMapper();


    @Override
    public List<Request> getRequest() {
        return requestRepository.findAll();
    }

    @Override
    public List<City> getAllCities() {
        return cityRepository.findByIsDeletedFalse();
    }

    @Override
    public Request createRequest(Request request) {
        return requestRepository.save(request);
    }

    @Override
    public City getCityById(long id) {
        return cityRepository.getCityById(id);
    }

    @Override
    public Request getRequestById(long id) {
        return requestRepository.findByIdAndIsDeletedFalseAndIsRequestClosedFalse(id);
    }
    @Override
    public City getCityByName(String name) {
        return cityRepository.getCityByName(name);
    }

    @Override
    public Response saveResponse(Map<String, String> request) {
        Response response = new Response();
        response.setNotes(request.get(Constants.NOTES));
        response.setRequest(getRequestById(Long.parseLong(request.get(Constants.REQUESTID))));
        response.setUser(userService.getUserById(Long.parseLong(request.get(Constants.USERID))));
        response.setCreatedBy(Long.parseLong(request.get(Constants.USERID)));
        response.setUpdatedBy(Long.parseLong(request.get(Constants.USERID)));
        return responseRepository.save(response);
    }
}
