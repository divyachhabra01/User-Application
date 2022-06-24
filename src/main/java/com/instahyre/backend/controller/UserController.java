package com.instahyre.backend.controller;

import com.instahyre.backend.dto.LoginRequest;
import com.instahyre.backend.dto.UserDTO;
import com.instahyre.backend.service.UserSearchService;
import com.instahyre.backend.service.UserService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserSearchService userSearchService;

    @PostMapping(value = "/sign-up")
    public ResponseEntity<?> userRegistration( @NotNull  @RequestBody UserDTO userDTO) {
        Assert.notNull(userDTO.getPassword(), "Password cannot be empty");
        Assert.notNull(userDTO.getPhoneNumber(), "Phone Number can not be Empty");
        Assert.notNull(userDTO.getUserName(), "User Name Cannot be empty");
        return ResponseEntity.ok().body(userService.userRegistration(userDTO));
    }

    @PatchMapping(value = "/spam")
    public ResponseEntity<String> markSpam(@RequestParam String phoneNumber) {
        return ResponseEntity.ok().body(userService.markSpam(phoneNumber) ? "Success" : "failed");
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> searchUsers(@RequestParam(value = "name",required = false) String name, @RequestParam(value = "phoneNumber",required = false) String phoneNumber) {
        if (Strings.isNotBlank(phoneNumber)) {
            return ResponseEntity.ok().body(userSearchService.searchUsersByPhoneNumber(phoneNumber));
        }
        if (Strings.isNotBlank(name)) {
            return ResponseEntity.ok().body(userSearchService.searchUsersByUserName(name));
        }
        return ResponseEntity.ok().body(Collections.emptyList());
    }

    @PostMapping(value = "/sign-in", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@NotNull @Valid @RequestBody LoginRequest loginRequest) {
        Assert.notNull(loginRequest.getPassword(), "Password cannot be empty");
        Assert.notNull(loginRequest.getPhoneNumber(), "Phone Number can not be Empty");
        return ResponseEntity.ok().body(userService.isRegistered(loginRequest));
    }
}
