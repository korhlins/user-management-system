package com.asalagroup.usermgt.payload.request;

import com.asalagroup.usermgt.commons.Gender;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class UserUpdateResquest {
        private String formerEmail;
        private String newFirstName;
        private String lastName;
        private String middleName;
        private LocalDate dateOfBirth;
        private String newEmailAddress;
        private String newMobileNumber;
        private Gender gender;
        private String country;
        private String state;
        private String city;
        private String residentialAddress;
}
