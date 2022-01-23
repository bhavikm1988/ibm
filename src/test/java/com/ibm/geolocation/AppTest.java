package com.ibm.geolocation;


import com.ibm.geolocation.controller.IPController;
import com.ibm.geolocation.models.User;
import com.ibm.geolocation.models.UserResponse;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(IPController.class);
    }

    @Test
    public void createAndGetUsers() {
        User[] users = target("/users").request().get(User[].class);
        assertEquals(users.length, 0);

        String jsonString = "{\"username\":\"te\",\"password\":\"Apple123sff#\",\"ipAddress\":\"24.48.9.1\"}";
        Response response = target("/users").request().post(Entity.json(jsonString));
        UserResponse userResponse = response.readEntity(UserResponse.class);
        assertThat(userResponse.getUser().getUsername()).isEqualTo("te");
        assertThat(userResponse.getUser().getCountry()).isEqualTo("Canada");

        users = target("/users").request().get(User[].class);
        assertEquals(users.length, 1);

    }


}
