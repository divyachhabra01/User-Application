package com.instahyre.backend.mapper;

import com.instahyre.backend.dto.UserDTO;
import com.instahyre.backend.model.User;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-22T01:02:49+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User getUser(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        user.setUserName( userDTO.getUserName() );
        user.setPhoneNumber( userDTO.getPhoneNumber() );
        user.setEmail( userDTO.getEmail() );
        user.setPassword( userDTO.getPassword() );
        user.setIsSpam( userDTO.getIsSpam() );

        return user;
    }
}
