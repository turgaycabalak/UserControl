package com.usercontrol.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class RegistrationRequest {

    private String firstName;
    private String lastName;
    private String password;
    private String email;

}
