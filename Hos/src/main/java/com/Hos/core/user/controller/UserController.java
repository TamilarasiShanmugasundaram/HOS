package com.Hos.core.user.controller;
import com.Hos.core.common.dto.SignUpDTO;
import com.Hos.core.common.dto.UserDTO;
import com.Hos.core.common.model.User;
//import com.Hos.core.common.exception.CustomException;
import com.Hos.core.common.util.Constants;
import com.Hos.core.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

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

	@PostMapping("/session")
	public User login(@RequestParam Map<String, String> request) {
		System.out.println("valuuuuuuuuuuuuu" + request);
		if (request.get("username").contains("ideas2it.com")) {
			return userService.getUserByUsernameAndPassword(request.get("username"), request.get("password"));
		} 
		throw new Error("Invalid username or password");
	}


	@PostMapping("/get-by-username")
	public UserDTO getUserByUsername(@RequestBody Map<String, String> request) {
		return new ModelMapper().map(userService.getUserByUsername(request.get(Constants.USERNAME)), UserDTO.class);
	}

	@PostMapping("/get-by-id")
	public UserDTO getUserById(@RequestBody Map<String, String> request) {
		return new ModelMapper().map(userService.getUserByUsername(request.get(Constants.USERNAME)), UserDTO.class);
	}

	@PostMapping("/signup")
	public UserDTO signup(@RequestBody SignUpDTO request) {
		return userService.signup(request);
	}

	@PostMapping("/send-otp")
	public String sendOtp(@RequestBody Map<String, String> request) {
		return userService.sendOtp(request.get(Constants.USERNAME));
	}

}
