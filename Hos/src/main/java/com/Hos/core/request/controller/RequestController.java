package com.Hos.core.request.controller;
import org.springframework.web.bind.annotation.RestController;

import com.Hos.core.request.Model.Request;
import com.Hos.core.request.service.RequestService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/request")
public class RequestController {

    @Autowired
	RequestService requestService;

    @GetMapping("/list")
	public List<Request> hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return requestService.getRequest();
	}
}
