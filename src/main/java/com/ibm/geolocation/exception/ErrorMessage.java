package com.ibm.geolocation.exception;

import lombok.Data;

@Data
public class ErrorMessage {
    private String status;
    private String message;
}
