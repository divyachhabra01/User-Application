package com.instahyre.backend.mapper;

import com.instahyre.backend.dto.UserDTO;
import com.instahyre.backend.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE= Mappers.getMapper(UserMapper.class);
    User getUser(UserDTO userDTO);
}
