package com.asalagroup.usermgt.payload.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreationResponseData {
    private String firstName;
    private String lastName;
    private String emailAddress;
}
