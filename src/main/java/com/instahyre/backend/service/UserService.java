package com.instahyre.backend.service;

import com.instahyre.backend.dto.LoginRequest;
import com.instahyre.backend.dto.UserDTO;

import java.util.Map;

public interface UserService {
    Map<String, Object> userRegistration(UserDTO user);

    Boolean markSpam(String phoneNumber);
    Boolean isRegistered(LoginRequest request);

}
