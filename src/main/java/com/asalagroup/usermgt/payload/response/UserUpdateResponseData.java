package com.asalagroup.usermgt.payload.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateResponseData {
    private String firstName;
    private String lastName;
    private String emailAddress;
}
