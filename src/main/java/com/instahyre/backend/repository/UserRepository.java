package com.instahyre.backend.repository;

import com.instahyre.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findUsersByPhoneNumber(String PhoneNumber);
    List<User> findUsersByUserNameContains(String name);
    User findUserByPhoneNumberAndPassword(String phoneNumber, String password);
}
