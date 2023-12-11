package com.Hos.core.community.service;

import com.Hos.core.common.dto.CommunityDTO;
import com.Hos.core.common.model.User;

import java.util.List;
import java.util.Map;

public interface CommunityService {
    User joinCommunity(Map<String, String> request);
    List<CommunityDTO> getCommunityUsers(long cityId);
}
