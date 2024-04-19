package com.asalagroup.usermgt.service;

import com.asalagroup.usermgt.payload.request.UserCreationRequest;
import com.asalagroup.usermgt.payload.request.UserUpdateResquest;
import com.asalagroup.usermgt.payload.response.ServerResponse;
import com.asalagroup.usermgt.payload.response.UserCreationResponseData;
import com.asalagroup.usermgt.payload.response.UserUpdateResponseData;

public interface UserService {
    ServerResponse<UserCreationResponseData> createUser(UserCreationRequest userCreationRequest);

    ServerResponse<UserUpdateResponseData> updateUser(UserUpdateResquest userUpdateResquest);
}
