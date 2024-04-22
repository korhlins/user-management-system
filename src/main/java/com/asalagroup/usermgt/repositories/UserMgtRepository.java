package com.asalagroup.usermgt.repositories;

import com.asalagroup.usermgt.entities.User;

public interface UserMgtRepository {

    User createUser(User user);

    User findUserByEmail(String email);

    User findUserByMobileNumber(String mobileNumber);

    User updateExistingUser(String formerEmail, String firstName, String mobileNumber, String email);

    void deleteAllUsers();
}
