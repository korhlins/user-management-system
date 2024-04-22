package com.asalagroup.usermgt.service;

import com.asalagroup.usermgt.commons.ResponseCodes;
import com.asalagroup.usermgt.entities.User;
import com.asalagroup.usermgt.payload.request.GetUserRequest;
import com.asalagroup.usermgt.payload.request.UserRequest;
import com.asalagroup.usermgt.payload.request.UserUpdateResquest;
import com.asalagroup.usermgt.payload.response.GetUserResponse;
import com.asalagroup.usermgt.payload.response.ServerResponse;
import com.asalagroup.usermgt.payload.response.UserResponseData;
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
    public ServerResponse<UserResponseData> createUser(UserRequest userCreationRequest) {

        ServerResponse<UserResponseData> serverResponse = new ServerResponse<>();

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

        UserResponseData userCreationResponseData = new UserResponseData();
        userCreationResponseData.setFirstName(createdUser.getFirstName());
        userCreationResponseData.setLastName(createdUser.getLastName());
        userCreationResponseData.setEmailAddress(createdUser.getEmailAddress());

        serverResponse.setResponseCode(ResponseCodes.SUCCESS);
        serverResponse.setResponseMessage("user created successfully!");
        serverResponse.setResponseData(userCreationResponseData);
        return serverResponse;
    }

    @Override
    public ServerResponse<UserResponseData> updateUser(UserUpdateResquest userUpdateResquest) {

        ServerResponse<UserResponseData> serverResponse = new ServerResponse<>();

        User user = userMgtRepository.findUserByEmail(userUpdateResquest.getFormerEmail());
        System.out.println(user);
        if (user == null) {
            serverResponse.setResponseCode(ResponseCodes.BAD_MODEL);
            serverResponse.setResponseMessage("User does not exist");
            return serverResponse;
        }


        User updatedUser = userMgtRepository.updateExistingUser(userUpdateResquest.getFormerEmail(), userUpdateResquest.getNewFirstName(), userUpdateResquest.getNewMobileNumber(), userUpdateResquest.getNewMobileNumber());

        UserResponseData userResponseData = new UserResponseData();
        userResponseData.setFirstName(updatedUser.getFirstName());
        userResponseData.setLastName(updatedUser.getLastName());
        userResponseData.setEmailAddress(updatedUser.getEmailAddress());

        serverResponse.setResponseCode(ResponseCodes.BAD_MODEL);
        serverResponse.setResponseMessage("user updated successfully");
        serverResponse.setResponseData(userResponseData);

        return serverResponse;
    }

    @Override
    public ServerResponse<?> deleteAllUsers(){
        ServerResponse<?> serverResponse = new ServerResponse<>();

        userMgtRepository.deleteAllUsers();

        serverResponse.setResponseMessage("Successfully deleted");
        serverResponse.setResponseCode(ResponseCodes.SUCCESS);
        return serverResponse;
    }

    @Override
    public ServerResponse<GetUserResponse> getUserByEmail(GetUserRequest getUserRequest){

        ServerResponse<GetUserResponse> serverResponse = new ServerResponse<>();

        User user = userMgtRepository.findUserByEmail(getUserRequest.getEmail());
        if(user == null) {
            serverResponse.setResponseCode(ResponseCodes.BAD_MODEL);
            serverResponse.setResponseMessage("user does not exist");
            return serverResponse;
        }

        GetUserResponse getUserResponse = new GetUserResponse();
        getUserResponse.setFirstName(user.getFirstName());
        getUserResponse.setLastName(user.getLastName());
        getUserResponse.setEmailAddress(user.getEmailAddress());
        getUserResponse.setMiddleName(user.getMiddleName());
        getUserResponse.setGender(user.getGender());
        getUserResponse.setCity(user.getCity());
        getUserResponse.setCountry(user.getCountry());
        getUserResponse.setState(user.getState());
        getUserResponse.setMobileNumber(user.getMobileNumber());
        getUserResponse.setDateOfBirth(user.getDateOfBirth());

        serverResponse.setResponseCode(ResponseCodes.SUCCESS);
        serverResponse.setResponseMessage("user selected successfully");
        serverResponse.setResponseData(getUserResponse);

        return serverResponse;
    }
}
