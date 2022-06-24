package com.instahyre.backend.service.impl;

import com.instahyre.backend.dto.LoginRequest;
import com.instahyre.backend.dto.UserDTO;
import com.instahyre.backend.mapper.UserMapper;
import com.instahyre.backend.model.User;
import com.instahyre.backend.repository.UserRepository;
import com.instahyre.backend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String, Object> userRegistration(UserDTO userDTO) {
        Map<String, Object> map = new HashMap<>();
        List<User> users;
        boolean atLeastOne = true;
        try {
            users = userRepository.findUsersByPhoneNumber(userDTO.getPhoneNumber());
            User user = userMapper.getUser(userDTO);
            if (users.isEmpty()) {
                userRepository.saveAndFlush(user);
                map.put("responseMessage", "User data saved successfully");


            } else {
                for (User value : users) {
                    if (value.getPassword() != null) {
                        atLeastOne = false;
                        map.put("responseMessage", "This number is already registered with another user account. Please enter another number");
                    }
                }
                if (atLeastOne) {
                    userRepository.saveAndFlush(user);
                    map.put("responseMessage", "User data saved successfully");
                }
            }
        } catch (Exception e) {
            log.error("Getting error due to: {}", e.getMessage());
            throw  new RuntimeException("Getting error due to: "+ e.getMessage());
        }
        return map;
    }

    @Override
    public Boolean markSpam(String phoneNumber) {
        List<User> users;
        try {
            users = userRepository.findUsersByPhoneNumber(phoneNumber);
            if (users.isEmpty()) {
                User user = new User();
                user.setPhoneNumber(phoneNumber);
                user.setIsSpam(Boolean.TRUE);
                userRepository.saveAndFlush(user);
               log.info("New number has been added to spam");
               return true;
            } else {
                for (User value : users) {
                    if (value.getPhoneNumber() != null) {
                        value.setIsSpam(Boolean.TRUE);
                        final User updatedUser = userRepository.saveAndFlush(value);
                    }
                }
                log.info("Phone Number marked spam successfully");
            }
            return true;
        } catch (Exception e) {
            log.error("Getting error due to: {}", e.getMessage());
            throw new RuntimeException("Getting error due to: "+ e.getMessage());
        }
    }

    @Override
    public Boolean isRegistered(LoginRequest request) {
        return Objects.nonNull(userRepository.findUserByPhoneNumberAndPassword(request.getPhoneNumber(), request.getPassword()));
    }
}


