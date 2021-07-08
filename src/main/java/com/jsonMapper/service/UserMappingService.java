package com.jsonMapper.service;


import com.jsonMapper.Repository.UserMappingRepository;
import com.jsonMapper.model.userMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMappingService {
    @Autowired
    private UserMappingRepository userMappingRepository;

    public String getUserMapping(Long userId) {
        return userMappingRepository.findByUserId(userId).getMapping();
    }

    public String addMappings(userMapping userMap){
        userMappingRepository.save(userMap);
        return userMappingRepository.findByUserId(userMap.getUserId()).getMapping();
    }

    public void deleteMappings(Long userId){
        userMappingRepository.deleteByUserId(userId);
    }
    public String updateMappings(userMapping userMap){
        userMappingRepository.deleteByUserId(userMap.getUserId());
        return userMappingRepository.save(userMap).getMapping();
    }

    public List<userMapping> getAllUserMappings() {
        return userMappingRepository.findAll();
    }
}
