package com.ibm.geolocation.config;

public class AppConstants {


    public static final String USERNAME_BLANK = "Username cannot be Empty or Null or Blank";
    public static final String IPADDRESS_NULL = "IpAddress cannot be Null";
    public static final String IPADDRESS_INVALID = "IpAddress not from CANADA";
    public static final String PASSWORD_BLANK = "Password cannot be Empty or Null or Blank";
    public static final String PASSWORD_INVALID = "Password does not meet the required standards";

    public static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[_$#.%])[A-Za-z\\d_$#.%]{8,}$";
}
