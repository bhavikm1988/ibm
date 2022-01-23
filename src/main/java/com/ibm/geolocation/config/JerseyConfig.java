package com.ibm.geolocation.config;

import com.ibm.geolocation.controller.IPController;
import com.ibm.geolocation.exception.UserExceptionMapper;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig
{
    public JerseyConfig() {
        register(IPController.class);
        register(UserExceptionMapper.class);
    }
}