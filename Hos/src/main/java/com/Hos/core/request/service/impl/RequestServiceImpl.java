package com.Hos.core.request.service.impl;

import com.Hos.core.common.model.City;
import com.Hos.core.common.model.Request;
import com.Hos.core.request.repository.CityRepository;
import com.Hos.core.request.repository.RequestRepository;
import com.Hos.core.request.service.RequestService;
import org.modelmapper.ModelMapper;
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
    public List<City> getAllCities() {
        return cityRepository.findByIsDeletedFalse();
    }

    @Override
    public Request createRequest(Request request) {
        // Request rr1 = requestRepository.save(request);
        // System.out.println("values sssssssssssssssssssss1 " + rr1);
        // Request rr2 =requestRepository.save(request);
        // System.out.println("values sssssssssssssssssssss1 2" + rr2);

        // Request rr3 =requestRepository.save(request);
         System.out.println("values sssssssssssssssssssss33333333 " + request.getId());
         System.out.println("values sssssssssssssssssssss33333333 " + request.getId());
         System.out.println("values sssssssssssssssssssss33333333 " + request.getId());
         System.out.println("values sssssssssssssssssssss33333333 " + request.getId());
         System.out.println("values sssssssssssssssssssss33333333 " + request.getId());
         System.out.println("values sssssssssssssssssssss33333333 " + request.getId());


        return requestRepository.save(request);
    }

    @Override
    public City getCityById(long id) {
        return cityRepository.getCityById(id);
    }


}
