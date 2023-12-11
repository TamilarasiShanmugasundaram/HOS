package com.Hos.core.request.controller;
import com.Hos.core.common.dto.CityDTO;
import com.Hos.core.common.dto.RequestFormDTO;
import com.Hos.core.common.model.Request;
import com.Hos.core.common.util.Constants;
import com.Hos.core.request.service.RequestService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping(value = "/request")
public class RequestController {

    @Autowired
	RequestService requestService;

	@PostMapping
	public Request createRequest(@RequestBody RequestFormDTO requestFormDTO) {
		Request request = new ModelMapper().map(requestFormDTO, Request.class);
		request.setCity(requestService.getCityById(requestFormDTO.getCity()));
		return requestService.createRequest(request);
	}
    @PostMapping("/list")
	public List<Request> getRequests() {
		return requestService.getRequest();
	}

	@GetMapping("/one")
	public String hello1() {
		return "Hellp";
	}

	@GetMapping("/city-list")
	public List<CityDTO> getAllCities() {
		return new ModelMapper().map(requestService.getAllCities(), new TypeToken<List<CityDTO>>() {
		}.getType());
	}

	@PostMapping("/cityById")
	public CityDTO getCityById(@RequestBody Map<String, String> request) {
		return new ModelMapper().map(requestService.getCityById(Long.parseLong(request.get(Constants.ID))), CityDTO.class);
	}
}
