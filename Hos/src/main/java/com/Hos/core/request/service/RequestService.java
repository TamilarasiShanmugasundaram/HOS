package com.Hos.core.request.service;

import java.util.List;

import com.Hos.core.common.model.Request;
import com.Hos.core.request.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    public List<Request> getRequest() {
     return  requestRepository.findAll();
    }
}
