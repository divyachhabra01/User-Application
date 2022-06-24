package com.instahyre.backend.service.impl;

import com.instahyre.backend.dto.UserDTO;
import com.instahyre.backend.model.User;
import com.instahyre.backend.repository.UserRepository;
import com.instahyre.backend.service.UserSearchService;
import com.instahyre.backend.util.UserNameComparator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class UserSearchServiceImpl implements UserSearchService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDTO> searchUsersByUserName(String name) {
        List<User> users;
        try {
            users = userRepository.findUsersByUserNameContains(name);
            if (users.isEmpty()) {
                log.info("No record found for with userName {}", name);
            } else {
                List<UserDTO> userList = new ArrayList<>();
                for (User user : users) {
                    UserDTO.UserDTOBuilder userDtoBuilder = UserDTO.builder().userName(user.getUserName())
                            .phoneNumber(user.getPhoneNumber())
                            .isSpam(user.getIsSpam());
                    if (user.getPassword() != null) {
                        userDtoBuilder.email(user.getEmail());

                    }
                    userList.add(userDtoBuilder.build());
                }
                userList.sort(new UserNameComparator(name));
                return userList;
            }
        } catch (Exception e) {
            log.error("Getting error due to: {}", e.getMessage());
            throw new RuntimeException("Exception occurred " + e.getMessage());
        }
        return Collections.emptyList();
    }


    @Override
    public List<UserDTO> searchUsersByPhoneNumber(String phoneNumber) {
        List<User> users;
        try {
            users = userRepository.findUsersByPhoneNumber(phoneNumber);
            if (users.isEmpty()) {
                log.info("No record found for with phoneNumber {}", phoneNumber);
            } else {
                List<UserDTO> userList = new ArrayList<>();
                for (User user : users) {
                    if (user.getPassword() != null) {
                        userList.add(UserDTO.builder()
                                .userName(user.getUserName())
                                .phoneNumber(user.getPhoneNumber())
                                .isSpam(user.getIsSpam())
                                .email(user.getEmail())
                                .build());

                    }
                }
                return userList;
            }
        } catch (Exception e) {
            log.error("Getting error due to: {}", e.getMessage());
            throw new RuntimeException("Exception occurred " + e.getMessage());
        }
        return Collections.emptyList();
    }
}
