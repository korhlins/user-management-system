package com.asalagroup.usermgt.payload.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServerResponse<T> {

    private String responseCode;
    private String responseMessage;
    private T responseData;
}
