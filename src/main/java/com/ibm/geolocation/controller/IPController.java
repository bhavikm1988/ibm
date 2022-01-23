package com.ibm.geolocation.controller;


import com.ibm.geolocation.config.AppConstants;
import com.ibm.geolocation.exception.ErrorMessage;
import com.ibm.geolocation.models.IpInfo;
import com.ibm.geolocation.models.User;
import com.ibm.geolocation.models.UserResponse;
import com.ibm.geolocation.service.IPService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.*;


@Slf4j
@Path("/users")
public class IPController {

    private static Map<UUID, User> DB = new HashMap<>();

    @Autowired
    private IPService ipService;


    @GET
    @Produces("application/json")
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        users.addAll(new ArrayList<>(DB.values()));
        return users;
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response createUser(@Valid User user) throws Exception{
        log.debug(user.getPassword());
        user.setId(UUID.randomUUID());
        IpInfo info = ipService.getIpInformation(user.getIpAddress().toString());
        log.debug("User Id {} country {} city {}", user.getId(), info.getCountry(), info.getCity());
        if(info.getCountry() != null && info.getCountry().equalsIgnoreCase("Canada")) {
            user.setCity(info.getCity());
            user.setCountry(info.getCountry());
            DB.put(user.getId(), user);
            UserResponse response = new UserResponse();
            response.setUser(user);
            response.setMessage("Welcome " + user.getUsername());
            return Response.status(201).entity(response).build();
        }else{
            ErrorMessage error = new ErrorMessage();
            error.setStatus(HttpStatus.FORBIDDEN.toString());
            error.setMessage(AppConstants.IPADDRESS_INVALID);
            return Response.status(400).entity(error).build();
        }

    }

}