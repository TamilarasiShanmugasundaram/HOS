package com.Hos.core.request.controller;
import com.Hos.core.common.dto.CityDTO;
import com.Hos.core.common.dto.RequestDTO;
import com.Hos.core.common.dto.RequestFormDTO;
import com.Hos.core.common.dto.ResponseDTO;
import com.Hos.core.common.dto.UserRequestDTO;
import com.Hos.core.common.model.Request;
import com.Hos.core.common.model.Response;
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
		return requestService.createRequest(requestFormDTO);
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

	@PostMapping("/city-by-id")
	public CityDTO getCityById(@RequestBody Map<String, String> request) {
		return new ModelMapper().map(requestService.getCityById(Long.parseLong(request.get(Constants.ID))), CityDTO.class);
	}

	@PostMapping("/city-by-name")
	public CityDTO getCityByName(@RequestBody Map<String, String> request) {
		return new ModelMapper().map(requestService.getCityByName(request.get(Constants.NAME)), CityDTO.class);
	}


	@PostMapping("/request-by-id")
	public RequestDTO getRequestById(@RequestBody Map<String, String> request) {
		return new ModelMapper().map(requestService.getRequestById(Long.parseLong(request.get(Constants.ID))), RequestDTO.class);
	}

	@PostMapping("save-response")
	public ResponseDTO saveResponse(@RequestBody Map<String, String> request) {
		return new ModelMapper().map(requestService.saveResponse(request), ResponseDTO.class);
	}

	@PostMapping("/user-request-list")
	public List<Request> getUserRequestList(@RequestBody UserRequestDTO userRequestDTO) throws Exception {
		return requestService.getUserRequestList(userRequestDTO);
	}

}
