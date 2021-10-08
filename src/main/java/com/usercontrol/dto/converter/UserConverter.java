package com.usercontrol.dto.converter;

import com.usercontrol.dto.RegistrationRequest;
import com.usercontrol.entities.User;
import com.usercontrol.entities.UserRole;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public User convert(RegistrationRequest request){
        return new User(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                UserRole.USER);
    }

}
