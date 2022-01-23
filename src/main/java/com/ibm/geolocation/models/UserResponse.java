package com.ibm.geolocation.models;

import lombok.Data;

@Data
public class UserResponse {
    private User user;
    private String message;
}
