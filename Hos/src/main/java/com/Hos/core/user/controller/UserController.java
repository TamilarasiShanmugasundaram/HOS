package com.Hos.core.user.controller;
import com.Hos.core.common.dto.UserDTO;
import com.Hos.core.user.service.UserService;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
	private UserService userService;

    @GetMapping("/list")
	public List<UserDTO> getAllUsers() {
		return userService.getAllUsers();
	}
}
