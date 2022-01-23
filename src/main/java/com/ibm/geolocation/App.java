package com.ibm.geolocation;


import com.ibm.geolocation.service.IPService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.ibm.geolocation")
//@ComponentScan({"com.ibm.geolocation.exception", "com.ibm.geolocation.config", "com.ibm.geolocation.controller"})
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public IPService getIPService(){
        return new IPService();
    }

}
