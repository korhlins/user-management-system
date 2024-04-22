package com.asalagroup.usermgt.service;

import com.asalagroup.usermgt.payload.request.GetUserRequest;
import com.asalagroup.usermgt.payload.request.UserRequest;
import com.asalagroup.usermgt.payload.request.UserUpdateResquest;
import com.asalagroup.usermgt.payload.response.GetUserResponse;
import com.asalagroup.usermgt.payload.response.ServerResponse;
import com.asalagroup.usermgt.payload.response.UserResponseData;

public interface UserService {
    ServerResponse<UserResponseData> createUser(UserRequest userCreationRequest);

    ServerResponse<UserResponseData> updateUser(UserUpdateResquest userUpdateResquest);

    ServerResponse<?> deleteAllUsers();

    ServerResponse<GetUserResponse> getUserByEmail(GetUserRequest getUserRequest);
}
