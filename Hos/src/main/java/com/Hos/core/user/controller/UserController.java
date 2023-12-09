package com.Hos.core.user.controller;
import com.Hos.core.common.dto.UserDTO;
import com.Hos.core.common.util.Constants;
import com.Hos.core.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
	private UserService userService;

    @GetMapping("/list")
	public List<UserDTO> getAllUsers() {
		return new ModelMapper().map(userService.getAllUsers(), new TypeToken<List<UserDTO>>() {
		}.getType());
	}

	@PostMapping("/get-by-username")
	public UserDTO getUserByUsername(@RequestBody Map<String, String> request) {
		return new ModelMapper().map(userService.getUserByUsername(request.get(Constants.USERNAME)), UserDTO.class);
	}

//	@PostMapping("/signup")
//	public UserDTO signup(@RequestBody Map<String, String>) {
//
//	}

	@PostMapping("/send-otp")
	public String sendOtp(@RequestBody Map<String, String> request) {
		return userService.sendOtp(request.get(Constants.USERNAME));
	}

}
