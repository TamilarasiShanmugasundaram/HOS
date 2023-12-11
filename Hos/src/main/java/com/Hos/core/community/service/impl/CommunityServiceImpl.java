package com.Hos.core.community.service.impl;

import com.Hos.core.common.dto.CommunityDTO;
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
    RequestService requestService;

    @Autowired
    UserRepository userRepository;

    @Override
    public User joinCommunity(Map<String, String> request) {
        User user = userService.getUserByUsername(request.get(Constants.USERNAME));
        user.setCommunityUser(Boolean.TRUE);
        user.setCity(requestService.getCityById(Long.parseLong(request.get(Constants.CITY))));
        return userRepository.save(user);
    }

    @Override
    public List<CommunityDTO> getCommunityUsers(long cityId) {
        return new ModelMapper().map(userService.getCommunityUsers(cityId), new TypeToken<List<CommunityDTO>>(){
        }.getType());
    }
}
