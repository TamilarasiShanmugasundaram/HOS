package com.Hos.core.community.controller;
import com.Hos.core.common.dto.CommunityDTO;
import com.Hos.core.common.dto.UserDTO;
import com.Hos.core.common.util.Constants;
import com.Hos.core.community.service.CommunityService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/community")
public class CommunityController {

    @Autowired
    CommunityService communityService;

    @PostMapping("/users")
    public List<CommunityDTO> getCommunityUsers(@RequestBody Map<String, String> request) {
        return new ModelMapper().map(communityService.getCommunityUsers(Long.parseLong(request.get(Constants.CITY))), new TypeToken<List<CommunityDTO>>(){
        }.getType());
    }

    @PostMapping("/join")
    public UserDTO joinCommunity(@RequestBody Map<String, String> request) {
        return new ModelMapper().map(communityService.joinCommunity(request), UserDTO.class);
    }
}
