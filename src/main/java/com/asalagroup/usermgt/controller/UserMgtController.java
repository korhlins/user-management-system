package com.asalagroup.usermgt.controller;


import com.asalagroup.usermgt.payload.request.GetUserRequest;
import com.asalagroup.usermgt.payload.request.UserRequest;
import com.asalagroup.usermgt.payload.request.UserUpdateResquest;
import com.asalagroup.usermgt.payload.response.GetUserResponse;
import com.asalagroup.usermgt.payload.response.ServerResponse;
import com.asalagroup.usermgt.payload.response.UserResponseData;
import com.asalagroup.usermgt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserMgtController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/users/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ServerResponse<UserResponseData> handleUserCreation(@RequestBody UserRequest userCreationRequest){
        return userService.createUser(userCreationRequest);
    }

    @PatchMapping(value = "/user/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ServerResponse<UserResponseData> handleUserUpdate(@RequestBody UserUpdateResquest userUpdateResquest){
      return userService.updateUser(userUpdateResquest);
    }

    @DeleteMapping(value = "/users/delete")
    public ServerResponse<?> deleteAllUsers(){
        return userService.deleteAllUsers();
   }

   @RequestMapping(value = "/user/get")
   public ServerResponse<GetUserResponse> getUserByEmail(@RequestBody GetUserRequest getUserRequest){
        return userService.getUserByEmail(getUserRequest);
   }

}

