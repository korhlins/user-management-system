package com.asalagroup.usermgt.service;

import com.asalagroup.usermgt.commons.ResponseCodes;
import com.asalagroup.usermgt.entities.User;
import com.asalagroup.usermgt.payload.request.UserCreationRequest;
import com.asalagroup.usermgt.payload.request.UserUpdateResquest;
import com.asalagroup.usermgt.payload.response.ServerResponse;
import com.asalagroup.usermgt.payload.response.UserCreationResponseData;
import com.asalagroup.usermgt.payload.response.UserUpdateResponseData;
import com.asalagroup.usermgt.repositories.UserMgtRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMgtRepository userMgtRepository;

    @Override
    public ServerResponse<UserCreationResponseData> createUser(UserCreationRequest userCreationRequest) {

        ServerResponse<UserCreationResponseData> serverResponse = new ServerResponse<>();

        User user = userMgtRepository.findUserByEmail(userCreationRequest.getEmailAddress());
        if (user != null) {
            serverResponse.setResponseCode(ResponseCodes.BAD_MODEL);
            serverResponse.setResponseMessage("User email already exist");
            return serverResponse;
        }
        user = userMgtRepository.findUserByMobileNumber(userCreationRequest.getMobileNumber());
        if (user != null) {
            serverResponse.setResponseCode(ResponseCodes.BAD_MODEL);
            serverResponse.setResponseMessage("User mobile number already exist");
            return serverResponse;
        }
        user = new User();
        user.setFirstName(userCreationRequest.getFirstName());
        user.setLastName(userCreationRequest.getLastName());
        user.setMiddleName(userCreationRequest.getMiddleName());
        user.setDateOfBirth(userCreationRequest.getDateOfBirth());
        user.setEmailAddress(userCreationRequest.getEmailAddress());
        user.setMobileNumber(userCreationRequest.getMobileNumber());
        user.setGender(userCreationRequest.getGender());
        user.setCountry(userCreationRequest.getCountry());
        user.setState(userCreationRequest.getState());
        user.setCity(userCreationRequest.getCity());
        user.setResidentialAddress(userCreationRequest.getResidentialAddress());

        User createdUser = userMgtRepository.createUser(user);

        UserCreationResponseData userCreationResponseData = new UserCreationResponseData();
        userCreationResponseData.setFirstName(createdUser.getFirstName());
        userCreationResponseData.setLastName(createdUser.getLastName());
        userCreationResponseData.setEmailAddress(createdUser.getEmailAddress());

        serverResponse.setResponseCode(ResponseCodes.SUCCESS);
        serverResponse.setResponseMessage("user created successfully!");
        serverResponse.setResponseData(userCreationResponseData);
        return serverResponse;
    }

    @Override
    public ServerResponse<UserUpdateResponseData> updateUser(UserUpdateResquest userUpdateResquest) {

        System.out.println(userUpdateResquest.getNewFirstName());

        ServerResponse<UserUpdateResponseData> serverResponse = new ServerResponse<>();

        System.out.println(userUpdateResquest.getFormerEmail() + " " + userUpdateResquest.getNewFirstName() + " " + userUpdateResquest.getNewMobileNumber() + " " +userUpdateResquest.getNewMobileNumber());

        User user = userMgtRepository.findUserByEmail(userUpdateResquest.getFormerEmail());
        System.out.println(user);
        if (user == null) {
            serverResponse.setResponseCode(ResponseCodes.BAD_MODEL);
            serverResponse.setResponseMessage("User does not exist");
            return serverResponse;
        }



        User updatedUser = userMgtRepository.updateExistingUser(userUpdateResquest.getFormerEmail(), userUpdateResquest.getNewFirstName(), userUpdateResquest.getNewMobileNumber(), userUpdateResquest.getNewMobileNumber());

        System.out.println(updatedUser);
        UserUpdateResponseData userUpdateResponseData = new UserUpdateResponseData();
        userUpdateResponseData.setFirstName(updatedUser.getFirstName());
        userUpdateResponseData.setLastName(updatedUser.getLastName());
        userUpdateResponseData.setEmailAddress(updatedUser.getEmailAddress());

        serverResponse.setResponseCode(ResponseCodes.BAD_MODEL);
        serverResponse.setResponseMessage("user updated successfully");
        serverResponse.setResponseData(userUpdateResponseData);

        return serverResponse;
    }
}
