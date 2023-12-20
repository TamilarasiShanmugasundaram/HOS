package com.Hos.core.request.controller;
import com.Hos.core.common.dto.*;
import com.Hos.core.common.model.Request;
import com.Hos.core.common.model.Response;
import com.Hos.core.common.util.Constants;
import com.Hos.core.request.service.RequestService;

import jakarta.validation.constraints.Null;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
		RequestDTO requestDTO = new ModelMapper().map(requestService.getRequestById(Long.parseLong(request.get(Constants.ID))), RequestDTO.class);
		System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii" + request.get(Constants.CREATEDBY));
		System.out.println("-----------------------------" + requestDTO.getCreatedBy());


		if (request.get(Constants.CREATEDBY).equals(requestDTO.getCreatedBy().toString())) {
					System.out.println("00000000000000000000000000000000000000" + request.get(Constants.CREATEDBY));

			requestDTO.setMyRequest(Boolean.TRUE);
		} else {
			System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii----------------");

			requestDTO.setMyRequest(Boolean.FALSE);
		}
	    requestDTO.setType("8825924911");
		//System.out.println(null);
		requestDTO.setLocation((!requestDTO.getCities().isEmpty() && !Objects.isNull(requestDTO.getCities().get(0))) ? requestDTO.getCities().get(0).getName() : null);
		return requestDTO;
	}

	@PostMapping("/save-response")
	public Request saveResponse(@RequestBody Map<String, String> request) {
		new ModelMapper().map(requestService.saveResponse(request), ResponseDTO.class);
		return new Request();
	}

	@PostMapping("/accecpt-response")
	public Request accecptResponse(@RequestBody Map<String, String> request) {
		return new ModelMapper().map(requestService.accecptResponse(request), Request.class);
	}

	@PostMapping("/user-request-list")
	public List<RequestDTO> getUserRequestList(@RequestBody UserRequestDTO userRequestDTO) throws Exception {
		System.out.println("***********************************************************************************************************************");
		System.out.println("***********************************************************************************************************************");
		System.out.println(userRequestDTO + "  ------------------------------------ requestttttttttt");
		List<RequestDTO> requestsDTOs = new ModelMapper().map(requestService.getUserRequestList(userRequestDTO), new TypeToken<List<RequestDTO>>() {
		}.getType());
		SimpleDateFormat sdf = new SimpleDateFormat("dd'-'MMM'-'yyyy");
		System.out.println("requestsDTOs ==========" +requestsDTOs );
		requestsDTOs.stream().forEach(requestDTO -> {
			requestDTO.setLocation((!requestDTO.getCities().isEmpty() && !Objects.isNull(requestDTO.getCities().get(0))) ? requestDTO.getCities().get(0).getName() : null);
			if (requestDTO.getCreatedBy().equals(userRequestDTO.getCreatedBy())) {

				requestDTO.setMyRequest(Boolean.FALSE);
			}
			
			String s = sdf.format(requestDTO.getCreatedAt());
			System.out.println(s);
			if (requestDTO.getCreatedBy().equals(userRequestDTO.getCreatedBy()))
				requestDTO.setMyRequest(Boolean.TRUE);
				requestDTO.setDateString(s);

		});
		System.out.println(requestsDTOs + "   eeeeeeeeeeeeeeeeeeeeeee");
		return requestsDTOs;
	}

}
