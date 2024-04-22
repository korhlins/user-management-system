package com.asalagroup.usermgt.payload.request;


import com.asalagroup.usermgt.commons.Gender;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class UserRequest {

    private String firstName;
    private String lastName;
    private String middleName;
    private LocalDate dateOfBirth;
    private String emailAddress;
    private String mobileNumber;
    private Gender gender;
    private String country;
    private String state;
    private String city;
    private String residentialAddress;

}
