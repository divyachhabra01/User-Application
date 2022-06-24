package com.instahyre.backend.service;

import com.instahyre.backend.dto.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserSearchService {
     List<UserDTO> searchUsersByUserName(String name);
     List<UserDTO> searchUsersByPhoneNumber(String phoneNumber);
}
