package com.Hos.core.request.controller;
import com.Hos.core.common.dto.CityDTO;
import com.Hos.core.common.model.Request;
import com.Hos.core.request.service.RequestService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping(value = "/request")
public class RequestController {

    @Autowired
	RequestService requestService;

    @GetMapping("/list")
	public List<Request> hello() {
		return requestService.getRequest();
	}

	@GetMapping("/city-list")
	public List<CityDTO> getAllCities() {
		return requestService.getAllCities();
	}
}
