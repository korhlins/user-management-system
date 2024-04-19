package com.asalagroup.usermgt.controller;


import com.asalagroup.usermgt.payload.request.UserCreationRequest;
import com.asalagroup.usermgt.payload.request.UserUpdateResquest;
import com.asalagroup.usermgt.payload.response.ServerResponse;
import com.asalagroup.usermgt.payload.response.UserCreationResponseData;
import com.asalagroup.usermgt.payload.response.UserUpdateResponseData;
import com.asalagroup.usermgt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserMgtController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/users/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ServerResponse<UserCreationResponseData> handleUserCreation(@RequestBody UserCreationRequest userCreationRequest){
        return userService.createUser(userCreationRequest);
    }

    @PatchMapping(value = "/user/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ServerResponse<UserUpdateResponseData> handleUserUpdate(@RequestBody UserUpdateResquest userUpdateResquest){
      return userService.updateUser(userUpdateResquest);
    }

}
