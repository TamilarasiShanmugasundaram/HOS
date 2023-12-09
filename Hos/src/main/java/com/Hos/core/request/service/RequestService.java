package com.Hos.core.request.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Hos.core.request.Model.Request;
import com.Hos.core.request.repositary.RequestRepositary;

@Service
public class RequestService {

    @Autowired
    private RequestRepositary requestRepositary;

    public List<Request> getRequest() {
     return  requestRepositary.findAll(); 
    }
}
