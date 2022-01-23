package com.ibm.geolocation.models;

import com.ibm.geolocation.config.AppConstants;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class UserValidatorTest {

    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void inValidUserName() throws UnknownHostException {
        User user = new User();
        user.setUsername("  ");
        user.setPassword("Apple123%");
        user.setIpAddress(InetAddress.getByName("10.0.0.0"));
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        for (ConstraintViolation<User> a: violations) {
            assertEquals(a.getMessage(), AppConstants.USERNAME_BLANK);
        }
    }

    @Test
    public void inValidPassword() throws UnknownHostException {
        User user = new User();
        user.setUsername("test");
        user.setPassword("Apple123");
        user.setIpAddress(InetAddress.getByName("10.0.0.0"));
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        for (ConstraintViolation<User> a: violations) {
            assertEquals(a.getMessage(), AppConstants.PASSWORD_INVALID);
        }
    }

    @Test
    public void inValidIPAddress() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("Apple123%");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        for (ConstraintViolation<User> a: violations) {
            assertEquals(a.getMessage(), AppConstants.IPADDRESS_NULL);
        }
    }

    @Test
    public void validInputs() throws UnknownHostException {
        User user = new User();
        user.setUsername("test");
        user.setPassword("Apple123%");
        user.setIpAddress(InetAddress.getByName("10.0.0.0"));
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertTrue(violations.isEmpty());
    }
}