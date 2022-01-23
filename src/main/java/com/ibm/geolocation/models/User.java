package com.ibm.geolocation.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ibm.geolocation.config.AppConstants;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.net.InetAddress;
import java.util.UUID;

@Data
public class User {

    public UUID id;

    public String country;

    public String city;

    @NotBlank(message = AppConstants.USERNAME_BLANK)
    public String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = AppConstants.PASSWORD_BLANK )
    @Pattern(regexp = AppConstants.PASSWORD_REGEX,  message = AppConstants.PASSWORD_INVALID)
    public String password;

    @NotNull(message = AppConstants.IPADDRESS_NULL)
    public InetAddress ipAddress;

}
