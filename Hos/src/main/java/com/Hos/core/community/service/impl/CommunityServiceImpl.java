package com.Hos.core.community.service.impl;

import com.Hos.core.common.dto.CommunityDTO;
import com.Hos.core.common.dto.UserResponseDTO;
import com.Hos.core.common.model.User;
import com.Hos.core.common.util.Constants;
import com.Hos.core.community.service.CommunityService;
import com.Hos.core.request.service.RequestService;
import com.Hos.core.user.repository.UserRepository;
import com.Hos.core.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CommunityServiceImpl implements CommunityService {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

	@Override
	public UserResponseDTO joinCommunity(Map<String, String> request) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		User user = userService.getUserByUserId(request.get(Constants.USERID));
		System.out.println(user);
		if (!user.isCommunityUser()) {
			user.setCommunityUser(Boolean.TRUE);
			userResponseDTO.setUser(userRepository.save(user));
			userResponseDTO.setMessage("Registered Successfully");
			return userResponseDTO;
		} else {
			userResponseDTO.setMessage("YOU HAVE ALREADY REGISTERED AS A COMMUNITY MEMBER");
			return userResponseDTO;
		}
	}

    @Override
    public List<CommunityDTO> getCommunityUsers(long cityId) {
        return new ModelMapper().map(userService.getCommunityUsers(cityId), new TypeToken<List<CommunityDTO>>(){
        }.getType());
    }
}
