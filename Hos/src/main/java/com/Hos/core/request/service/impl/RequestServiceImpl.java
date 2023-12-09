package com.Hos.core.request.service.impl;

import com.Hos.core.common.dto.CityDTO;
import com.Hos.core.common.model.Request;
import com.Hos.core.request.repository.CityRepository;
import com.Hos.core.request.repository.RequestRepository;
import com.Hos.core.request.service.RequestService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private CityRepository cityRepository;

    private ModelMapper modelMapper = new ModelMapper();


    @Override
    public List<Request> getRequest() {
        return requestRepository.findAll();
    }

    @Override
    public List<CityDTO> getAllCities() {
        return modelMapper.map(cityRepository.findByIsDeletedFalse(), new TypeToken<List<CityDTO>>() {
        }.getType());
    }
}
